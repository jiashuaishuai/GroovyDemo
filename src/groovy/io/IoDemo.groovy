package groovy.io

class IoDemo {

    static main(arg) {
        def demo = new IoDemo()
        def path = "/Users/shuaijia/Pictures/pathDemo"
        demo.printFile path

        demo.printReadLine path

        demo.toList path

        demo.toStrings path

//        demo.toBytes path

        demo.writer path
    }

    void printFile(String path) {
        new File(path).eachLine("UTF-8") { content, nb ->
            println "Line $nb:$content"
        }
    }

    void printReadLine(String path) {
        def count = 0, MAXSIZE = 7
        new File(path).withReader {
            while (it.readLine()) {
                if (++count > MAXSIZE) {
                    throw new RuntimeException('Haiku should only have 3 verses')
                }
            }
        }

    }

    void toList(String path) {
        def list = new File(path).collect { it }
        list.each {
            println it
        }
    }

    void toStrings(String path) {
        def array = new File(path) as String[]

        array.each {
            println it
        }

    }

    void toBytes(String path) {

        byte[] bys = new File(path).bytes
// bys.each {println it}

    }

    void input(String path) {
//        new File(path).withInputStream {} 直接使用
        def is = new File(path).newInputStream()
        //....
        is.close()
    }


    void writer(String path) {
//        new File(path).withWriter {
//
//            it.writeLine("写入测试111")
//            it.writeLine("写入测试222")
//            it.writeLine("写入测试333")
//        }

        //简易写法

        new File(path) << '''写入测试1111！
写入测试2222！
写入测试3333！'''

    }


}
