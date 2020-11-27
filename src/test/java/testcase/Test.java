package testcase;



import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author:
 * @Date:
 */
public class Test {

    @org.testng.annotations.Test
    public void test1() {
        HashMap<String, Object> otherPara = new HashMap();
        otherPara.put("key1", "value1");
        otherPara.put("key2", "value2");
        otherPara.put("key3", "value3");

        HashMap<String, Object> para1 = RandomParameter.getStrParam(7, 10, "para1");

        HashMap<String, Object> para2 = RandomParameter.getStrParam(4, 6, "para2");

        HashMap<String, Object> para3 = RandomParameter.getStrParam(0, 3, "para3");

        HashMap<String, Object> para4 = RandomParameter.getStrParam(11, 21, "para4");

        ArrayList<HashMap<String, Object>> orgData = RandomParameter.getOrgData(para1, para2, para3, para4);
        System.out.println(orgData.size());

        ArrayList<String> strings = RandomParameter.requestBodyArrayList(orgData, otherPara);
        for (String str : strings
        ) {
            System.out.println(str);
        }

    }
    @org.testng.annotations.Test
    public void test2(){
    }

}
