package com.example.data.model

data class Player(
    val id: Int,
    val name: String,
    val role: Role,
    val isAlive: Boolean = true,
    val isMayor: Boolean = false,
    val isProtected: Boolean = false,
    val isTargetedByWerewolves: Boolean = false,
    val hasSeenCard: Boolean = false,
    val notes: String = ""
)
