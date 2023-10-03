package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Radix
import dev.tcheng.chip8.data.State

data object SetVRegisterFromVRegister : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('8') && instruction.endsWith('0')

    override fun getName() = "8xy0 - LD Vx, Vy"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val vRegisterIndex1 = instruction[1].digitToInt(Radix.BASE_16)
        val vRegisterIndex2 = instruction[2].digitToInt(Radix.BASE_16)
        state.vRegisters[vRegisterIndex1] = state.vRegisters[vRegisterIndex2]
    }
}
