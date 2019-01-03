package groovy.io

class SerializeDemo {
    static main(arg) {
        boolean b = true
        String message = "hello from groovy"
        def path = "/Users/shuaijia/Pictures/serializedemo"
        def file = new File(path)
        file.withDataOutputStream {
            it.writeBoolean(b)
            it.writeUTF(message)
        }
        file.withDataInputStream {
            println it.readBoolean()

            println it.readUTF()
        }



    }
}
