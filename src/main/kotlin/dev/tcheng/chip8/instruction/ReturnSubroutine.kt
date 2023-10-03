package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.State

data object ReturnSubroutine : Instruction {
    override fun shouldExecute(instruction: String) = instruction == "00EE"

    override fun getName() = "00EE - RET"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        state.programCounter = state.callStack[state.stackPointer.toInt()]
        state.stackPointer.dec()
    }
}
