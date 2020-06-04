# kafka learning

----------- Java Setup ---------------------------------------
Single Node Multi Broker SetUp 
#Check Java Version
java -version
# Download Location
http://www.oracle.com/technetwork/java/javase/downloads/index.html
# Path 
$ cd /go/to/download/path
# Unpacking 
$ tar -zxf jdk-8u60-linux-x64.gz
#Move to opt directory 
$ su
password: (type password of root user)
$ mkdir /opt/jdk
$ mv jdk-1.8.0_60 /opt/jdk/
#Java Path
export JAVA_HOME =/usr/jdk/jdk-1.8.0_60
export PATH=$PATH:$JAVA_HOME/bin
#Apply changes in running system
$ source ~/.bashrc
#Alternative Java 
update-alternatives --install /usr/bin/java java /opt/jdk/jdk1.8.0_60/bin/java 100

--------------------- Zookeeper Framework Installation -------------------- 
http://zookeeper.apache.org/releases.html
$ cd opt/
$ tar -zxf zookeeper-3.4.6.tar.gz
$ cd zookeeper-3.4.6
$ mkdir data

#Edit file 
$ vi conf/zoo.cfg
tickTime=2000
dataDir=/path/to/zookeeper/data
clientPort=2181
initLimit=5
syncLimit=2

#Start server
$ bin/zkServer.sh start

#output
$ JMX enabled by default
$ Using config: /Users/../zookeeper-3.4.6/bin/../conf/zoo.cfg
$ Starting zookeeper ... STARTED

#StartCLI
$ bin/zkCli.sh

#Output of above command
Connecting to localhost:2181
................
................
................
Welcome to ZooKeeper!
................
................
WATCHER::
WatchedEvent state:SyncConnected type: None path:null
[zk: localhost:2181(CONNECTED) 0]

#Stop Zookeeper server
$ bin/zkServer.sh stop

------------------------------- Apache Kafka Installation ---------------------- 
