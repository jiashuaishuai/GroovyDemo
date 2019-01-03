package groovy.annotation

import groovy.transform.AnnotationCollector

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.reflect.Modifier

//注解和java基本一样，这里举例，Closure 闭包 作为注解参数，
class AnnotationDemo {

    static main(arg) {
        def task = run(Task)

        println task.result.collect()

    }

    static <T> T run(Class<T> taskClass) {
        def tasks = taskClass.newInstance()
        def params = [jdk: 6, windows: false]
        tasks.class.getDeclaredMethods().each {
            if (Modifier.isPublic(it.modifiers) && it.parameterCount == 0) {
                def onlyIf = it.getAnnotation(OnlyIf)
                if (onlyIf) {
                    //newInstance(tasks,tasks)  不明觉厉,但是必须传入值
                    Closure cl = onlyIf.value().newInstance(tasks, tasks)
                    cl.delegate = params
                    if (cl()) {
                        //执行注解匹配方法
                        it.invoke(tasks)
                    }


                } else {
                    println it.name
                }

            }
        }

        //返回task
        tasks

    }

}

@Retention(RetentionPolicy.RUNTIME)
//@AnnotationCollector 元注解，多个注解合并 元注释支持覆盖特定值
@interface OnlyIf {

    //不能声明Closure作为参数，需要用Class
    Class value()

}

class Task {

    def result = []

    @OnlyIf({ true })
    void alwaysExecuted() {
        result << 1
    }

    @OnlyIf({ jdk >= 6 })
    void supportedOnlyInJDK6() {
        result << "JDK 6"
    }

    //参数为闭包 返回值boolean
    @OnlyIf({ jdk >= 6 && windows })
    void requiresJDK7AndWindows() {
        result << "JDK 7 Windows"
    }
}

