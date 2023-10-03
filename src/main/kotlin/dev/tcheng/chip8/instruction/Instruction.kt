package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.State

sealed interface Instruction {
    fun shouldExecute(instruction: String): Boolean
    fun getName(): String
    fun execute(state: State, instruction: String)
}
