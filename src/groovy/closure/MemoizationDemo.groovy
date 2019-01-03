package groovy.closure

class MemoizationDemo {

    def static main(arg) {
        def fib
        fib = { long n -> n<2?n:fib(n-1)+fib(n-2) }.memoize()
        assert fib(25) == 75025 // slow!




    }
}
