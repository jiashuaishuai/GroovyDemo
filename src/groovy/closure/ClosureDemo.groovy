package groovy.closure

class ClosureDemo {

    static main(args) {

        def code = { 123 }
        assert code() == 123


        def isOdd = { int i -> i % 2 != 0 }
        assert isOdd(3)
        assert !isOdd(2)


        //隐含参数
        def closureWithOneArg = { it.toUpperCase() }
        assert closureWithOneArg("aa") == "AA"


        def closureWithOneArgAndExplicitType = {
            String arg -> arg.toUpperCase()
        }
        assert closureWithOneArgAndExplicitType("bb") == "BB"

        //参数默认值
        def closureWithTwoArgAndDefaultValue = {
            a, b = 2 -> a + b
        }

        assert closureWithTwoArgAndDefaultValue(1) == 3
        assert closureWithTwoArgAndDefaultValue(1, 4) == 5


        //不带参数的闭包
        def magicNumber = {
            -> 42
        }
// MissingMethodException  No signature of method: groovy.closure.ClosureDemo$_main_closure6.call() is applicable for argument types: (Integer) values: [1]
//Possible solutions: doCall(), any(), any(), any(groovy.lang.Closure), each(groovy.lang.Closure), tap(groovy.lang.Closure)
//        assert magicNumber(1)==42


        //可变参数，和java一样
        def concat1 = {
            String... arg -> arg.join()
        }

        assert concat1("a", "b", "c") == "abc"

        def concat2 = {
            String[] arg -> arg.join()
        }

        assert concat2("d", "e", "f") == "def"

        def concat3 = {
                //可以不定义类型，
            n, ... arg -> arg.join() * n
        }

        assert concat3(2, "c", "a") == "caca"

        //this 闭包定义所在类  getThisObject返回定义闭包的封闭类，等价于this 然后this对应最近的外类，而不是封闭的封闭！

        def whatIsThisObject = {
            getThisObject()
        }
        assert this == whatIsThisObject()

        def whatIsThis = {
            this
        }
        assert whatIsThisObject() == whatIsThis()


        def nestedClosures = {
            def cl = { this }
            cl()
        }

        assert nestedClosures() == whatIsThis()


        def inner = new Inner()
//        assert inner.cl() == whatIsThis
        assert inner.dump() == inner


        def person = new Person(nam: "小花", age: 1024)

        person.dump()
        println person.toString()
        assert person.dump() == "Person{nam=小花, age=1024}"


        //owner 闭包定义所在对象可以是类或者闭包，与this相似，但是有一个细微区别：它将返回直接封闭对象，无论是闭包还是类：

        def whatIsOwnerMethod = {
            getOwner()
        }

        assert whatIsOwnerMethod() == this

//        NestedClosures nestedClosures1 = new NestedClosures()
        NestedClosures.run()

        def nestedClosures1 = {
            //多了一层闭包就返回闭包
            def cl = { owner }
            cl()
        }

        assert nestedClosures1 == nestedClosures1()


        //delegate 闭包中引用的第三方对象，其中每当未定义消息的接收者时解析方法调用或属性

        def delegateCl = {
            getDelegate()
        }
        def delegateCl2 = {

            delegate

        }

        assert delegateCl2() == delegateCl()
        assert delegateCl() == this



        def enclosed = {
            def cl = {
                it? delegate:owner
            }
            cl()
        }

        //特别是在嵌套闭包的情况下 delegate 将对应于 owner
        assert enclosed(true) == enclosed(false)


    }


    @Override
    String toString() {
        return "ClosureDemo-------"
    }

    static class Person {
        def nam
        def age


        def dump() {

            def cl = {
                //静态内部类，隐匿持有外部类,顾这里的this需要指定
//                def msg = Person.this.toString()
                //返回的是ClosureDemo
//                def msg  = this.toString()
                //owner在闭包中将返回内部类，而不是顶级类
                def msg = owner.toString()
                println msg
                msg
            }
            cl()
        }

        @Override
        String toString() {
            return "Person{" +
                    "nam=" + nam +
                    ", age=" + age +
                    '}'
        }
    }
}

//	如果闭包是在内部类中定义的 this在闭包中将返回内部类，而不是顶级类
class Inner {
    def dump() {
        def cl = {
            //如果该类为静态内部类 输出 groovy.closure.ClosureDemo$Inner 静态内部类，隐匿持有外部类
            //正常输出 groovy.closure.Inner
            println this.class.name
            this
        }
        cl()
    }

    @Override
    String toString() {
        return "嘻嘻嘻嘻嘻"
    }
}

class NestedClosures {
    static void run() {
        def nestedClosures = {
            def cl = { owner }
            cl()
        }
        assert nestedClosures() == nestedClosures
    }
}



