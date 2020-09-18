package testcase;

import com.interfaceproject.Application;
import com.interfaceproject.listener.TimeTask;
import com.interfaceproject.utils.BaseTestngInit;
import com.interfaceproject.utils.hxutils.CreateIDCardNo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringBootTestCase {

    @Autowired
    private TimeTask timeTask;

//    测试类注解使用junit
    @Test
    public void executeCse() throws Exception {
        timeTask.runTest();
    }

    public void getid(){
        String createIDCardNo = CreateIDCardNo.getRandomID();
        System.out.println(createIDCardNo);
    }
}
