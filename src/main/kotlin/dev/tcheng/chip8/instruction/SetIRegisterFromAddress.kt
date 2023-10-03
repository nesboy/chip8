package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Radix
import dev.tcheng.chip8.data.State

data object SetIRegisterFromAddress : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('A')

    override fun getName() = "Annn - LD I, addr"

    override fun execute(state: State, instruction: String) {
        val address = instruction.substring(1).toUShort(Radix.BASE_16)
        state.iRegister = address
    }
}
