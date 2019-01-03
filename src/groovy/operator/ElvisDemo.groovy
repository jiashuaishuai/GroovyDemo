package groovy.operator

class ElvisDemo {
    def static main(arg) {

        String name

        //!name为空=true
        assert !name

        //name不为空则name，否则"aaa"
        def displayName = name ?: "aaa"
        println displayName
        name = "bb"
        displayName = name ?: "aaa"
        println displayName

        PersonElvis personElvis;

        //NullPointerException
//        personElvis.name = "cc"
        //不会报空指针，和kotlin一样
        personElvis?.name = "cc"

        personElvis = new PersonElvis(name: "乌尔奇")

        //默认回调用get方法和kotlin一样
        assert personElvis.name == "Name is 乌尔奇"

        //轻质调用字段而不是get方法
        assert personElvis.@name == "乌尔奇"


        def num = 23.11

        //MissingPropertyException: No such property: toString for class: java.math.BigDecimal
//        println "num is $num.toString()"
        println "num is ${num.toString()}"


        //	这里，闭包只接受一个java.io.StringWriter参数，你可以使用<<leftShift设置参数
        //只能接受一个参数
        def num12 = 3
        println "1+2==${w -> w << num12}"

    }

    static class PersonElvis {
        def name

        def getName() {
            return "Name is $name"
        }
    }

}
