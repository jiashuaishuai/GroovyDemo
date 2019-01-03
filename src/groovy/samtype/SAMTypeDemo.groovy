package groovy.samtype

/**
 * 为SAM类型分配闭包
 *
 */
class SAMTypeDemo {
    static main(arg) {

        //as Type自Groovy 2.2.0以来，表达式是可选的。您可以省略它并简单地写：
        Predicate predicate = { it.contains "G" }
        assert predicate.accept("Groovy")

        Greeter greeter = { "Groovy" }
        greeter.greet()


        //还可以使用法法指针
        def filter1 = this.&doFilter
        assert filter1("Groovy")
        Predicate pFilter = this.&doFilter
        assert pFilter.accept("Groovy")

        Greeter ggreeter = GroovySystem.&getVersion
        ggreeter.greet()

        //调用接收带有闭包的sam类型的方法，可以使用闭包调用它，无需创建接口的显示实现

        assert filter(["Java", "Groovy"]) { it.contains("G") } == ["Groovy"]


    }

    static doFilter(String s) {
        s.contains("G")
    }

    static <T> List<T> filter(List<T> source, Predicate<T> predicate) {
        source.findAll {
            predicate.accept(it)
        }
    }
}

interface Predicate<T> {
    boolean accept(T obj)
}

abstract class Greeter {
    abstract String getName()

    void greet() {
        println "Hello ,$name"
    }
}