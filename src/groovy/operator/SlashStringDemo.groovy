package groovy.operator

class SlashStringDemo {

    def static main(arg) {
        def fooPattern = /.*foo.*/
        assert fooPattern == '.*foo.*'
        def multilineSlashy = /one
tow
three
${fooPattern}
fa\/faf
/

        println multilineSlashy

    }
}
