package ru.potatophobe.luckyticketscounter

fun main(args: Array<String>) {
    if (args.size != 1
        || args.first().toIntOrNull() == null
        || args.first().toInt() % 2 != 0
    ) {
        println("This applies only singe int argument representing a number of digits in ticket")
    }
    println("Number of lucky tickets: ${LuckyTicketsCounter.countRecursively(args.first().toInt() / 2)}")
}
