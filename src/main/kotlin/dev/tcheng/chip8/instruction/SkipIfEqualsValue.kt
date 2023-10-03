package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Radix
import dev.tcheng.chip8.data.State

data object SkipIfEqualsValue : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('3')

    override fun getName(): String = "3xkk - SE Vx, byte"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val vRegisterIndex = instruction[1].digitToInt(Radix.BASE_16)
        val value = instruction.substring(2).toUByte(Radix.BASE_16)

        if (state.vRegisters[vRegisterIndex] == value) {
            state.programCounter = state.programCounter.plus(2u).toUShort()
        }
    }
}
