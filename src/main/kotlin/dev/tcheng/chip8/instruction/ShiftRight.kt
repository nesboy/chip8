package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Constant
import dev.tcheng.chip8.data.State

data object ShiftRight : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('8') && instruction.endsWith('6')

    override fun getName() = "8xy6 - SHR Vx {, Vy}"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val vRegisterIndex = instruction[1].digitToInt(Constant.HEXADECIMAL)
        val vRegisterValue = state.vRegisters[vRegisterIndex]

        state.vRegisters[0xF] = if (vRegisterValue.takeLowestOneBit() == 1u.toUByte()) 1u else 0u
        state.vRegisters[vRegisterIndex] = vRegisterValue.div(2u).toUByte()
    }
}
