package dev.tcheng.chip8.instruction

import dev.tcheng.chip8.data.State

data object DrawSprites : Instruction {
    override fun shouldExecute(instruction: String) = instruction.startsWith('D')

    override fun getName() = "Dxyn - DRW Vx, Vy, nibble"

    override fun execute(state: State, instruction: String) {
        // TODO
        /**
         * Display n-byte sprite starting at memory location I at (Vx, Vy), set VF = collision.
         *
         * The interpreter reads n bytes from memory, starting at the address stored in I.
         * These bytes are then displayed as sprites on screen at coordinates (Vx, Vy).
         * Sprites are XORed onto the existing screen.
         * If this causes any pixels to be erased, VF is set to 1, otherwise it is set to 0.
         * If the sprite is positioned so part of it is outside the coordinates of the display,
         * it wraps around to the opposite side of the screen.
         */
    }
}
