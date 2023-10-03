package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Radix
import dev.tcheng.chip8.data.State

data object ShiftLeft : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('8') && instruction.endsWith('E')

    override fun getName() = "8xyE - SHL Vx {, Vy}"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val vRegisterIndex = instruction[1].digitToInt(Radix.BASE_16)
        val vRegisterValue = state.vRegisters[vRegisterIndex]

        state.vRegisters[0xF] = if (vRegisterValue.takeHighestOneBit() == 1u.toUByte()) 1u else 0u
        state.vRegisters[vRegisterIndex] = vRegisterValue.times(2u).toUByte()
    }
}
