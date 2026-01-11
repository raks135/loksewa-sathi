package com.loksewasathi.models

data class QuizResult(
    val topicId: String,
    val topicName: String,
    val score: Int,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val incorrectAnswers: Int,
    val timeTaken: Long, // in seconds
    val timestamp: Long,
    val accuracy: Float
) {
    companion object {
        fun calculate(
            topicId: String,
            topicName: String,
            totalQuestions: Int,
            correctAnswers: Int,
            timeTaken: Long
        ): QuizResult {
            val incorrectAnswers = totalQuestions - correctAnswers
            val accuracy = if (totalQuestions > 0) {
                (correctAnswers.toFloat() / totalQuestions.toFloat()) * 100
            } else 0f
            
            return QuizResult(
                topicId = topicId,
                topicName = topicName,
                score = correctAnswers * 10, // 10 points per correct answer
                totalQuestions = totalQuestions,
                correctAnswers = correctAnswers,
                incorrectAnswers = incorrectAnswers,
                timeTaken = timeTaken,
                timestamp = System.currentTimeMillis(),
                accuracy = accuracy
            )
        }
    }
}
