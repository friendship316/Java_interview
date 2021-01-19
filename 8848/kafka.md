### partion的Leader选举机制
- broker启动之后，会往ZK注册controller节点，只有最先到达的才能成功创建，那么相应的broker就成为整个集群的总控节点controller。
- controller节点会监听broker节点下的ids（即brokerID列表），当某个broker挂掉了，controller能收到watcher通知
- controller会从该broker涉及的分区partion的ISR列表中找第一个节点作为新的leader。如果正好是第一个节点挂了，会再往后找一个。
   ISR列表：①当前与ZK有联系（即活着），②并且与leader正常同步数据的节点列表
   如果ISR列表都挂了，如果配置了相应参数（具体参数名忘了），可以从replica列表中找活着的第一个。否则就只能等挂掉的重启。
