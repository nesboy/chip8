package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Radix
import dev.tcheng.chip8.data.State

data object AddVRegisterToVRegister : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('8') && instruction.endsWith('4')

    override fun getName() = "8xy4 - ADD Vx, Vy"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val vRegisterIndex1 = instruction[1].digitToInt(Radix.BASE_16)
        val vRegisterIndex2 = instruction[2].digitToInt(Radix.BASE_16)

        val sum = state.vRegisters[vRegisterIndex1].plus(state.vRegisters[vRegisterIndex2])
        state.vRegisters[vRegisterIndex1] = sum.toUByte() // may cause overflow

        // carry bit
        state.vRegisters[0xF] = if (sum > 255u) 1u else 0u
    }
}
