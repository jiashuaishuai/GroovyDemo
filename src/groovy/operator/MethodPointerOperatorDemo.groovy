package groovy.operator

/**
 * 方法指针operator（.&）用于存储对变量中方法的引用，以便稍后调用它：
 */
class MethodPointerOperatorDemo {
    def static main(arg) {
        def str = "example of method reference"
        //我们在名为变量toUpperCase的str实例中存储对该方法的引用fun
        def fun = str.&toUpperCase
        //稍后调用
        assert fun() == str.toUpperCase()


        //方法指针有点：这种方法指针类型是一个Closure，因此在任何地方都可以使用闭包，特别是根据策略模式需要转换现有方法

        //获取本类中的方法指针赋给action
        def action = this.&describe
        //数组
        def list = [new MethodPerson(name: "乌尔奇奥拉", age: 234), new MethodPerson(name: "一级顾", age: 18)]

        println transform(list, action)


        //方法指针由接接收器和方法名绑定，参数在运行时解析，这意味着如果有个相同方法名，则方法指针语法没有区别，只有在运行时回调用相应参数的方法解析
        def doSomethingAction = this.&doSomething
        println doSomethingAction("something action")
        println doSomethingAction(5)


    }

    //该transform方法获取列表中的每个元素并调用action它们上的闭包，返回一个新列表
    static List transform(List elements, Closure action) {
        def result = []

        elements.each {
            result << action(it)
        }

        result

    }

    //根据person返回信息String
    static String describe(MethodPerson person) {
        "$person.name  is $person.age"
    }

    static class MethodPerson {
        def name
        def age
    }


    static doSomething(String str) {
        str.toUpperCase()
    }

    static doSomething(int i) {
        i * 2
    }
}
