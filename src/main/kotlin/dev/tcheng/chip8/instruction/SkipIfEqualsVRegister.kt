package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Constant
import dev.tcheng.chip8.data.State

data object SkipIfEqualsVRegister : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('5') && instruction.endsWith('0')

    override fun getName() = "5xy0 - SE Vx, Vy"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val vRegisterIndex1 = instruction[1].digitToInt(Constant.HEXADECIMAL)
        val vRegisterIndex2 = instruction[2].digitToInt(Constant.HEXADECIMAL)

        if (state.vRegisters[vRegisterIndex1] == state.vRegisters[vRegisterIndex2]) {
            state.programCounter = state.programCounter.plus(2u).toUShort()
        }
    }
}
