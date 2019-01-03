package groovy

class Soun {
    def name

    def artist

    def genre

    String getGenre() {
         genre?.toUpperCase()
    }


    @Override
    String toString() {
        return "Soun{" +
                "name=" + name +
                ", artist=" + artist +
                ", genre=" + getGenre() +
                '}'
    }
}
