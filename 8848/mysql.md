### 主从复制
#### 传统bin-log主从集群方式
- 开启过程：
>1、指定主库和从库的serverId  
2、主库配置文件中开启binlog（还可以指定需要或者不需要同步的库），并在重启后开启replication slave的权限  
3、从库配置文件中开启binlog和relay-log（还可以指定需要或者不需要同步的库），并在启动后填写主库的用户名密码等数据。
- 开启状态：
>1、主从复制开启后，show master status可以查看主库的同步状态，show slave status可以查看从库的同步状态  
2、从库会显示IO thread和Sql thread是running状态，其中IO thread是接收主库传来的binlog数据写入relay-log，Sql thread是将relay-log数据转成SQL写入从库。  
3、可以通过观察从库的Master_Log_File以及Read_Master_Log_Pos是否与主库一致来判断是否搭建成功
#### 5.6引入的GTID集群方式
