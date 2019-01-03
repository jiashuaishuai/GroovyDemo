package groovy.operator

class RangeOperatorDemo {
    def static main(arg) {
//范围实现是轻量级的，意味着只存储下限和上限。您可以从任何Comparable具有的对象next()和previous()方法创建范围，以确定范围中的下一个/上一个项目
        def range = 0..5
        assert range == [0, 1, 2, 3, 4, 5]
        //or
        assert range.collect() == [0, 1, 2, 3, 4, 5]

        assert 1..<5 == [1, 2, 3, 4]


        assert 'a'..'e' == ['a', 'b', 'c', 'd', 'e']

        def list = [0, 1, 2, 3, 4, 5]
//UnsupportedOperationException  只存储下限和上限
//        range[2..4] = [7, 8, 9]
//支持范围
        assert list[0..2] == [0, 1, 2]
        list[2..4] = [7, 8, 9]


        println list.collect()


        assert 3 in range

    }
}
