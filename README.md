# 项目文件路径说明    
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─interfaceproject
│  │  │          │  Application.java  # 启动类
│  │  │          │  
│  │  │          ├─controller         # 控制层，单接口和多接口互调示例
│  │  │          │      ParaPost.java
│  │  │          │      PostCase.java
│  │  │          │      
│  │  │          ├─entry              # 实体类user
│  │  │          │      User.java
│  │  │          │      
│  │  │          ├─httpclient         # httpclient 旧方法和新抽取方法，保留记录
│  │  │          │      HttpclinetCaseOld.java
│  │  │          │      PostJsonGeneralCase.java
│  │  │          │      
│  │  │          ├─listener           # extentreports 报告模板
│  │  │          │      ExtentTestNGIReporterListener.java
│  │  │          │      
│  │  │          ├─swagger2           # swagger2配置文件
│  │  │          │      Swagger2Config.java
│  │  │          │      
│  │  │          ├─testng             # testng  执行顺序示例
│  │  │          │      TestNgOrdrTest.java
│  │  │          │      
│  │  │          └─utils              # HttpClient工具类抽取
│  │  │                  HxHttpClient.java
│  │  │                  HxHttpClientPool.java
│  │  │                  HxHttpClientResponseData.java
│  │  │                  HxHttpClientResponseErrorData.java
│  │  │                  HxHttpClientResponseSuccessData.java
│  │  │                  JsonUtils.java
│  │  │                  
│  │  └─resources                     # 配置文件
│  │      │  application.yml 
│  │      │  application-dev.yml 
│  │      │  log4j2.xml               # 输出日志模板
│  │      │  testNG.xml               # 执行testNG.xml 生成测试报告，生成报告在testoutput下
│  │      ├─sample                    # moco模拟数据
│  │      │      have-paramaters-get.json
│  │      │      have-paramaters-post-json.json
│  │      │      have-paramaters-post.json
│  │      │      json-paramaters-havecookies-post-2.json
│  │      │      json-paramaters-havecookies-post.json
│  │      │      json-paramaters-haveheaders-post.json
│  │      │      moco-runner-0.11.0-standalone.jar
│  │      │      no-paramaters-get-302.json
│  │      │      no-paramaters-get-responsehavecookies.json
│  │      │      no-paramaters-get1.json
│  │      │      no-paramaters-get2.json
│  │      │      no-paramaters-post.json
│  │      │      noparamaters-havecookies-get.json
│  │      │      
│  │      └─static                     # 静态文件
│  │              test.jpg
│  │              
│  └─test                              # 单元测试类
│      └─java
│          └─testcase
│                  PostListCase.java
│                  
│              
└─testoutput                           # 输出测试报告
│        index.html                
│                  
│              
└─Dockerfile                          # docker构建项目镜像
       
# 示例说明 

- testng生成报告示例
            