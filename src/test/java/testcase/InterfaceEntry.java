package testcase;

import com.javaelf.utils.BaseTestngInit;
import org.testng.annotations.Test;

/**
 * 测试用例入口
 * */
public class InterfaceEntry {
    BaseTestngInit baseTestngInit = new BaseTestngInit();
    @Test
    public void EntryCase(){
        baseTestngInit.baseTestngInitCode();
    }
}
