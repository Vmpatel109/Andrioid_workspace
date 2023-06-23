// write a program to find the summation of given number

fun main()
{
    println("Enter a number")
    var n= readLine()!!.toInt()

    var a=0

    while (n>0)
    {
        a=a+n%10
        n=n/10
    }
    println(a)
}