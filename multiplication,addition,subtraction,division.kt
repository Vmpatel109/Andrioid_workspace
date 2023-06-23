fun main() {
    println("Enter number 1 : ")
    val num1 = readln().toInt()
    println("Enter number 2 : ")
    val num2 = readln().toInt()

    val add = num1 + num2
    val sub = num1 - num2
    val mul = num1 * num2
    val div = num1.div(num2)
    println("addition = $add")
    println("substraction = $sub")
    println("Multiplication = $mul")
    println("Division = $div")
}