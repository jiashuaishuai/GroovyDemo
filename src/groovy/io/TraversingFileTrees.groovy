package groovy.io

class TraversingFileTrees {
    static main(arg) {
        def tft = new TraversingFileTrees()
        tft.eachFile()
        println "----------0--------------------"
        tft.eachFileMath()
        println "---------------------0----------------------"
        tft.eachRecurse()
        println "--------------------0-------------------------"
        tft.eachRecurseFiles()
        println "--------------------0-------------------------"
        tft.traverse()

    }

    void eachFile() {
        new File(".").eachFile {
            println it
        }
    }

    void eachFileMath() {

        new File("/Users/shuaijia/Pictures/").eachFileMatch(~/.*\.jpg/) {
            println it.name
        }
    }
    //递归遍历，目录树
    void eachRecurse() {
        new File(".").eachFileRecurse {
            println it.name
        }
    }
    //仅对文件夹执行闭包代码
    void eachRecurseFiles() {
        new File(".").eachFileRecurse(FileType.DIRECTORIES) {
            println it.name
        }
    }

    //复杂遍历，特殊表示java，当遍历到目录为java是，停止，否则继续遍历
    void traverse() {
        new File(".").traverse {
            if (it.directory && it.name == "java") {
                FileVisitResult.TERMINATE
            } else {
                println(it.name)
                FileVisitResult.CONTINUE
            }
        }
    }


}
