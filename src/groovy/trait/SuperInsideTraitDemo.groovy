package groovy.trait

/**
 * 如果一个类实现了多个特征并且找到了对不合格的调用super，那么：
 * 1.如果该类实现了另一个特征，则该调用将委托给链中的下一个特征
 * 2.如果链中没有任何特征，则super引用实现类的超类this
 */
class SuperInsideTraitDemo {
    static main(arg) {

        def sb = new StringBuilder().withTraits(Filtering)

        sb.append("Groovy")

        println sb.toString()

    }
}

trait Filtering {
    StringBuilder append(String str) {
        def subst = str.replace("o", "")
        super.append(subst)
    }

    String toString() {
        super.toString()
    }
}
