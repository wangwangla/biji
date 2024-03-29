## 云服务器配置
#### jdk的安装
(1)查看软件包列表

```shell
yum search java | grep -i --color jdk
```

（2）选择版本进行安装

```shell
yum install -y java-1.8.0-openjdk java-1.8.0-openjdk-devel
```

或者如下命令，安装jdk1.8.0的所有文件

```shell
yum install -y java-1.8.0-openjdk*
```


（3）查看是否安装成功

```shell
java -version
```


（4）环境变量配置

```shell
set java environment  
JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.181-3.b13.el7_5.x86_64
PATH=$PATH:$JAVA_HOME/bin  
CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar  
export JAVA_HOME  CLASSPATH  PATH 
```


（5）立即生效

```shell
source  /etc/profile
```


（6）查看变量

```shell
echo $JAVA_HOME
echo $PATH
echo $CLASSPATH
```

​	


#### 米特网注册账号
https://www.mite5.com


#### nginx的安装
	更新软件
	yum update
	安装必须工具
	yum -y install make zlib zlib-devel gcc-c++ libtool openssl openssl-devel
	安装pcre  
	wget http://downloads.sourceforge.net/project/pcre/pcre/8.35/pcre-8.35.tar.gz	
	解压安装包：tar zxvf pcre-8.35.tar.gz
	进入安装包目录：cd pcre-8.35
	编译：./configure
	安装：make && make install
	
	下载nginx
	cd /usr/local/
	wget http://nginx.org/download/nginx-1.8.0.tar.gz
	tar -zxvf nginx-1.8.0.tar.gz
	cd nginx-1.8.0  
	安装nginx指定目录
	./configure --prefix=/usr/local/nginx 
	make
	make install
	
	--with-pcre=/usr/local/pcre-8.36 指的是pcre-8.36 的源码路径。
	--with-zlib=/usr/local/zlib-1.2.8 指的是zlib-1.2.8 的源码路径。

启动

```shell
systemctl restart docker.service
```

 检查是否启动成功：

​	打开浏览器访问此机器的 IP，如果浏览器出现 Welcome to nginx! 则表示 Nginx 已经安装并运行成功。

部分命令如下：
	重启：

```shelll
/usr/local/nginx/sbin/nginx –s reload
```

​	停止：

```shell
/usr/local/nginx/sbin/nginx –s stop
```

	测试配置文件是否正常：
	$ /usr/local/nginx/sbin/nginx –t
	
	强制关闭：
	$ pkill nginx


### Redis安装
	yum install redis


### 数据库安装
	- 查看本身是否已经安装
	rpm -qa | grep mariadb
	- 卸载
	rpm -e --nodeps  上面查出来的名字
	- 设置yum repository
	
	设置rmp 
	rpm -Uvh https://dl.fedoraproject.org/pub/epel/epel-release-latest-6.noarch.rpm
	
	下载webtatic-release RPM
	rpm -Uvh https://mirror.webtatic.com/yum/el6/latest.rpm
	
	安装
	yum install mysql55w mysql55w-server
	
	service mysqld start 开启服务
	
	mysqladmin -u root password 'root'设置密码
	
	mysql -u root -p  登录



#### jboot项目构建

​	在服务器和客户端的目录下执行下面命令
	 mvn clean 
	 mvn clean package appassembler:generate-daemons

	jboot-admin\jboot-admin\target目录下的generated-resources复制到服务器
	在服务器上给执行权限，然后启动服务器

​	在服务器上将他们授予执行权限，然后就可以进行执行了，在执行的过程中，可以查看日志，在日志目录下，查看根目录下的启动日志。

犯得错误：在根目录下执行名，这个要在服务端程序和客户端程序中都执行，然后分别启动即可。

#### 服务器配置

```shell
数据库
consul
redis	 	 
```


### 启动过程
- 启动nginx
	./nginx
- 启动consul
	./consul agent -dev 或者consul agent -dev
- 启动Redis
	开机找服务开启
	./redis-server

jboot-admin下Application
jboot-admin-provider下Application

运行run方法。





-------

### docker发布项目

首先我们需要安装redis、数据库、consul、java镜像。

#### docker安装

```shell
yum -y install docker-io
yum list installed | grep docker
```

镜像加速

```shell
{
  "registry-mirrors": ["https://wghlmi3i.mirror.aliyuncs.com"]
}
或者
{
  "registry-mirrors": ["https://registry.docker-cn.com"]
}
```

启动服务

```shel
systemctl start docker.service
```

已经安装完成，下来案例实验。

- 启动docker

  ```shell
  [root@VM_0_15_centos ~]# systemctl start docker
  ```

  

- 停止

  ```shell
  systemctl stop docker
  ```

  

（1）启动一个hello-world

```shell
 docker run hello-world 运行容器，如果没有，就会自己去下载
```

（2）安装mysql

- 速索mysql

```shell
docker search mysql
```

- pull镜像

```shell
 docker pull  docker.io/mysql
```

- 启动它

```shell
  sudo docker run --name kw-mysql -e MYSQL_ROOT_PASSWORD=123456 -p 3307:3306 -d mysql
```

- –name：给新创建的容器命名，此处命名为`pwc-mysql`
- -e：配置信息，此处配置`mysql`的`root用户`的登陆密码
- -p：端口映射，此处映射`主机3306端口`到`容器pwc-mysql的3306端口`
- -d：成功启动容器后输出容器的完整ID，例如上图 `73f8811f669ee...`
- 最后一个`mysql`指的是`mysql镜像名字`

远程连接

```shell
mysql> CREATE USER 'kangwang'@'%' IDENTIFIED BY '123456';
Query OK, 0 rows affected (0.00 sec)
mysql> GRANT ALL ON *.* TO 'kangwang'@'%';
Query OK, 0 rows affected (0.01 sec)
```

cmd中输入

```shell
mysql -h118.24.235.237 -p3307 -ukangwang -p123456
```

（3）安装redis

查询Redis

```shell
docker search redis
```

下载redis

```shell
 docker pull docker.io/redis
```

启动redis

```shell
docker run --name kw-redis -p 6379:6379 -d redis:latest redis-server
```

使用工具连接数据库。



补充：

​	使用makefile构建redis

```shell
FROM 
```



（3）docker安装consul

查找镜像

```shell
docker search consul
```

下载镜像

```shell
docker pull consul
```

启动

```
docker run --name consuls -d -p 8500:8500 -p 8300:8300 -p 8301:8301 -p 8302:8302 -p 8600:8600 consul:1.4.4 agent -server -bootstrap-expect 2 -ui -bind=0.0.0.0 -client=0.0.0.0
```

注意端口占用。

#### redis安装

