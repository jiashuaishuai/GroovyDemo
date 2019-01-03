package groovy.operator

import java.util.regex.Matcher
import java.util.regex.Pattern

class RegularExpressionDemo {
    def static main(arg) {


        def p = ~/foo/
        assert p instanceof Pattern
        p = ~$/doolar/slashy $ string/$
        println p

        def test = "some test to match"
        //=~text 查找运算符
        def m = test =~ /match/
        assert m instanceof Matcher

        if (!m) {//如果么有找到 等价于 if(!m.find())
            throw new RuntimeException("Oops, text not found!")
        }

        //匹配运算符，返回boolean
        m = test ==~ /match/
        assert m instanceof Boolean

        if (m) {
            throw new RuntimeException("Should not reach that point!")
        }


    }
}
