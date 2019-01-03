package groovy.io

/**
 * 执行外部流程
 */
class ExecuteDemo {
    static main(arg) {
        def process = "ls / -l".execute()
        println process
        def process1 = "dir".execute()
        println(process1)
    }
}
