package com.loksewasathi.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loksewasathi.models.Question
import com.loksewasathi.models.QuizAnswer
import com.loksewasathi.models.QuizResult
import com.loksewasathi.repository.QuestionRepository
import com.loksewasathi.repository.ProgressRepository
import com.loksewasathi.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class QuizState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswer: String? = null,
    val isAnswerSubmitted: Boolean = false,
    val quizAnswers: List<QuizAnswer> = emptyList(),
    val isLoading: Boolean = true,
    val startTime: Long = 0L,
    val quizResult: QuizResult? = null
)

class QuizViewModel(
    private val questionRepository: QuestionRepository,
    private val progressRepository: ProgressRepository,
    private val topicId: String
) : ViewModel() {
    
    private val _state = MutableStateFlow(QuizState())
    val state: StateFlow<QuizState> = _state.asStateFlow()
    
    init {
        loadQuestions()
    }
    
    private fun loadQuestions() {
        viewModelScope.launch {
            val topic = Constants.getTopicById(topicId)
            if (topic != null) {
                val questions = questionRepository.getRandomQuestions(
                    topic.jsonFileName,
                    Constants.QUESTIONS_PER_QUIZ
                )
                _state.value = _state.value.copy(
                    questions = questions,
                    isLoading = false,
                    startTime = System.currentTimeMillis()
                )
            }
        }
    }
    
    fun selectAnswer(answer: String) {
        if (!_state.value.isAnswerSubmitted) {
            _state.value = _state.value.copy(selectedAnswer = answer)
        }
    }
    
    fun submitAnswer() {
        val currentState = _state.value
        if (currentState.selectedAnswer == null || currentState.isAnswerSubmitted) return
        
        val currentQuestion = currentState.questions[currentState.currentQuestionIndex]
        val isCorrect = currentState.selectedAnswer == currentQuestion.correctAnswer
        
        val quizAnswer = QuizAnswer(
            questionId = currentQuestion.id,
            question = currentQuestion.question,
            selectedAnswer = currentState.selectedAnswer!!,
            correctAnswer = currentQuestion.correctAnswer,
            options = currentQuestion.options,
            isCorrect = isCorrect,
            explanation = currentQuestion.explanation
        )
        
        _state.value = currentState.copy(
            isAnswerSubmitted = true,
            quizAnswers = currentState.quizAnswers + quizAnswer
        )
    }
    
    fun nextQuestion() {
        val currentState = _state.value
        if (currentState.currentQuestionIndex < currentState.questions.size - 1) {
            _state.value = currentState.copy(
                currentQuestionIndex = currentState.currentQuestionIndex + 1,
                selectedAnswer = null,
                isAnswerSubmitted = false
            )
        } else {
            finishQuiz()
        }
    }
    
    private fun finishQuiz() {
        val currentState = _state.value
        val correctAnswers = currentState.quizAnswers.count { it.isCorrect }
        val timeTaken = (System.currentTimeMillis() - currentState.startTime) / 1000
        val topic = Constants.getTopicById(topicId)
        
        if (topic != null) {
            val result = QuizResult.calculate(
                topicId = topicId,
                topicName = topic.nameEnglish,
                totalQuestions = currentState.questions.size,
                correctAnswers = correctAnswers,
                timeTaken = timeTaken
            )
            
            _state.value = currentState.copy(quizResult = result)
            
            viewModelScope.launch {
                progressRepository.saveQuizResult(result)
            }
        }
    }
    
    fun getCurrentQuestion(): Question? {
        val currentState = _state.value
        return if (currentState.questions.isNotEmpty()) {
            currentState.questions[currentState.currentQuestionIndex]
        } else null
    }
}
