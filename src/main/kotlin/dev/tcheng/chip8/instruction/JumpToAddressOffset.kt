package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.Radix
import dev.tcheng.chip8.data.State

data object JumpToAddressOffset : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('B')

    override fun getName() = "Bnnn - JP V0, addr"

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(state: State, instruction: String) {
        val address = instruction.substring(1).toUShort(Radix.BASE_16)
        val offset = state.vRegisters[0]
        state.programCounter = address.plus(offset).toUShort()
    }
}
