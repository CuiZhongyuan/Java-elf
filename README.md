### 前言
无论是自动化测试还是自动化部署，撸码肯定少不了，所以下面的基于java语言的接口自动化测试，要想在业务上实现接口自动化，前提是要有一定的java基础。
如果没有java基础，也没关系。这里小编也为大家提供了一套java基础精讲视频（虽然年代有点久2017，但是讲解内容绝对干货，小编看了很多的基础视频唯有这一套讲解到位）由于视频较大，放到了某盘上，关注下方公众号回复关键字【java】即可获取。

### 进入主题
###### 使用技术
- Spring Boot 
- mybatis
- testng
- Swagger2
- extentreports
- httpclient（这里抽取了一些方法非常好用）
- log4j2
###### 项目结构
![](https://upload-images.jianshu.io/upload_images/16753854-f04c184aa58cf65f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

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

好了这里就结束了，简单的写了两个示例，在实际的业务中还需根据自己的接口文档去设计测试用例。


---
*   *更多测试技术分享、学习资源以及一些其他福利可关注公众号：【Coding测试】获取：*

![Coding测试](https://upload-images.jianshu.io/upload_images/16753854-6932e76d9d2f430b.png?imageMogr2/auto-orient/strip|imageView2/2/w/239)
