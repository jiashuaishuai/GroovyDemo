package groovy.multi_methods;

public class DemoJava {
    public static void main(String[] arg) {
        DemoJava demoJava = new DemoJava();
        Object o = "Java";
        System.out.println(demoJava.method(o));

        Xc xc = new DemoJava().new Xc();
        int i = 1;
        demoJava.m(i);//扩展优先于取消装箱
    }

    void m(long l) {
        System.out.println(l);
    }

    void m(Integer l) {
        System.out.println(l);
    }


    public class Xc {
    }

    int method(String arg) {
        return 1;
    }

    int method(Object arg) {
        return 2;
    }
}
