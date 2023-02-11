package ru.potatophobe.luckyticketscounter

import org.junit.jupiter.api.Test
import ru.potatophobe.luckyticketscounter.test.getAssociatedResourceDir
import ru.potatophobe.luckyticketscounter.test.tester.FileBasedTester
import ru.potatophobe.luckyticketscounter.test.tester.deserializer.BigIntegerStringDeserializer
import ru.potatophobe.luckyticketscounter.test.tester.deserializer.IntStringDeserializer
import java.nio.file.Path

internal class LuckyTicketsCounterTest {
    private val providedDir = System.getProperty("${LuckyTicketsCounterTest::class.qualifiedName}.dir")
        ?.let { Path.of(it) }

    @Test
    fun countRecursively() {
        val testDir = if (providedDir != null) {
            println("Provided test directory is '$providedDir'")
            providedDir
        } else {
            val defaultDir = getAssociatedResourceDir()
            println("Provided test directory is null. Using default '$defaultDir'")
            defaultDir
        }
        val tester = FileBasedTester(
            dir = testDir,
            function = LuckyTicketsCounter::countRecursively,
            IntStringDeserializer(), BigIntegerStringDeserializer()
        )
        tester.testAll()
    }
}
