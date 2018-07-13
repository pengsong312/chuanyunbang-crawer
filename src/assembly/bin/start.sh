#!/bin/bash
ENV=$1
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
PID_FILE=${DEPLOY_DIR}/pid;
LOCAL_IP=`hostname -I |awk '{ print $1 }'`

if [ ! -f $PID_FILE ];then
	touch ${PID_FILE};
else
	PID=$(cat ${PID_FILE});
	PID_EXIST=$(ps aux | grep $PID | grep -v 'grep');
	if [ ! -z "$PID_EXIST" ];then
		echo "agent is running,no need to start again!...";
		exit 1;
	fi	
fi
CONF_DIR=$DEPLOY_DIR/conf

LOGS_DIR=$DEPLOY_DIR/logs

UPLOAD_DIR=$DEPLOY_DIR/upload

if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi

if [ ! -d $UPLOAD_DIR ]; then
    mkdir $UPLOAD_DIR
fi

STDOUT_FILE=$LOGS_DIR/stdout.log

LIB_DIR=$DEPLOY_DIR/lib

LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "

JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server -Xmx2g -Xms2g -Xmn6g -XX:MaxMetaspaceSize=2048m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:+PrintGCDetails -Xloggc:$LOGS_DIR/gc.log"
else
    JAVA_MEM_OPTS=" -server -Xms512m -Xmx512m -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

echo -e "Starting the yzb-basic-service  Env :  $ENV"
nohup java -Dspring.profiles.active=$1 $JAVA_OPTS $JAVA_MEM_OPTS  -DLOCAL_IP=$LOCAL_IP -classpath $CONF_DIR:$LIB_JARS com.chuanyunbang.crawer.Application > $STDOUT_FILE 2>&1 &

echo "$!" > ${PID_FILE};

echo "OK!"
echo "PID: $(cat $PID_FILE)"
echo "STDOUT: $STDOUT_FILE"
echo "to show log,please try :";
