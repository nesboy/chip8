package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Radix
import dev.tcheng.chip8.data.State

data object SubtractNegativeVRegisterFromVRegister : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('8') && instruction.endsWith('7')

    override fun getName() = "8xy7 - SUBN Vx, Vy"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val vRegisterIndex1 = instruction[1].digitToInt(Radix.BASE_16)
        val vRegisterIndex2 = instruction[2].digitToInt(Radix.BASE_16)

        val difference = state.vRegisters[vRegisterIndex2].minus(state.vRegisters[vRegisterIndex1])
        state.vRegisters[vRegisterIndex1] = difference.toUByte() // may cause underflow

        // borrow bit
        state.vRegisters[0xF] = if (difference > 0u) 1u else 0u
    }
}
