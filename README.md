### 前言
无论是自动化测试还是自动化部署，撸码肯定少不了，在GitHub记录使用的技术栈
### 进入主题

###### 使用技术
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
- 2020.05.27 修改extentreports源码，由线上引用样式改为项目本身离线样式

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

实际的业务中还需根据自己的接口文档去设计测试用例。





