echo "老铁我们开始吧！！！"
#操作/项目路径(Dockerfile存放的路径)
BASE_PATH=/work/dockerimage
# 源jar路径  
SOURCE_PATH=/work/dockerimage/target
# 项目运行jar路径  
PROJECT_PATH=/work/dockerimage/project
# 项目备份jar路径  
BACKUP_PATH=/work/dockerimage/backup
#docker 镜像/容器名字或者jar名字 这里都命名为这个
CONTAINER_NAME=test
SERVER_NAME=test-1.0-SNAPSHOT
#端口映射
MAIN_PORT=8090
SERVER_PORT=8090
#容器id
CID=$(docker ps -a | grep "$CONTAINER_NAME" | awk '{print $1}')
#镜像id
IID=$(docker images | grep "$SERVER_NAME" | awk '{print $3}')
DATE=`date +%Y%m%d%H%M`
# 最新构建代码移动到项目环境
function transfer(){
    echo "新构建代码$SOURCE_PATH/$SERVER_NAME.jar 移至$PROJECT_PATH ...."
        cp $SOURCE_PATH/$SERVER_NAME.jar $PROJECT_PATH
        cp $SOURCE_PATH/Dockerfile $PROJECT_PATH
    echo "迁移完成"
}
# 备份
function backup(){
	if [ -f "$SOURCE_PATH/$SERVER_NAME.jar" ]; then
    	echo "$SERVER_NAME.jar 备份..."
        	cp $SOURCE_PATH/$SERVER_NAME.jar $BACKUP_PATH/$SERVER_NAME-$DATE.jar
        echo "备份 $SERVER_NAME.jar 完成"
    else
    	echo "$SOURCE_PATH/$SERVER_NAME.jar不存在，跳过备份"
    fi
}
# 构建docker镜像
function build(){
	if [ -n "$IID" ]; then
		echo "存在$SERVER_NAME镜像，IID=$IID，开始删除镜像并重新构建镜像$SERVER_NAME"
		docker rmi -f $SERVER_NAME
	else
		echo "不存在$SERVER_NAME镜像，开始构建镜像"
			
	fi
	#构建
	cd $PROJECT_PATH
	docker build --no-cache=true -t $SERVER_NAME .
}



GIT_USERNAME=15238305317
GIT_PWD=czy
GIT_PROJECT_PATH=/work/dockerimage/workspace/open-platform

# 运行docker容器
function run(){

		#自动更新git项目 expect插件安装  yum -y install expect
		echo "============拉取代码==========="
		cd $GIT_PROJECT_PATH
		expect -c "spawn git pull origin; expect \"*Username*\" { send \"${GIT_USERNAME}\n\"; exp_continue } \"*Password*\" { send \"${GIT_PWD}\n\" }; interact"

		echo "============打包tools==========="
		cd $GIT_PROJECT_PATH/platform-tools/
		mvn install

		echo "============打包id-server==========="
		cd $GIT_PROJECT_PATH/platform-id-server/
		mvn install

		echo "============打包entity-server==========="
		cd $GIT_PROJECT_PATH/platform-entity-server/
		mvn install

		echo "============打包pay==========="
		cd $GIT_PROJECT_PATH/platform-pay/
		mvn install

		echo "============复制pay==========="
		cp -rf target/$SERVER_NAME.jar $SOURCE_PATH



		

		#备份镜像
	 	backup
	 	#迁移(代码构建产物到运行环境)
		transfer
		#构建镜像
		build
		#删除历史缓存镜像
		#echo "删除历史缓存镜像"
		#docker images|grep none|awk '{print $3 }'|xargs docker rmi

		if [ -n "$CID" ]; then
			echo "存在$CONTAINER_NAME容器，CID=$CID,停止docker：$CONTAINER_NAME容器 ..."
				docker stop $CONTAINER_NAME
			echo "删除$CONTAINER_NAME容器"
			    docker rm $CONTAINER_NAME 
			echo "$CONTAINER_NAME容器清除完成"
		else
			echo "不存在$CONTAINER_NAME容器，无须清理容器..."
		#		docker run --name $CONTAINER_NAME -v $BASE_PATH:$BASE_PATH -d -p $MAIN_PORT:$SERVER_PORT $SERVER_NAME
		#	echo "$SERVER_NAME容器创建完成"
		fi
				
		echo "开始创建镜像$SERVER_NAME的容器$CONTAINER_NAME，端口映射$MAIN_PORT>>>$SERVER_PORT"
		docker run --name $CONTAINER_NAME -v $BASE_PATH:$BASE_PATH -d -p $MAIN_PORT:$SERVER_PORT --net=host $SERVER_NAME		
		echo "$CONTAINER_NAME容器创建结束"
		docker ps
		#输出10分钟内的启动日志
		docker logs --since 10m $CONTAINER_NAME
} 
#入口
run 
