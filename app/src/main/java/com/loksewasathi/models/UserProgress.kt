package com.loksewasathi.models

data class UserProgress(
    val topicId: String,
    val questionsAttempted: Int = 0,
    val correctAnswers: Int = 0,
    val totalQuizzesTaken: Int = 0,
    val lastAttemptedTimestamp: Long = 0L,
    val bestScore: Int = 0,
    val averageAccuracy: Float = 0f
) {
    fun getAccuracy(): Float {
        return if (questionsAttempted > 0) {
            (correctAnswers.toFloat() / questionsAttempted.toFloat()) * 100
        } else 0f
    }
}
