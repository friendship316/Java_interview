### partition的Leader选举机制
- broker启动之后，会往ZK注册controller节点，只有最先到达的才能成功创建，那么相应的broker就成为整个集群的总控节点controller。
- controller节点会监听broker节点下的ids（即brokerID列表），当某个broker挂掉了，controller能收到watcher通知
- controller会从该broker涉及的分区partition的ISR列表中找第一个节点作为新的leader。如果正好是第一个节点挂了，会再往后找一个。

   ISR列表：
   - 当前与ZK有联系（即活着）；
   - 并且与leader正常同步数据的节点列表
   
   如果ISR列表都挂了，如果配置了相应参数（具体参数名忘了），可以从replica列表（即副本列表）中找活着的第一个。否则就只能等挂掉的重启。

### offset提交保存机制
每个consumer会将自己消费分区的offset提交保存到broker的_consumer_offset_xx中。_consumer_offset_xx是kafka创建的内部分区，默认是50个。
根据消费者组的Id进行hash然后对_consumer_offset_xx的个数取模，就知道应该放在哪个文件中。所以offset是以consumerGroup为单位存储的。
_consumer_offset_xx中存放的是键值对，键是consumerGroupId+topic+消费的分区号，值是offset。

### Rebalance机制
#### 策略
- range模式 大概和rockertMQ默认的消费端负载均衡类似，就是假设有0-9十个partition，3个consumer，每个consumer各自分配一段分区，可能是第一个consumer是0-3号partition，第二个consumer是4-6，第三个consumer是7-9;
- round-robin 轮询
- sticky 初始的时候跟轮询类似，但是后面发生变动重新分配的时候，需要尽可能保持原有分配不变，并保持重新分配尽可能均匀

#### 过程
- 先选出服务端组长和消费端组长，其中服务端组长由某分区某个组保存offset分区的leader那台机器担任（选择方式没太明白），消费端组长由服务端组长指定
- 消费端组长指定负载均衡策略之后同步给服务端组长
- 服务端组长会在与消费者心跳通讯的时候将变化的负载均衡策略同步过去

### Kafka与RocketMq的区别
#### 功能区别
- RocketMq支持延时队列，kafka不支持
- RocketMq没有主动的消息批量压缩，kafka有
#### 设计区别
- 整体高可用架构类似。但是RocketMq以broker分主从，Kafka以partition分主从。
- rocketMq的数据存储与kafka有较大不同。kafka针对不同的topic的每个partition都有单独的commitLog，但是rocketMq一个broker只有一个commitLog。两者都存在对应的commitLog的多个索引。
