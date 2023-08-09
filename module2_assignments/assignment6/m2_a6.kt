import java.util.*

fun main()
{
    var reader = Scanner(System.`in`)

    print("Enter a number: ")
    var num = reader.nextInt()

    if (num % 2 == 0)
        println("$num is even")
    else
        println("$num is odd")
}