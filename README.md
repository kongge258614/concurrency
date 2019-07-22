# concurrency
并发

1.        <dependency>
              <groupId>org.openjdk.jol</groupId>
              <artifactId>jol-core</artifactId>
              <version>0.8</version>
          </dependency>
2.  out.println(ClassLayout.parseInstance(student).toPrintable());

3. -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0   开启偏向锁，设置延迟偏向时间为0
    -XX:+UseBiasedLocking：启用偏向锁
    -XX:-UseBiasedLocking：禁用偏向锁（一般只有在高度并发的情况下才有明显的优化效果）
    -XX:BiasedLockingStartupDelay=0：系统启动后马上启用偏向锁（由于系统启动或 JVM 默认并非马上启用的）

4.  对象头为什么是12B？这个12B当中分别存储的是什么呢？（不同位数的VM对象头的长度不一 样，这里指的是64bit的vm）
    关于java对象头的一些专业术语 http://openjdk.java.net/groups/hotspot/docs/HotSpotGlossary.html 
    首先引用openjdk文档当中对对象头的解释 
     object header 
    Common structure at the beginning of every GC-managed heap object. (Every oop points to an object header.) Includes fundamental information about the heap object's layout, type, GC state, synchronization state, and identity hash code. Consists of two words. In arrays it is immediately followed by a length field. Note that both Java objects and VM-internal objects have a common object header format 
    上述引用中提到一个java对象头包含了2个word，并且好包含了堆对象的布局、类型、GC状态、同步状态和标识哈 希码，具体怎么包含的呢？又是哪两个word呢？ 
     mark word 
    The first word of every object header. Usually a set of bitfields including synchronization state and identity hash code. May also be a pointer (with characteristic low bit encoding) to synchronization related information. During GC, may contain GC state bits. 
    mark word为第一个word根据文档可以知他里面包含了锁的信息，hashcode，gc信息等等，第二个word是什么 呢？
     klass pointer 
     The second word of every object header. Points to another object (a metaobject) which describes the layout and behavior of the original object. For Java objects, the "klass" contains a C++ style "vtable". 
     klass word为对象头的第二个word主要指向对象的元数据。


5. 偏向锁标识：0 非偏向锁   1 偏向锁
6. 锁标识：01 无锁     00 轻量级锁

7. 在jvm层面上，锁都是从偏向锁开始申请，如果申请不到变成轻量级锁，轻量级锁自旋失败升级为重量级锁，重量级锁调用的是os级别的线程互斥。

8. 加锁的原理也是采用CAS操作，CAS（V,E,N）,当且仅当E=V时，N才更新到V中，E是期望值，N是更新到V的值。java.util.concurrent包下面的所有加锁方式都是这种实现。

   悲观锁：悲观思想，认为写操作频繁，所以每次读写都会上锁。sycchronized就是悲观锁的一种实现，同时也是一种重量级锁。

   乐观锁：乐观思想，认为读多写少（并发写的可能性低），所以不上锁。每次更新前读取版本号，并比较版本号，如果一致则认为没有其他线程的写操作而完成更新，若版本号不一致则放弃此次更新，并重复此次更新（读版本号，比较，更新）操作。
   Java的乐观锁主要由CAS实现，CAS（V,A,B）,V--内存值，A--预期值，B--更新值。
   
   偏向锁：jvm会偏向第一个获取锁的线程，如果在运行过程中，同步锁只有一个线程在访问，不存在多线程竞争的情况，则线程不需要触发同步，这种情况下，就会给线程加一个偏向锁。当只有一个线程多次重复抢占同一资源时，即使是轻量级锁每次也至少需要两次cas操作(加锁和释放锁)
   如果在运行过程中，遇到了其他线程抢占锁，则持有偏向锁的线程会被挂起，jvm会消除它身上的偏向锁，将锁升级为轻量级锁。
