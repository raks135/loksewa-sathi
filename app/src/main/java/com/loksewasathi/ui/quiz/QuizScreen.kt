package com.loksewasathi.ui.quiz

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.loksewasathi.repository.QuestionRepository
import com.loksewasathi.repository.ProgressRepository
import com.loksewasathi.ui.theme.CorrectGreen
import com.loksewasathi.ui.theme.IncorrectRed
import com.loksewasathi.ui.theme.PrimaryBlue
import com.loksewasathi.ui.theme.PrimaryPurple
import com.loksewasathi.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    topicId: String,
    onNavigateToResult: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val viewModel = remember {
        QuizViewModel(
            questionRepository = QuestionRepository(context),
            progressRepository = ProgressRepository(context),
            topicId = topicId
        )
    }
    
    val state by viewModel.state.collectAsState()
    val topic = Constants.getTopicById(topicId)
    
    // Auto-advance to next question after showing feedback
    LaunchedEffect(state.isAnswerSubmitted) {
        if (state.isAnswerSubmitted) {
            delay(1500) // Show feedback for 1.5 seconds
            viewModel.nextQuestion()
        }
    }
    
    LaunchedEffect(state.quizResult) {
        if (state.quizResult != null) {
            onNavigateToResult()
        }
    }
    
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = topic?.nameNepali ?: "",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Question ${state.currentQuestionIndex + 1}/${state.questions.size}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryPurple,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Progress Bar
            LinearProgressIndicator(
                progress = (state.currentQuestionIndex + 1).toFloat() / state.questions.size.toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = PrimaryBlue,
                trackColor = Color.LightGray.copy(alpha = 0.3f)
            )
            
            val currentQuestion = viewModel.getCurrentQuestion()
            
            if (currentQuestion != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Question
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Text(
                            text = currentQuestion.question,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Options
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        listOf("A", "B", "C", "D").forEach { optionKey ->
                            val optionText = currentQuestion.options[optionKey] ?: ""
                            OptionButton(
                                optionKey = optionKey,
                                optionText = optionText,
                                isSelected = state.selectedAnswer == optionKey,
                                isCorrect = currentQuestion.correctAnswer == optionKey,
                                isAnswerSubmitted = state.isAnswerSubmitted,
                                onClick = {
                                    if (!state.isAnswerSubmitted) {
                                        viewModel.selectAnswer(optionKey)
                                        viewModel.submitAnswer() // Auto-submit immediately
                                    }
                                }
                            )
                        }
                    }
                    
                    
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun OptionButton(
    optionKey: String,
    optionText: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    isAnswerSubmitted: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        isAnswerSubmitted && isCorrect -> CorrectGreen.copy(alpha = 0.2f)
        isAnswerSubmitted && isSelected && !isCorrect -> IncorrectRed.copy(alpha = 0.2f)
        isSelected -> PrimaryBlue.copy(alpha = 0.1f)
        else -> MaterialTheme.colorScheme.surface
    }
    
    val borderColor = when {
        isAnswerSubmitted && isCorrect -> CorrectGreen
        isAnswerSubmitted && isSelected && !isCorrect -> IncorrectRed
        isSelected -> PrimaryBlue
        else -> Color.LightGray.copy(alpha = 0.3f)
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(2.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable(enabled = !isAnswerSubmitted, onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Option Key (A, B, C, D)
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(borderColor.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = optionKey,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = borderColor
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Option Text
            Text(
                text = optionText,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            
            // Check/Cross Icon
            if (isAnswerSubmitted) {
                Text(
                    text = if (isCorrect) "✓" else if (isSelected) "✗" else "",
                    style = MaterialTheme.typography.titleLarge,
                    color = if (isCorrect) CorrectGreen else IncorrectRed
                )
            }
        }
    }
}
