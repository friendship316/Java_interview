### 存储结构
结构如下：
![rocketMq结构](https://github.com/friendship316/Java_interview/blob/main/8848/pictures/rocketMq/RocketMq%E7%BB%93%E6%9E%84.jpg)
其中ConsumerQueue等价于后面MessageQueue

### 延时队列实现原理

开源版本自定义了18个队列，根据不同的延时等级，收到消息之后改写topic，将其放入系统自定义的18个MessageQueue中,延时到时间了再放入其真正的归属的topic的队列中去。其基本原理与rabbitMq的死信队列实现延时有些类似。

### 如果要自己实现一个任意时间的延时队列该如何实现？

### 生产者负载均衡

Producer端在发送消息的时候，会先根据Topic找到指定的TopicPublishInfo，在获取了TopicPublishInfo路由信息后，RocketMQ的客户端在默认方式下selectOneMessageQueue()方法会从TopicPublishInfo中的messageQueueList中选择一个队列（MessageQueue）进行发送消息。具体的容错策略均在MQFaultStrategy这个类中定义。这里有一个sendLatencyFaultEnable开关变量，如果开启，在随机递增取模的基础上，再过滤掉not available的Broker代理。所谓的"latencyFaultTolerance"，是指对之前失败的，按一定的时间做退避。例如，如果上次请求的latency超过550Lms，就退避3000Lms；超过1000L，就退避60000L；如果关闭，采用随机递增取模的方式选择一个队列（MessageQueue）来发送消息，latencyFaultTolerance机制是实现消息发送高可用的核心关键所在。

### 消费者负载均衡原理

消费者负载均衡是在消费端（即业务代码的机器）进行的。消费者启动之后，向其订阅的topic下其中一台broker（从NameServer获取到的broker列表中的第一台）发送请求获取到同一个consumeGroup的所有consumer的ID列表，然后根据策略，计算自己应该分配到哪几个MessageQueue。

以平均分配为例：

先对Topic下的消息消费队列、消费者Id排序，然后用消息队列分配策略算法（默认为：消息队列的平均分配算法），计算出待拉取的消息队列。这里的平均分配算法，类似于分页的算法，将所有MessageQueue排好序类似于记录，将所有消费端Consumer排好序类似页数，并求出每一页需要包含的平均size和每个页面记录的范围range，最后遍历整个range而计算出当前Consumer端应该分配到的记录（这里即为：MessageQueue）。

### 拉取消息过程及流量控制

### 事务消息原理

### 事务消息half如何做到对用户（消费者）不可见的？

如果消息是half消息，将备份原消息的主题与消息消费队列，然后改变主题为RMQ_SYS_TRANS_HALF_TOPIC（系统定义的topic）。由于消费组未订阅该主题，故消费端无法消费half类型的消息，然后RocketMQ会开启一个定时任务，从Topic为RMQ_SYS_TRANS_HALF_TOPIC中拉取消息进行消费，根据生产者组获取一个服务提供者发送回查事务状态请求，根据事务状态来决定是提交或回滚消息。

### RocketMQ中的零拷贝

### RocketMQ中Netty的使用
