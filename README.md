# java-elf 项目实行接口关键字+数据驱动模式开发测试

- 关键字

该项目可以通过”接口名称“关键字，匹配该接口名称下的测试用例。

- 数据驱动数据驱动目前实行两种途径

1、excel数据驱动（比较传统）
示例代码见：EasypoiWxCaseService类

2、数据库数据驱动（更易用于扩展）
示例代码见：JpaWXCaseService类

可以根据自己维护用例的习惯进行其中的数据驱动选择

3、持续测试集成
配置jdk环境和maven环境并运行：
```$xslt
mvn clean test
```
执行testng.xml文件中配置的测试套件

4、本地maven编译推送至远程maven库
```
mvn clean deploy -X -Dmaven.test.skip=true

```

4、~~功能废弃-新增测试用例动态生成（必填项为空，必填项缺省、超长长度...），实际测试中后端对于必填为空、超长长度、类型不一致等校验统一处理，验证一个即为其他为参数空情况的验证覆盖~~
```
//简单示例
//请求参数
{
		"parames1": "11",
		"parames2": "22",
		"parames3": "33"
}
//返回结果
{
	"参数值：parames1为空返回结果": {
		"parames1": "",
		"parames2": "22",
		"parames3": "33"
	},
	"参数值：parames2为空返回结果": {
		"parames1": "11",
		"parames2": "",
		"parames3": "33"
	},
	"参数值：parames3为空返回结果": {
		"parames1": "11",
		"parames2": "22",
		"parames3": ""
	}
}

```
5、跳出接口测试误区，接口更适合做冒烟和回归测试。虽然接口测试的本质也是功能测试，但接口的测试粒度应该是根据后端接收参数处理逻辑来定。

6、增加随机数据生成：
```
     Random(String[0,10])
     Random(Char[10])
     Random(Long[10])
     Random(Boolean)
入参数参数直接写随机函数即可，eg:
{
"data":{
 "key1":"测试-numRandom(Long[2])",
 "key2":"测试-Random(Char[3])"
  }
}
//对应会生成：测试-num34的随机数和测试-jh的随机数
```
