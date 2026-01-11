package com.loksewasathi.utils

import com.loksewasathi.models.Topic
import com.loksewasathi.ui.theme.*

object Constants {
    const val APP_NAME = "Loksewa Sathi"
    const val QUESTIONS_PER_QUIZ = 20
    const val POINTS_PER_QUESTION = 10
    
    // DataStore Keys
    const val DATASTORE_NAME = "loksewa_sathi_prefs"
    const val KEY_USER_PROGRESS = "user_progress"
    const val KEY_QUIZ_HISTORY = "quiz_history"
    const val KEY_DARK_MODE = "dark_mode"
    
    // Topic IDs
    const val TOPIC_WORLD_GEOGRAPHY = "world_geography"
    const val TOPIC_NEPAL_GEOGRAPHY = "nepal_geography"
    const val TOPIC_NEPAL_HISTORY = "nepal_history"
    const val TOPIC_WORLD_HISTORY = "world_history"
    const val TOPIC_GENERAL_SCIENCE = "general_science"
    const val TOPIC_BIOLOGY = "biology"
    const val TOPIC_PHYSICS = "physics"
    const val TOPIC_CHEMISTRY = "chemistry"
    const val TOPIC_CONSTITUTION = "constitution"
    const val TOPIC_CURRENT_AFFAIRS = "current_affairs"
    const val TOPIC_GENERAL_AWARENESS = "general_awareness"
    const val TOPIC_MENTAL_ABILITY = "mental_ability"
    
    // Get all topics
    fun getAllTopics(): List<Topic> = listOf(
        Topic(
            id = TOPIC_WORLD_GEOGRAPHY,
            nameEnglish = "World Geography",
            nameNepali = "विश्व भूगोल",
            icon = "🌍",
            gradientColors = listOf(GeographyBlue, GeographyTeal),
            questionCount = 20,
            description = "Physical, Political, Economic Geography",
            jsonFileName = "world-geography.json"
        ),
        Topic(
            id = TOPIC_NEPAL_GEOGRAPHY,
            nameEnglish = "Nepal Geography",
            nameNepali = "नेपाल भूगोल",
            icon = "🏔️",
            gradientColors = listOf(GeographyTeal, ScienceGreen),
            questionCount = 20,
            description = "Nepal's Physical & Political Geography",
            jsonFileName = "nepal-geography.json"
        ),
        Topic(
            id = TOPIC_NEPAL_HISTORY,
            nameEnglish = "Nepal History",
            nameNepali = "नेपालको इतिहास",
            icon = "🏛️",
            gradientColors = listOf(HistoryOrange, HistoryAmber),
            questionCount = 20,
            description = "Ancient, Medieval, Modern History",
            jsonFileName = "nepal-history.json"
        ),
        Topic(
            id = TOPIC_WORLD_HISTORY,
            nameEnglish = "World History",
            nameNepali = "विश्व इतिहास",
            icon = "📜",
            gradientColors = listOf(HistoryAmber, HistoryOrange),
            questionCount = 20,
            description = "Major World Historical Events",
            jsonFileName = "world-history.json"
        ),
        Topic(
            id = TOPIC_GENERAL_SCIENCE,
            nameEnglish = "General Science",
            nameNepali = "सामान्य विज्ञान",
            icon = "🔬",
            gradientColors = listOf(ScienceGreen, ScienceLime),
            questionCount = 20,
            description = "Basic Science Concepts",
            jsonFileName = "general-science.json"
        ),
        Topic(
            id = TOPIC_BIOLOGY,
            nameEnglish = "Biology",
            nameNepali = "जीवविज्ञान",
            icon = "🧬",
            gradientColors = listOf(ScienceLime, ScienceGreen),
            questionCount = 20,
            description = "Life Science & Biology",
            jsonFileName = "biology.json"
        ),
        Topic(
            id = TOPIC_PHYSICS,
            nameEnglish = "Physics",
            nameNepali = "भौतिकशास्त्र",
            icon = "⚛️",
            gradientColors = listOf(AwarenessBlue, AwarenessCyan),
            questionCount = 20,
            description = "Physical Science & Mechanics",
            jsonFileName = "physics.json"
        ),
        Topic(
            id = TOPIC_CHEMISTRY,
            nameEnglish = "Chemistry",
            nameNepali = "रसायनशास्त्र",
            icon = "⚗️",
            gradientColors = listOf(MentalAbilityViolet, MentalAbilityDeepPurple),
            questionCount = 20,
            description = "Chemical Science & Reactions",
            jsonFileName = "chemistry.json"
        ),
        Topic(
            id = TOPIC_CONSTITUTION,
            nameEnglish = "Constitution",
            nameNepali = "संविधान",
            icon = "⚖️",
            gradientColors = listOf(ConstitutionRed, ConstitutionPink),
            questionCount = 20,
            description = "Nepal Constitution & Laws",
            jsonFileName = "constitution.json"
        ),
        Topic(
            id = TOPIC_CURRENT_AFFAIRS,
            nameEnglish = "Current Affairs",
            nameNepali = "समसामयिक घटनाक्रम",
            icon = "📰",
            gradientColors = listOf(CurrentAffairsIndigo, CurrentAffairsPurple),
            questionCount = 20,
            description = "National & International Events",
            jsonFileName = "current-affairs.json"
        ),
        Topic(
            id = TOPIC_GENERAL_AWARENESS,
            nameEnglish = "General Awareness",
            nameNepali = "सामान्य ज्ञान",
            icon = "💡",
            gradientColors = listOf(AwarenessCyan, AwarenessBlue),
            questionCount = 20,
            description = "Literature, Arts, Culture",
            jsonFileName = "general-awareness.json"
        ),
        Topic(
            id = TOPIC_MENTAL_ABILITY,
            nameEnglish = "Mental Ability",
            nameNepali = "मानसिक क्षमता",
            icon = "🧠",
            gradientColors = listOf(MentalAbilityDeepPurple, MentalAbilityViolet),
            questionCount = 20,
            description = "IQ & Aptitude Tests",
            jsonFileName = "mental-ability.json"
        )
    )
    
    fun getTopicById(id: String): Topic? {
        return getAllTopics().find { it.id == id }
    }
}
