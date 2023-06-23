//write the program to print given number table

fun main()
{
    println("Enter a number")
    var n= readLine()!!.toInt()

    var i=1

    for (i in i..10)
    {
        var a= n * i

        println("$n * $i = $a ")
    }

}