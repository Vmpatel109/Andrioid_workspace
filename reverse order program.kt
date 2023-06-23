//wp to print reverse order program

fun main()
{
    println("Enter a number")
    var n= readLine()!!.toInt()

    println("--reverse order--")


    for (a in n downTo 1)
    {
        println(a)
    }
}