# chuanyunbang-crawer

# 工程部署方法
## 创建数据库
1. 执行database.sql 中的脚本
2. 随后执行table.sql 中的脚本

## 服务器安装maven
安装方法自行百度

## 下载依赖
1. 在本地maven仓库下新建路径xxxx/.m2/repository/com/yzb/base/yzb-base-commons
2. 下载链接:https://pan.baidu.com/s/1NnmeaGYRODylTDBkzWMAFg 并解压到上述路径下
3. 下载密码不能公开，请见谅

## 工程打包
1. 执行工程路径下的 ```make.sh```
2. cd 到bin 路径下执行 ```sh start.sh```
3. 程序启动完成 查看数据库数据