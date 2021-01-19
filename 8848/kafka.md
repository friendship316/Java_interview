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
