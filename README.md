# concurrency
并发

1.        <dependency>
              <groupId>org.openjdk.jol</groupId>
              <artifactId>jol-core</artifactId>
              <version>0.8</version>
          </dependency>
2.  out.println(ClassLayout.parseInstance(student).toPrintable());

3.

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
