import com.kouryoushine.mybatis.demo.MybatisDemoApplication;
import com.kouryoushine.mybatis.demo.entity.User;
import com.kouryoushine.mybatis.demo.mapper.UserMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = MybatisDemoApplication.class)
@RunWith(SpringRunner.class)
public class MybatisApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test() {
        List<User> allUser = userMapper.getAllUser();
        System.out.println(allUser);
    }

}
