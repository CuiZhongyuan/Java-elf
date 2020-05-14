# 基础镜像是docker仓库的java:8（JDK8）
FROM java:8 
#  作者签名
MAINTAINER czy czy725@yeat.com
#  挂载宿主机jar包到镜像  /platform-pay-1.0.0.jar  和 下个指令对应即可，命名并非一定要和jar名一样，为了能够识别
copy test-1.0-SNAPSHOT.jar /test-1.0-SNAPSHOT.jar
#  执行 java -jar 命令，启动容器跟随启动
CMD java -jar /test-1.0-SNAPSHOT.jar
#  设置对外端口为 8090
 EXPOSE 8090
