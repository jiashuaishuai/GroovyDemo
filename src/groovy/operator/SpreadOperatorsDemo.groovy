package groovy.operator


/**
 * 表达式cars*.make相当于cars.collect{ it.make }。
 * 当引用的属性不是包含列表的属性时，Groovy的GPath表示法允许快捷方式，在这种情况下，它会自动传播。
 * 在前面提到的情况下，cars.make可以使用表达式，但通常建议保留显式扩展点运算符。
 *
 * spread运算符是null安全的，这意味着如果集合的元素为null，它将返回null而不是抛出NullPointerException：
 *
 *
 *
 */
class SpreadOperatorsDemo {
    def static main(arg) {
        def cars = [new Car(make: 'Peugeot', model: '508'),
                    new Car(make: 'Renault', model: 'Clio')]
        def makes = cars*.make
        println makes

        Compositeobject components = new Compositeobject()

        println components*.id
        println components*.name

        def ps = [1, 2, 3]
        println(function(*ps))

        def function2 = {
            x, y, z -> x + y + z
        }
        println(function2(*ps))


        def items = [4, 5]
        //直接引入
        def list = [1, 2, *items, 6]
        assert list == [1, 2, 4, 5, 6]
        //支持map
        def ml=[c:4,d:5]
        def map =[a:1,b:2,*:ml,e:6]
        assert map==[a:1,b:2,c:4,d:5,e:6]


    }

    static int function(def x, def y, def z) {
        x + y + z
    }

    static class Car {
        String make
        String model
    }


}


class Component {
    Long id
    String name
}

//spread运算符可用于任何实现Iterable接口的类：
class Compositeobject implements Iterable<Component> {

    def components = [new Component(id: 1, name: 'Foo'),
                      new Component(id: 2, name: 'Bar')]

    @Override
    Iterator<Component> iterator() {
        return components.iterator()
    }
}