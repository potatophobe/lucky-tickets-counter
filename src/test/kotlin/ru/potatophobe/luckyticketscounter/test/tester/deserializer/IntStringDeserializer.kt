package ru.potatophobe.luckyticketscounter.test.tester.deserializer

import kotlin.reflect.KType
import kotlin.reflect.full.createType

class IntStringDeserializer : StringDeserializer<Int> {
    override val type: KType = Int::class.createType()

    override fun deserialize(stringValue: String): Int {
        return stringValue.toInt()
    }
}
