package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Constant
import dev.tcheng.chip8.data.State

data object SkipIfNotEqualsValue : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('4')

    override fun getName() = "4xkk - SNE Vx, byte"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val vRegisterIndex = instruction[1].digitToInt(Constant.HEXADECIMAL)
        val value = instruction.substring(2).toUByte(Constant.HEXADECIMAL)

        if (state.vRegisters[vRegisterIndex] != value) {
            state.programCounter = state.programCounter.plus(2u).toUShort()
        }
    }
}
