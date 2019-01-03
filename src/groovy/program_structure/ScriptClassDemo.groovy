package groovy.program_structure

import org.codehaus.groovy.runtime.InvokerHelper

//一个脚本总是编译成class。Groovy编译器将为您编译该类，并将脚本的主体复制到run方法中，Hello Groovy最终会被编译为以下代码
class ScriptClassDemo extends Script {

    @Override
    Object run() {
        println "Groovy world!"
    }

    static main(arg) {
        //并委托在该run方法上执行脚本
        InvokerHelper.runScript(ScriptClassDemo, arg)
    }

}
