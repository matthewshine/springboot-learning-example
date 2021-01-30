package com.kouryoushine;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName AsyncServlet
 * @Description TODO
 * @Author kouryoushine
 * @Date 2020/10/9 23:17
 * @Version 1.0
 */
@WebServlet(asyncSupported = true,//开启异步支持
name="asyncServlet", //servlet 名字
        urlPatterns = "/asyncServlet"
)
public class AsyncServlet extends HttpServlet {
    //覆盖service
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
            if(request.isAsyncSupported()){
                AsyncContext asyncContext = request.startAsync();
                asyncContext.addListener(new AsyncListener() {
                    @Override
                    public void onComplete(AsyncEvent asyncEvent) throws IOException {
                        println("onComplete");
                    }

                    @Override
                    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                        println("onTimeout");
                    }

                    @Override
                    public void onError(AsyncEvent asyncEvent) throws IOException {
                        println("onError");
                    }

                    @Override
                    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                        println("onStartAsync");
                    }
                });

                ServletResponse response1 = asyncContext.getResponse();
                response1.setContentType("text/plain;charset=UTF-8");
                PrintWriter writer=response1.getWriter();
                writer.println("helloworld");
                writer.flush();
            }
    }

    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("AsyncEvent[" + threadName + "]: " + object);
    }
}
