#### Java基础
> ###### HashMap
>要点：扩容过程（2倍问题）、非线程安全的体现、JDK1.8所做的优化
复习链接：[Java 8系列之重新认识HashMap](https://tech.meituan.com/java-hashmap.html)
简要总结：非线程安全主要体现在多线程put时候数据被覆盖、循环链表（JDK1.7才有这个问题）

>###### ThreadLocal
>要点：原理，与线程之间的关系，增删操作，弱引用
>复习链接：[自己的博客-图说threadLocal](https://www.jianshu.com/p/2f3d734504c5)

> ###### 线程池
>要点：基本使用，执行过程，任务流转，几种类型
>复习链接：[自己的博客-Java线程池带图源码解析](https://www.jianshu.com/p/b6c076fb6947)、[网上的博客](http://www.cnblogs.com/dolphin0520/p/3932921.html)

>###### countDownLatch
>复习链接：[countDownLatch](https://www.jianshu.com/p/7c7a5df5bda6?ref=myread)

>###### CyclicBarrier
>复习链接：[CyclicBarrier](http://www.cnblogs.com/200911/p/6060195.html)

> ###### NIO
>要点：相对于BIO的优势，基本原理及使用，应用场景（dubbo）
复习链接：自己的博客

>###### AQS
>复习链接：[AQS](https://mp.weixin.qq.com/s/-swOI_4_cxP5BBSD9wd0lA)




集合类、并发包、IO/NIO、JVM、内存模型、泛型、异常、反射


concurrenthashmap、
copyOnWrite、
CAS、AQS、虚拟机优化、nginx、tomcat、redis、mongodb

#### JVM
> ###### JVM最大线程数
> 链接：
https://yq.aliyun.com/articles/657805、https://www.cnblogs.com/princessd8251/articles/3914434.html
 [从实际案例聊聊Java应用的GC优化](https://tech.meituan.com/2017/12/29/jvm-optimize.html)
>目测理论值是（操作系统单个进程最大内存-Xmx(堆内存)-MaxPermSize（方法区）-MaxDirectMemorySize（堆外直接内存）-ReservedCodeCacheSize（代码缓存区）)/Xss(线程栈大小) 
在这个基础上，同时还受限于操作系统的max_user_processes、threads-max、pid_max、max_map_count



#### 常用框架
- 数据库引擎
- 多线程 FutureTask、aqs、信号量
- 线程池
- 工作流
- 事务
- 索引结构
- 索引种类
- 锁
- linux
- 缓存 redis，memcache
- jvm
- 协议相关 http tcp/ip
- nginx
- io/nio
- jdbc
- 设计模式
- seesion机制
- 一致性哈希
- 数据库连接池
- 分布式锁
- 联合索引作用字段
- 实现分布式锁的方式
- 消息队列
- Java core

#### 回看&博客总结
spring三级缓存解决循环依赖
