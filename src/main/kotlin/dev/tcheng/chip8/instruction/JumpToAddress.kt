package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Constant
import dev.tcheng.chip8.data.State

data object JumpToAddress : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('1')

    override fun getName() = "1nnn - JP addr"

    override fun execute(state: State, instruction: String) {
        state.programCounter = instruction.substring(1).toUShort(Constant.HEXADECIMAL)
    }
}
