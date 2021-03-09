import java.lang.StringBuilder

class Latihan3{
    /**
     * Latihan 3: Control Flow
     *
     * @author : Hermansyah
     */

    companion object{
        fun calculateResult(listNumbers : IntRange?, breakValue: Int?) :StringBuilder{
            val result: StringBuilder
            listNumbers?.forEach lit@{
                val value = it
                if (value > breakValue) break
                breakValue?.let {
                    if (value%2==0) return@lit

                }
            }
        }
    }
}