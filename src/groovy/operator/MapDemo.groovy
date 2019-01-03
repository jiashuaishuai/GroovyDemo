package groovy.operator

//注意。。。。。。
class MapDemo {
    def static main(arg) {

        def key = "name"
        //这里的key是字符串，不是变量
        def m = [key: "value"]

        assert m.key
        assert !m.name

        //这里的key才是变量需要用（）
        def mn = [(key): "value"]

        assert !mn.key
        assert mn.name

        assert [1, 2, 3].every { it < 5 }//所有元素满足
        assert [1, 2, 3].any { it > 2 }//任意一个满足

        def list = [9, 4, 2, 10, 5]
        assert list.max() == 10
        assert list.min() == 2


    }
}
