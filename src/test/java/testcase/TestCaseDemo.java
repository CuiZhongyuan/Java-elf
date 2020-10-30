package testcase;

import com.javaelf.utils.JsonUtils;
import com.javaelf.utils.LoadStaticConfigUtil;
import com.javaelf.utils.RestTemplateUtils;
import com.javaelf.utils.VariableUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import java.util.Map;

public class TestCaseDemo {

    @Test
    public void case1(){
        String str1 = "qzcsbj";
        String str2 = "qzcsbj";
        String str3 = new String("qzcsbj");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
    }

    @Test
    public void case2(){
        String excelCasePath = (String) LoadStaticConfigUtil.getCommonYml("testcaseexcel.cases");
        String var = VariableUtil.replaceVariables("${loginName}");
        System.out.println(var);
    }
    @Test
    public void case3(){
        String url = "http://192.168.1.57:811/platform-product/commodity/page-others";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMzIxNDAwMTQ5NjAzMzg5NDQwIiwiYXVkIjoiMDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjYiLCJjb21wYW55SWRzIjpbMTI2MDQ2MzA2NzEwODYxMDA0OF0sIm5iZiI6MTYwNDAyMTM3MSwiaXNzIjoicmVzdGFwaXVzZXIiLCJleHAiOjE2MDQwMjg1NzEsInVzZXJJZCI6MTMyMTQwMDE0OTYwMzM4OTQ0MCwiaWF0IjoxNjA0MDIxMzcxLCJqdGkiOiJiMzBmZjJjMi1kZTUxLTQ4ODYtYjNkMy01ODQ2YWIxOTc4NjkifQ.d73DENmtduK7M53HqVhIYb4Aq_1nOWAtvfBzOk3Wmzk";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        ResponseEntity responseEntity = RestTemplateUtils.get(url,headers,Object.class,"");
        Map<String,Object> map = (Map<String, Object>) responseEntity.getBody();
        System.out.println(JsonUtils.mapToJson(map));
    }
}
