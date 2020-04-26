package com.kouryoushine.springbootlearingexample.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/my/webservlet",asyncSupported = true)
public class MyServlet extends HttpServlet { //servlet3.0规范里决定的实现方式

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AsyncContext asyncContext = req.getAsyncContext();
        asyncContext.start(()->{
            try {
//                Thread.sleep(5000);
                resp.getWriter().print("HelloWorld");

                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
