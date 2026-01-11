package com.loksewasathi.models

data class QuizAnswer(
    val questionId: String,
    val question: String,
    val selectedAnswer: String,
    val correctAnswer: String,
    val options: Map<String, String>,
    val isCorrect: Boolean,
    val explanation: String? = null
)
