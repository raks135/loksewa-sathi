package com.loksewasathi.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loksewasathi.models.QuizResult
import com.loksewasathi.models.UserProgress
import com.loksewasathi.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.DATASTORE_NAME)

class ProgressRepository(private val context: Context) {
    private val gson = Gson()
    
    private val userProgressKey = stringPreferencesKey(Constants.KEY_USER_PROGRESS)
    private val quizHistoryKey = stringPreferencesKey(Constants.KEY_QUIZ_HISTORY)
    
    // Save user progress for a topic
    suspend fun saveUserProgress(topicId: String, progress: UserProgress) {
        context.dataStore.edit { preferences ->
            val allProgress = getAllProgressMap(preferences)
            allProgress[topicId] = progress
            preferences[userProgressKey] = gson.toJson(allProgress)
        }
    }
    
    // Get user progress for a topic
    fun getUserProgress(topicId: String): Flow<UserProgress?> {
        return context.dataStore.data.map { preferences ->
            val allProgress = getAllProgressMap(preferences)
            allProgress[topicId]
        }
    }
    
    // Get all user progress
    fun getAllProgress(): Flow<Map<String, UserProgress>> {
        return context.dataStore.data.map { preferences ->
            getAllProgressMap(preferences)
        }
    }
    
    // Save quiz result
    suspend fun saveQuizResult(result: QuizResult) {
        context.dataStore.edit { preferences ->
            val history = getQuizHistoryList(preferences).toMutableList()
            history.add(result)
            preferences[quizHistoryKey] = gson.toJson(history)
            
            // Update user progress
            val allProgress = getAllProgressMap(preferences)
            val currentProgress = allProgress[result.topicId] ?: UserProgress(topicId = result.topicId)
            
            val updatedProgress = currentProgress.copy(
                questionsAttempted = currentProgress.questionsAttempted + result.totalQuestions,
                correctAnswers = currentProgress.correctAnswers + result.correctAnswers,
                totalQuizzesTaken = currentProgress.totalQuizzesTaken + 1,
                lastAttemptedTimestamp = result.timestamp,
                bestScore = maxOf(currentProgress.bestScore, result.score),
                averageAccuracy = ((currentProgress.averageAccuracy * currentProgress.totalQuizzesTaken) + result.accuracy) / (currentProgress.totalQuizzesTaken + 1)
            )
            
            allProgress[result.topicId] = updatedProgress
            preferences[userProgressKey] = gson.toJson(allProgress)
        }
    }
    
    // Get quiz history
    fun getQuizHistory(): Flow<List<QuizResult>> {
        return context.dataStore.data.map { preferences ->
            getQuizHistoryList(preferences)
        }
    }
    
    // Get quiz history for a specific topic
    fun getQuizHistoryByTopic(topicId: String): Flow<List<QuizResult>> {
        return context.dataStore.data.map { preferences ->
            getQuizHistoryList(preferences).filter { it.topicId == topicId }
        }
    }
    
    private fun getAllProgressMap(preferences: Preferences): MutableMap<String, UserProgress> {
        val json = preferences[userProgressKey] ?: return mutableMapOf()
        val type = object : TypeToken<MutableMap<String, UserProgress>>() {}.type
        return gson.fromJson(json, type) ?: mutableMapOf()
    }
    
    private fun getQuizHistoryList(preferences: Preferences): List<QuizResult> {
        val json = preferences[quizHistoryKey] ?: return emptyList()
        val type = object : TypeToken<List<QuizResult>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }
}
