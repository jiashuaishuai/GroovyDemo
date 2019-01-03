package groovy.trait

//trait 动态代码，methodMissing动态方法
class TraitDemo {
    static main(arg) {

        def d = new Duck()
        assert d.speak() == "Quack!"

        def c = new C()

        println c.extra()

    }

}


trait SpeakingDuck {
    String speak() {
        quack()
    }
}

class Duck implements SpeakingDuck {
    String methodMissing(String name, args) {
        "${name.capitalize()}!"
    }
}

trait A{
    String extra(){"A"}
}

trait B{
    String extra(){"B"}
}
class C implements B,A{

}