package com.loksewasathi.ui.review

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.loksewasathi.ui.theme.CorrectGreen
import com.loksewasathi.ui.theme.IncorrectRed
import com.loksewasathi.ui.theme.PrimaryPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(
    onNavigateBack: () -> Unit
) {
    // Mock data - in real app, this would come from ViewModel
    val mockAnswers = listOf(
        ReviewItem(
            questionNumber = 1,
            question = "What is the capital of Nepal?",
            selectedAnswer = "A. Kathmandu",
            correctAnswer = "A. Kathmandu",
            isCorrect = true
        ),
        ReviewItem(
            questionNumber = 2,
            question = "Which is the highest mountain in the world?",
            selectedAnswer = "B. K2",
            correctAnswer = "A. Mount Everest",
            isCorrect = false
        )
    )
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Review Answers",
                        fontWeight = FontWeight.Bold
                    )
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(mockAnswers) { index, item ->
                ReviewCard(item = item)
            }
        }
    }
}

data class ReviewItem(
    val questionNumber: Int,
    val question: String,
    val selectedAnswer: String,
    val correctAnswer: String,
    val isCorrect: Boolean
)

@Composable
fun ReviewCard(item: ReviewItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Question Number and Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Question ${item.questionNumber}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                
                Box(
                    modifier = Modifier
                        .background(
                            color = if (item.isCorrect) CorrectGreen.copy(alpha = 0.2f) else IncorrectRed.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (item.isCorrect) "✓ Correct" else "✗ Incorrect",
                        color = if (item.isCorrect) CorrectGreen else IncorrectRed,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Question
            Text(
                text = item.question,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Your Answer
            AnswerBox(
                label = "Your Answer",
                answer = item.selectedAnswer,
                isCorrect = item.isCorrect,
                showBorder = true
            )
            
            // Correct Answer (if wrong)
            if (!item.isCorrect) {
                Spacer(modifier = Modifier.height(12.dp))
                AnswerBox(
                    label = "Correct Answer",
                    answer = item.correctAnswer,
                    isCorrect = true,
                    showBorder = true
                )
            }
        }
    }
}

@Composable
fun AnswerBox(
    label: String,
    answer: String,
    isCorrect: Boolean,
    showBorder: Boolean
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (showBorder) {
                        Modifier.border(
                            width = 2.dp,
                            color = if (isCorrect) CorrectGreen else IncorrectRed,
                            shape = RoundedCornerShape(8.dp)
                        )
                    } else Modifier
                )
                .background(
                    color = if (isCorrect) CorrectGreen.copy(alpha = 0.1f) else IncorrectRed.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp)
        ) {
            Text(
                text = answer,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
