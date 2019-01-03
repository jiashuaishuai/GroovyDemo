package groovy.closure

class DelegateDemo {
    static main(a) {


        def upperCasedName = {
            //names 也可以直接用  不引用闭包的词法范围中的变量
            println names
            delegate.names.toUpperCase()
        }

        def p = new Person(names: "Person", test: {
            println("new Person.test ex $it")
        })
        def t = new Thing(names: "Thing", test: {
            println("new Thing.test ex $it")
        })


        upperCasedName.delegate = p
        assert upperCasedName() == "PERSON"
        upperCasedName.delegate = t
        assert upperCasedName() == "THING"


        def delegateTest = {
            test.call("delegateTest 委托")
        }

        delegateTest.delegate = p
        delegateTest()
        delegateTest.delegate = t
        delegateTest()

//        delegateTest.resolveStrategy=Closure.TO_SELF

    }
}

class Person {
    def names
    def test
}

class Thing {
    def names
    def test
}
