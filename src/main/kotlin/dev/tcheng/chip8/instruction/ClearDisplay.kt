package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.State

data object ClearDisplay : Instruction {
    override fun shouldExecute(instruction: String) = instruction == "00E0"

    override fun getName() = "00E0 - CLS"

    override fun execute(state: State, instruction: String) {
        state.display.forEach { xPosition ->
            xPosition.forEachIndexed { index, _ ->
                xPosition[index] = false
            }
        }
    }
}
