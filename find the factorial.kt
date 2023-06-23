// write the program to find the factorial of this num=5!=5*4*3*2*1

fun main()
{
    println("Enter a number")
    var n= readLine()!!.toInt()
    var fac =1
    for (a in 1..n)
    {
        fac=fac*a
    }
    println("factorial is $fac")
}