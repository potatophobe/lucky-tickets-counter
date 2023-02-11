package ru.potatophobe.luckyticketscounter.test.tester.deserializer

import kotlin.reflect.KType

interface StringDeserializer<T> {
    val type: KType

    fun deserialize(stringValue: String): T
}
