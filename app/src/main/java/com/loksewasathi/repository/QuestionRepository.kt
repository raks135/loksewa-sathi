package com.loksewasathi.repository

import android.content.Context
import com.loksewasathi.models.Question
import com.loksewasathi.models.QuestionSet
import com.loksewasathi.utils.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionRepository(private val context: Context) {
    
    suspend fun loadQuestionsByTopic(jsonFileName: String): List<Question> = withContext(Dispatchers.IO) {
        val questionSet = JsonParser.loadQuestionsFromAssets(context, jsonFileName)
        questionSet?.questions ?: emptyList()
    }
    
    suspend fun getRandomQuestions(jsonFileName: String, count: Int): List<Question> = withContext(Dispatchers.IO) {
        val allQuestions = loadQuestionsByTopic(jsonFileName)
        if (allQuestions.size <= count) {
            allQuestions.shuffled()
        } else {
            allQuestions.shuffled().take(count)
        }
    }
    
    suspend fun getQuestionSetMeta(jsonFileName: String): QuestionSet? = withContext(Dispatchers.IO) {
        JsonParser.loadQuestionsFromAssets(context, jsonFileName)
    }
}
