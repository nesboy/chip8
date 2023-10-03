package dev.tcheng.chip8

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
    val font = ubyteArrayOf(
        0xF0u, 0x90u, 0x90u, 0x90u, 0xF0u, // 0
        0x20u, 0x60u, 0x20u, 0x20u, 0x70u, // 1
        0xF0u, 0x10u, 0xF0u, 0x80u, 0xF0u, // 2
        0xF0u, 0x10u, 0xF0u, 0x10u, 0xF0u, // 3
        0x90u, 0x90u, 0xF0u, 0x10u, 0x10u, // 4
        0xF0u, 0x80u, 0xF0u, 0x10u, 0xF0u, // 5
        0xF0u, 0x80u, 0xF0u, 0x90u, 0xF0u, // 6
        0xF0u, 0x10u, 0x20u, 0x40u, 0x40u, // 7
        0xF0u, 0x90u, 0xF0u, 0x90u, 0xF0u, // 8
        0xF0u, 0x90u, 0xF0u, 0x10u, 0xF0u, // 9
        0xF0u, 0x90u, 0xF0u, 0x90u, 0x90u, // A
        0xE0u, 0x90u, 0xE0u, 0x90u, 0xE0u, // B
        0xF0u, 0x80u, 0x80u, 0x80u, 0xF0u, // C
        0xE0u, 0x90u, 0x90u, 0x90u, 0xE0u, // D
        0xF0u, 0x80u, 0xF0u, 0x80u, 0xF0u, // E
        0xF0u, 0x80u, 0xF0u, 0x80u, 0x80u // F
    )
    font.copyInto(destination = memory, destinationOffset = 0)
}

@OptIn(ExperimentalUnsignedTypes::class)
fun readInstruction(programCounter: UShort, memory: UByteArray): String {
    val range = programCounter..programCounter.inc()
    return memory
        .slice(range.map { it.toInt() })
        .joinToString(separator = "") { it.toString(radix = 16).padStart(length = 2, padChar = '0').uppercase() }
}
