class Latihan1_2{
    /**
     * Latihan 1 Bagian 2: Program Pertama Kotlin
     *
     * @author : Hermansyah
     */

    companion object{
        fun display(message: String?, isNewLine: Boolean = true){
            message?.let {
                if (isNewLine) println(message) else print(message)
            } ?: run {
                if (isNewLine) println("message param is empty") else print("message param is empty")
            }
        }
    }
}