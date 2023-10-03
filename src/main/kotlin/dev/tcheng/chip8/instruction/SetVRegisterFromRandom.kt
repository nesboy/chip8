package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Constant
import dev.tcheng.chip8.data.State
import kotlin.random.Random

data object SetVRegisterFromRandom : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('C')

    override fun getName() = "Cxkk - RND Vx, byte"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val vRegisterIndex = instruction[1].digitToInt(Constant.HEXADECIMAL)
        val value = instruction.substring(2).toUByte(Constant.HEXADECIMAL)
        state.vRegisters[vRegisterIndex] = value.and(Random.nextBits(Byte.SIZE_BITS).toUByte())
    }
}
