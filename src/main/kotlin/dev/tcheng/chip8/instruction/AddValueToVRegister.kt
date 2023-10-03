package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Constant
import dev.tcheng.chip8.data.State

data object AddValueToVRegister : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('7')

    override fun getName() = "7xkk - ADD Vx, byte"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val vRegisterIndex = instruction[1].digitToInt(Constant.HEXADECIMAL)
        val value = instruction.substring(2).toUByte(Constant.HEXADECIMAL)
        state.vRegisters[vRegisterIndex] = state.vRegisters[vRegisterIndex].plus(value).toUByte()
    }
}
