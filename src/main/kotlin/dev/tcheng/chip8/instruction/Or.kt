package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Constant
import dev.tcheng.chip8.data.State

data object Or : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith("8") && instruction.endsWith('1')

    override fun getName() = "8xy1 - OR Vx, Vy"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val vRegisterIndex1 = instruction[1].digitToInt(Constant.HEXADECIMAL)
        val vRegisterIndex2 = instruction[2].digitToInt(Constant.HEXADECIMAL)
        state.vRegisters[vRegisterIndex1] = state.vRegisters[vRegisterIndex1].or(state.vRegisters[vRegisterIndex2])
    }
}
