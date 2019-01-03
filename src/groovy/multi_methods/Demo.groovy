package groovy.multi_methods

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class Demo {
    static main(arg) {

        def demo = new Demo()
        Object o = "Groovy"//转为String类型，java中则为Object
        println demo.method(o)


        new File("/Users/shuaijia/Pictures/pathDemo").eachLine("UTF-8") {
            println it
        }
//
//        CountDownLatch called = new CountDownLatch(1)
//        Timer timer = new Timer()
//        timer.schedule(new TimerTask() {
//            @Override
//            void run() {
//
//                called.countDown()
//            }
//        }, 0)
//
//        assert called.await(10, TimeUnit.SECONDS)


        //java写法
//        DemoJava.Xc xc = new DemoJava().new DemoJava.Xc();
        new Xc(new Demo())


        int l = 1

        demo.m(l)//所有原始引用都使用它们的包装类


        l.compareTo()

        String


    }

    void m(long l) {
        println l
    }

    void m(Integer l) {
        println l
    }

    class Xc {}

    int method(String arg) {
        return 1
    }

    int method(Object arg) {
        return 2
    }
}
