package ru.potatophobe.luckyticketscounter.test.tester

import ru.potatophobe.luckyticketscounter.test.tester.deserializer.StringDeserializer
import java.nio.file.Path
import kotlin.io.path.*
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.test.assertEquals

class FileBasedTester(
    private val dir: Path,
    private val function: KFunction<*>,
    vararg deserializers: StringDeserializer<*>
) : Tester {
    private val deserializers: Map<KType, StringDeserializer<*>> = deserializers.associateBy { it.type }

    init {
        if (!dir.isDirectory() || dir.notExists()) {
            throw IllegalArgumentException("Dir must be an existent directory")
        }
    }

    override fun test(index: Int) {
        val parameters = function.parameters
        val arguments = dir.inputFile(index).readArgs(parameters.size)
            .mapIndexed { i, it -> deserializerForType(parameters[i].type).deserialize(it) }
        val expectedResult = dir.outputFile(index).readArgs(1).single()
            .let { deserializerForType(function.returnType).deserialize(it) }

        assertEquals(
            expected = expectedResult,
            actual = function.call(*arguments.toTypedArray()),
            message = "Result is not equal to test.$index.out"
        )
    }

    override fun testAll() {
        dir.forEachDirectoryEntry {
            if (it.fileName.pathString.matches(TEST_INPUT_FILE_REGEX)) {
                test(it.getTestIndex())
            }
        }
    }

    private fun deserializerForType(type: KType) = deserializers[type]
        ?: throw IllegalStateException("No suitable deserializer")

    private fun Path.getTestIndex(): Int {
        if (fileName.pathString.matches(TEST_FILE_REGEX)) {
            return fileName.pathString.split(".")[1].toInt()
        } else {
            throw IllegalStateException("This is not test file")
        }
    }

    private fun Path.inputFile(index: Int) = resolve("test.$index.in")

    private fun Path.outputFile(index: Int) = resolve("test.$index.out")

    private fun Path.readArgs(quantity: Int) = readText().trim().split(",")
        .also { if (it.size != quantity) throw IllegalArgumentException() }

    companion object {
        val TEST_FILE_REGEX = Regex("test\\.\\d+\\.(in)|(out)")
        val TEST_INPUT_FILE_REGEX = Regex("test\\.\\d+\\.in")
    }
}
