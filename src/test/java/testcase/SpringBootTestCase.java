package testcase;

import com.javaelf.JavaElfApplication;
import com.javaelf.utils.CreateIDCardNo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaElfApplication.class)
public class SpringBootTestCase {

    public void getid(){
        String createIDCardNo = CreateIDCardNo.getRandomID();
        System.out.println(createIDCardNo);
    }
}
