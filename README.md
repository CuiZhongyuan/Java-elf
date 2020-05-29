### 前言
Java-elf是一个基于java语言的轻巧灵活接口测试框架，目前没有UI界面只能通过代码形式实现业务接口测试
- 特别注意：
pom文件
```
        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports-java</artifactId>
            <version>3.1.5</version>
        </dependency>
```
的jar包引用，是通过这里[修改extentreports源码路径](https://github.com/rootczy/extentreports)
### 使用技术栈

- Spring Boot 
- mybatis
- testng
- Swagger2
- extentreports
- httpclient（这里抽取了一些方法比较好用）
- log4j2

##### 更新日志记录
- 2020.04.26 删除公司业务代码，精简示例代码，初始化项目
- 2020.04.28 添加docker-auto.sh脚本和Dockerfile留存
- 2020.05.27 1.修改extentreports源码，由线上引用样式改为项目本身离线样式
             2.增加定时任务执行用例
- 2020.05.28 1.增加测试报告邮件发送功能
             2.自动生成测试报告集成到项目中可以通过项目直接访问
- 2020.05.29 1.优化testng测试用例由xml文件改为BaseTestngInit工具类引入
             
###### 项目结构
![](https://upload-images.jianshu.io/upload_images/16753854-0e897d6647a42a5a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

###### 实战示例
- 通过Swagger2调取controller层示例
![](https://upload-images.jianshu.io/upload_images/16753854-e239c05f3564353b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

 ![](https://upload-images.jianshu.io/upload_images/16753854-c3e270a0b7dcb0ee.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 请求示例
![](https://upload-images.jianshu.io/upload_images/16753854-4432d0d4eb1bf723.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 响应示例
![](https://upload-images.jianshu.io/upload_images/16753854-a8f059d6b571d518.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

###### 接口测试用例通过extentreports生成测试报告
- 执行resources下的testNG.xml生成测试用例报告
示例测试报告如下：
![](https://upload-images.jianshu.io/upload_images/16753854-e0595fd9f2e982b6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 接口的并发测试
测试单元ConcurrentTestCase类是测试并发的示例
根据入参参数设置并发量，测试结果如下：
![](https://upload-images.jianshu.io/upload_images/16753854-40827876db033744.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 执行定时任务生成报测试报告通过项目访问
![](https://upload-images.jianshu.io/upload_images/16753854-c2fd17fc6c3546ad.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
实际的业务中还需根据自己的接口文档去设计测试用例。





