package ru.potatophobe.luckyticketscounter.test.tester.deserializer

import java.math.BigInteger
import kotlin.reflect.KType
import kotlin.reflect.full.createType

class BigIntegerStringDeserializer : StringDeserializer<BigInteger> {
    override val type: KType = BigInteger::class.createType()

    override fun deserialize(stringValue: String): BigInteger {
        return stringValue.toBigInteger()
    }
}
