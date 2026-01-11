package com.loksewasathi.models

data class Question(
    val id: String,
    val topic: String,
    val question: String,
    val options: Map<String, String>,
    val correctAnswer: String,
    val explanation: String? = null
)

data class QuestionSet(
    val meta: MetaData,
    val questions: List<Question>
)

data class MetaData(
    val title: String,
    val language: String,
    val source: String,
    val sourceCitation: String? = null,
    val version: String,
    val count: Int
)
