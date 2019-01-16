package groovy.operator


class GStringsDemo {
    def static main(arg) {

        def x = 1;
        def gs = "x=${x}"
        assert gs == "x=1"
        x = 2
//断言失败 ${x}GString中的语法不表示闭包，而是表示在$x创建GString时计算的表达式。
//        assert gs == "x=2"

        //${-> x} GString中的实际闭包，
        def gss = "x=${-> x}"
        assert gss == "x=2"
        x = 3
        assert gss == "x=3"


        def message = "The message is ${"hello"}"

        assert message instanceof GString

        assert taskStrint(message) == "The message is hello"

        //GString和String的 HashCode不同，顾不能作为map集合的key，因为map集合key采用的是hash算法
        assert "test ${"xx"}".hashCode() != "test xx".hashCode()

        def key = 'a'
        //注意警告⚠️
        def m = ["key=$key": "letter ${key}"]
        assert m["key=a"] == null

        //-------------------************最骚的用法****************************


        def method = 'toString'
        println new Date()."$method"()




    }

    static taskStrint(String message) {
        assert message instanceof String

        message

    }
}
