package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class CountOfSleepSessions implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        return new SleepAnalysisResult("Количество сессий сна: " + sleepingSessions.size());
    }
}
