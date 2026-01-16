package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class AverageDurationSleepSession implements SleepAnalysisFunction{

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long averageDuration;
        if (sleepingSessions.isEmpty()){
            averageDuration = 0;
        }else {
            long totalDuration = sleepingSessions.stream()
                    .map(sleepingSession ->
                            Duration.between(sleepingSession.timeFallAsleep, sleepingSession.timeWakeUp))
                    .mapToLong(Duration::toMinutes).sum();
            averageDuration = totalDuration/sleepingSessions.size();
        }
        return new SleepAnalysisResult("Средняя продолжительность сессии (в минутах): "+ averageDuration);
    }
}
