package groovy.operator
//下标运算符，可以重载  as 强行转换 也可以重载
/**
 * 重载表
 */
class SubscriptOperatorDemo {
    def static main(arg) {
        def user = new User(id: 2, name: "白夜族")

        assert user[0] == 2
        assert user[1] == "白夜族"

        user[0] = 4
        user[1] = "晓"

        assert user[1] == "晓"

        def idf = user as Identifiable
        assert idf.name == "晓"

        def user2 = new User(id: 10, name: "随便了")

        def nuser = user+user2

        println nuser.id
        println nuser.name

    }
}

class Identifiable {
    String name
}

class User {
    def id
    def name

    def plus(User user) {
        return new User(id: id + user.id, name: name + user.name)
    }

    def asType(Class target) {
        if (target == Identifiable) {
            return new Identifiable(name: name)
        }
        throw new ClassCastException("User cannot be coerced into $target")

    }

    def getAt(int i) {
        switch (i) {
            case 0: return id
            case 1: return name
        }
        throw new IllegalArgumentException("No such element $i")
    }

    def putAt(int i, def value) {
        switch (i) {
            case 0: id = value
                return
            case 1: name = value
                return
        }
        throw new IllegalArgumentException("No such element $i")
    }
}
