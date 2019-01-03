package groovy

class HelloGroovy {
    def static value = "Hello World"


    static Closure getPrinter() {
        return { line -> println line }
    }

    def static benchmark(repeat, Closure worker) {
        def start = System.currentTimeMillis()
        repeat.times {
            worker(it)
        }
        def stop = System.currentTimeMillis()
        return stop - start
    }

    static void main(args) {

        //闭包
        Closure pars = {
            String... arg ->

                //this，闭包定义所在类
                println this.simpleName
                //owner 即闭包定义所在的对象或闭包。
                println owner.metaClass

                arg.each {
                    println it;
                }
        }

        pars.call("zz", "cc", "ff")

        //泛型表示返回值类型
        Closure<String> returnString = {
            s -> s = s + "aaaa"
        }

        println returnString.call("测试")

//        { println ++it }.call(1)
//        { item -> println++ item }.call(3)

        def slow = benchmark(10000) {
            (int) it / 2
        }
        def fast = benchmark(10000) {
            it.intdiv(2)
        }
        println(fast)
        println(slow)


        def toTriple = {
            soun, at ->
                println soun.toString()
                println at
        }
        assert toTriple instanceof Closure

        def souss = new Soun(name: "小明", genre: "喝喝喝")
        toTriple.call(souss) {
            it = "xxx"
//            println it
        }

//        println value
        repeat args: value, repeat: 7
        println "——————————————————————————————————————————"
        repeat value



        println "——————————————————————————————————————————"
        def range = 0..4
        println range.class
        assert range instanceof List

        def coll = ["Groovy", "Java", "Ruby"]
        assert coll instanceof Collection
        assert coll instanceof ArrayList
        coll << "哈哈哈"
        for (i in 0..<coll.size()) {
            println coll.value[i]
        }
        println "——————————————————————collect————————————————————"
        coll.collect {
            println "$it"
        }
        coll.each {
            println "$it"
        }


        println "—————————————————集合+- —————————————————————————"

        def nums = [1, 2, 3, 4, 5]

        assert nums + 6 == [1, 2, 3, 4, 5, 6]
        assert nums - [1, 3] == [2, 4, 5]
        println "—————————————————join—————————————————————————"

        assert nums.join() == "12345"
        assert "1x2x3x4x5" == nums.join("x")
        assert "1 2 3 4 5" == nums.join(" ")

        def newNums = nums.collect {
            it * it//有返回值
        }
        newNums.each {
            println(it)//没有返回值
        }

        println "——————————————————count————————————————————————"

        List<String> strs = ["he", 'oe', 'fe', 'ee', 'he']
        assert strs.count('he') == 2

        println "——————————————————toUpperCase————————————————————————"

        println(["he", 'oe', 'fe', 'ee', 'he']*.toUpperCase().collect())
        strs*.toUpperCase()
        println strs.collect()

        println "——————————————————map LinkedHashMap  映射————————————————————————"

        def ash = [name: "小明", "hehe": 33]

        ash.put("id", 11)
        assert ash.getClass() == LinkedHashMap
        assert ash.name == "小明"
        assert ash.get("id") == 11
        assert ash.id == 11
        ash.dob = "01/29/76"
        assert ash.dob == "01/29/76"
        assert ash["dob"] == "01/29/76"
        ash["gender"] = "male"
        ash.collect {
            println "$it.key ----- $it.value"
        }
        println "——————————————————闭包————————————————————————"

        def excite = {
            it -> return it + "闭包执行"
        }
        println excite("呵呵")
        println excite.call("call-")
        println "——————————————————类————————————————————————"

        def soun = new Soun(name: "伤心太平洋", artist: "任贤齐")
        soun.setName "伤心太平洋2"
//        println soun.genre.toUpperCase()
        println soun.genre?.toUpperCase()
        soun.setGenre "hahah"
        println soun


        def code = { 123 }

        assert code.call() == 123

        def strc = "ca"

        //打印两边
        println strc * 2


    }

    def static repeat(args, repeat = 5) {
        for (i in 1..<repeat) {
            println(args)
        }

    }
}
