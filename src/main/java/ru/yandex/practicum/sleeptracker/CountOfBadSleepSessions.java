package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class CountOfBadSleepSessions implements SleepAnalysisFunction {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long countSessions = sleepingSessions.stream()
                .filter(sleepingSession -> sleepingSession.assessment == SleepAssessment.BAD)
                .count();
        return new SleepAnalysisResult("Количество сессий с плохим качество сна: " + countSessions);
    }
}
