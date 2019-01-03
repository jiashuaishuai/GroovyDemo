package groovy.operator

//<< 或者  >> 箭头方向的为外层，
class CompositionDemo {
    def static main(arg) {
        def plus2 = { it + 2 }
        def times3 = { it * 3 }


        def cc = plus2 times3(3)
        def value = plus2 << times3
        assert value(3) == 11 && value(3) == cc


        def dd = times3 plus2(3)
        def valustp = times3 << plus2


        assert dd == 15 && 15 == valustp(3)


        assert dd == (plus2 >> times3)(3)

    }
}
