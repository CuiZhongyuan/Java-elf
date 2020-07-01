
### 理论知识不能丢

> 参考搜狗测试

- 什么是框架

总结：测试框架是测试开发过程中提取特定领域测试方法共性部分形成的体系结构，并不是一个现成可用的系统，
需要测试工程师在它基础上结合自己的测试对象转换为自己的测试用例。

- 测试框架四要素

1.测试目标

一般是一个函数，一个对象或者一组相关的对象集。测试人员在测试前需要对测试目标有充分的了解，明确测试的预期结果。

2.测试集

这一组测试用例服务于相同的测试目标，保证测试的有序维护。

3.测试执行

测试集按序执行

4.断言

验证被测程序在测试中的行为或状态是否符合预期。

- 测试框架的意义

提供工作效率：在测试框架基础上重用测试设计原则和测试经验，调整部分内容便可满足需求，可提高测试用例设计开发质量，降低成本，缩短时间。

- 哪些项目适用测试框架

1.逻辑复杂且封装性好

首先如果代码逻辑很简单，单测也就没有太大的必要了；其次如果代码没有进行基本的封装或者封装过度，层次结构不清晰，那在测试过程中也是举步维艰。

2.复用性高

被测试模块的复用性高，搭建的测试框架才是有价值有收益的，毕竟投入成本很高；其次在测试中还可以抽象出可重复使用的公共方法，测试代码的复用性也高。

- 基本执行过程

一般测试用例执行过程分为四个步骤：

1.SetUp：准备阶段

每个测试用例执行前的准备阶段，部署测试环境比如对象的初始化等。

2.Run：测试执行

执行测试用例。

3.Verify：测试验证

验证测试用例的结果是否符合预期。

4.TearDown：清理环境

清理该条测试用例执行中产生的环境，比如申请空间的释放，还原测试环境，保证对其他的测试用例无连带影响。

- 结果度量

结果度量是对测试过程进行量化分析，采集一定的指标来衡量软件或工作的质量，为测试工作的改善、各类报告提供有力的支持。
因为结果度量是对测试过程进行量化分析，所以必须保证度量的指标是具体可衡量的。

- 如何进行结果度量

从项目、工程、代码层面三个方向进行分析

     1.项目：通过结果度量指导项目的分析和改进，提升项目口碑，降低用户负面反馈，评估项目成本，对后续项目的成本和进度的预估提供指导。
     2.工程：提升工程效率、保证工程质量，例如对测试的人力成本、白盒测试发现bug数占总bug数、白盒测试在版本迭代间的时间占比的评估等。
     3.代码用例层：保证测试的有效性、完整性，体现在通过对代码覆盖率、用例执行效率等的评估来保证测试的完整有效。

- 总结一下

在进行白盒测试的结果度量时我们可对项目纵向分析，从项目、工程、代码等层面进行分析设定度量指标。
同时需要说明的是针对不同项目，进行结果度量的指标也不同，应根据项目实际情况设置度量指标。
例如对于输入法等长期运行的软件应对响应时间、内存占用等性能指标进行度量。

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

也可以直接下载修改源码后的extentreports依赖包,放到maven的依赖repository.com.aventstack下即可
```
链接： 
提取码：hosn 
```

### 使用技术栈

- Spring Boot 
- mybatis
- testng
- Swagger2
- extentreports
- httpclient（这里抽取了一些方法比较好用）
- log4j2

##### 更新日志记录
- 2020.04.26 初始化项目
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
- 调用工具类BaseTestngInit内部方法baseTestngInitCode即可生成测试报告（前提需要把测试用例引入到setTestClasses中，具体见示例）;
示例测试报告如下：
![](https://upload-images.jianshu.io/upload_images/16753854-e0595fd9f2e982b6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 接口的并发测试
测试单元ConcurrentTestCase类是测试并发的示例
根据入参参数设置并发量，测试结果如下：
![](https://upload-images.jianshu.io/upload_images/16753854-40827876db033744.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 执行定时任务生成报测试报告通过项目访问
![](https://upload-images.jianshu.io/upload_images/16753854-c2fd17fc6c3546ad.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
实际的业务中还需根据自己的接口文档去设计测试用例。

- 增加测试报告邮件通知，SendEmail邮件类，通过配置mail配置收件人和抄送人邮件地址即可

- 执行以下命令生成代码覆盖率统计报表

```
mvn clean verify -DfailIfNoTests=false
```
![](https://upload-images.jianshu.io/upload_images/16753854-147dbb2b1173cc8c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)





