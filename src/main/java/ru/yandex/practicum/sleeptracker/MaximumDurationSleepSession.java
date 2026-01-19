package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class MaximumDurationSleepSession implements SleepAnalysisFunction {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long maxDuration = sleepingSessions.stream()
                .map(sleepingSession ->
                        Duration.between(sleepingSession.timeFallAsleep, sleepingSession.timeWakeUp))
                .max(Duration::compareTo).orElseGet(() -> Duration.ofMinutes(0)).toMinutes();

        return new SleepAnalysisResult("Максимальная продолжительность сессии (в минутах): " + maxDuration);
    }
}
