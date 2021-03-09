/**
 * Latihan Runner
 */
fun main(){
    Latihan1_1.display("Kotlin is awesome")
    Latihan1_2.display("Kotlin Fundamental", true)

    var resultA = Latihan2.calculateResult(101, 52, 99);
    var resultB = Latihan2.calculateResult(101, 52, null)
    println("""
        ResultA is $resultA
        ResultB is $resultB
    """.trimIndent())

}