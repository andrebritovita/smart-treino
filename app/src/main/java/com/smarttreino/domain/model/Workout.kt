package com.smarttreino.domain.model

data class Workout(
    val id: String = "",
    val name: String = "",
    val exercises: List<Exercise> = emptyList(),
    val createdAt: Long = System.currentTimeMillis()
)

data class Exercise(
    val name: String,
    val sets: Int,
    val reps: Int
)