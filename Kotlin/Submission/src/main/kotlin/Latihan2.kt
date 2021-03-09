class Latihan2{
    /**
     * Latihan 2: Kotlin Fundamental
     *
     * @author : Hermansyah
     */

    companion object{
        fun calculateResult(valueA: Int, valueB: Int, valueC: Int?) :Int{
            valueC?.let {
                return valueA + (valueB - valueC)
            } ?: run {
                return valueA + (valueB - 50)
            }
        }
    }
}