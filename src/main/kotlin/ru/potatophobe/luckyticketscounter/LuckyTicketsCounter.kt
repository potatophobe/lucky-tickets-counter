package ru.potatophobe.luckyticketscounter

import java.math.BigInteger

object LuckyTicketsCounter {

    fun countRecursively(halfSize: Int): BigInteger {
        return if (halfSize == 0) BigInteger.ZERO
        else countSumCombinationsForEachNumberRecursively(halfSize)
            .reduce { sum, it -> sum + (it * it) }
    }

    private fun countSumCombinationsForEachNumberRecursively(digits: Int): Array<BigInteger> {
        return if (digits < 1) Array(0) { BigInteger.ZERO }
        else if (digits == 1) Array(10) { BigInteger.ONE }
        else Array((9 * digits) + 1) { BigInteger.ZERO }.also { result ->
            val previousResult = countSumCombinationsForEachNumberRecursively(digits - 1)
            repeat(10) { i ->
                for (j in previousResult.indices) {
                    result[i + j] = result[i + j] + previousResult[j]
                }
            }
        }
    }
}
