package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;


public class MinimumDurationSleepSession implements SleepAnalysisFunction {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long minDuration = sleepingSessions.stream()
                .map(sleepingSession ->
                        Duration.between(sleepingSession.timeFallAsleep, sleepingSession.timeWakeUp))
                .min(Duration::compareTo).orElseGet(() -> Duration.ofMinutes(0)).toMinutes();

        return new SleepAnalysisResult("Минимальная продолжительность сессии (в минутах): " + minDuration);
    }
}
