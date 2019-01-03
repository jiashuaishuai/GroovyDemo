package groovy.operator

class CurryingDemo {
    def static main(arg) {

        def nCopies = { n, str -> str * n }
        //设置左边 curry将第一个参数设置为2，创建一个接受单个的新闭包（函数）String
        def twoce = nCopies.curry(2)
        assert twoce("ss") == "ssss"

        //rcurry将最后一个参数设置为bla，创建一个接受单个的新闭包（函数）int
        def blah = nCopies.rcurry("as")
        assert blah(2) == "asas"


        def volume = { l, w, h -> l * w * h }

        //将第二个（index=1）参数设置为2，创建一个新的闭包返回
        def fixedwidthVolume = volume.ncurry(1, 2)

        assert volume(1, 2, 3) == fixedwidthVolume(1, 3)

        //也可以从指定的索引开始设置多个参数
        def fixedWidthAndHeight = volume.ncurry(1, 2, 3)

        assert volume(1, 2, 3) == fixedWidthAndHeight(1)


    }
}
