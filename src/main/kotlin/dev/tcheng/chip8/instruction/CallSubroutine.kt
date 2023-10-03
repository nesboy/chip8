package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Constant
import dev.tcheng.chip8.data.State

data object CallSubroutine : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('2')

    override fun getName() = "2nnn - CALL addr"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val address = instruction.substring(1).toUShort(Constant.HEXADECIMAL)
        state.stackPointer.inc()
        state.callStack[state.stackPointer.toInt()]
        state.programCounter = address
    }
}
