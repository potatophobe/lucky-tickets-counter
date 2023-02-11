package ru.potatophobe.luckyticketscounter.test

import java.nio.file.Path

fun Any.getAssociatedResourceDir(): Path {
    val uri = (javaClass.classLoader.getResource(javaClass.simpleName)?.toURI()
        ?: throw IllegalStateException("No associated directory"))
    return Path.of(uri)
}
