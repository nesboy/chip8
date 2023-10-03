package dev.tcheng.chip8

import dev.tcheng.chip8.data.Constant
import dev.tcheng.chip8.data.State
import dev.tcheng.chip8.instruction.Instruction
import java.io.File

@OptIn(ExperimentalUnsignedTypes::class)
fun main(args: Array<String>) {
    val state = State()
    val supportedInstructions: List<Instruction> = Instruction::class.sealedSubclasses.map { it.objectInstance!! }

    // load memory
    loadFont(memory = state.memory)
    loadRom(file = File("rom/Chip8 Picture.ch8"), memory = state.memory)
    println(state)

    val instruction = readInstruction(state.programCounter, state.memory)

    supportedInstructions.forEach {
        if (it.shouldExecute(instruction)) {
            println(it.getName())
        }
    }
}

@OptIn(ExperimentalUnsignedTypes::class)
fun loadRom(file: File, memory: UByteArray) {
    val rom = file.inputStream().readBytes().asUByteArray()
    rom.copyInto(destination = memory, destinationOffset = 0x200)
}

@OptIn(ExperimentalUnsignedTypes::class)
fun loadFont(memory: UByteArray) {
    Constant.FONT.copyInto(destination = memory, destinationOffset = 0)
}

@OptIn(ExperimentalUnsignedTypes::class)
fun readInstruction(programCounter: UShort, memory: UByteArray): String {
    val range = programCounter..programCounter.inc()
    return memory
        .slice(range.map { it.toInt() })
        .joinToString(separator = "") { it.toString(radix = 16).padStart(length = 2, padChar = '0').uppercase() }
}
