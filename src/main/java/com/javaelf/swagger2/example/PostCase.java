package com.javaelf.swagger2.example;

import com.javaelf.utils.CreateIDCardNo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 *PostCase类供测试类方法调用
 *@author czy
 * @date 2020年05月09日
 */

@RestController
@Api(value = "api",tags = "接口测试用例")
public class PostCase {


//   其中@PostMapping注解=@RequestMapping(value = "/postjson", method = RequestMethod.POST)
//    @RequestMapping(value = "/postjson", method = RequestMethod.POST)

//    post 请求无 header 参数的请求示例
    @ApiOperation(value ="case1--无header的post用例")
    @PostMapping("/jsonpost")
    public Map<String, Object> postCase1(@RequestBody Map<String, Object> reqMap) throws Exception {
        String name = (String) reqMap.get("name");
        String age = (String) reqMap.get("age");
        if ((null == name || "".equals(name)) || (null == age || "".equals(age))) {
            throw new Exception("参数异常");
        }
        //这里用两种方式返回。1、Javabean对象 2、Map对象
//        User user = new User(12,"121","121","121");
//        //这里需要把javabean对象转成字符串然后在转成map对象
//        return JsonUtils.json2map(JsonUtils.obj2json(user));
        //Map对象返回响应
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("error", "1000");
        resMap.put("mages", "success");
        resMap.put("data","20200509");
        return resMap;
    }
    // post请求带header参数的示例，header中包含：token ，jwt,session等
    @ApiOperation(value = "case2--有header的post用例")
//    @RequestMapping(value = "/headerpost",method = RequestMethod.POST)
    @PostMapping("/headerpost")
    public Map<String,Object> postCase2(HttpServletRequest request, @RequestBody Map<String, Object> reqMap) throws Exception {
        String data = (String)reqMap.get("data");
        String age = (String) reqMap.get("age");
        if ((null == data || "".equals(data)) || (null == age || "".equals(age))) {
            throw new Exception("参数异常");
        }
        String token = request.getHeader("header");
        System.out.println("token:"+token);
        String num = "123456789%";

        //Map对象返回响应
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("address","北京六道口");
        Map<String,Object> contMap = new HashMap<>();
        contMap.put("error", "1000");
        contMap.put("mages", "success");
        contMap.put("data","20200509");
        contMap.put("content",resMap);
        if(  null == token || "".equals(token) || !token.equals(num)){
            throw new Exception("token鉴权失败");
        }
        return contMap;
    }
    @ApiOperation(value = "case3--身份证ID自动生成")
    @GetMapping("/getRandomID")
    public String getRandomID(){
        String randomID = CreateIDCardNo.getRandomID();
        return randomID;
    }

}
