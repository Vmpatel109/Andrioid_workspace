fun main()
{
    var map = HashMap<String, String>()
    map.put("Gujarar", "Rajkot")
    map.put("Maharastra", "Mumbai")
    map.put("Rajsthan", "Jaipur")


    var keyList = ArrayList(map.keys)
    var valueList = ArrayList(map.values)

    println("Key List: $keyList")
    println("Value List: $valueList")

}


