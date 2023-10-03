package dev.tcheng.chip8.data

@OptIn(ExperimentalUnsignedTypes::class)
data class State(
    val memory: UByteArray = UByteArray(size = 4096),
    var programCounter: UShort = 0x200u,
    val callStack: UShortArray = UShortArray(size = 16),
    var stackPointer: UByte = 0u,
    val vRegisters: UByteArray = UByteArray(size = 16),
    var iRegister: UShort = 0u,
    var delayTimerRegister: UByte = 0u,
    var soundTimerRegister: UByte = 0u,
    val display: List<MutableList<Boolean>> = List(size = 64) { MutableList(size = 32) { false } }
)
