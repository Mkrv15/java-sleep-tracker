package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;

public class CountOfSleeplessNights implements SleepAnalysisFunction {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long result;
        if (sleepingSessions.isEmpty()) {
            result = 0;
        } else {
            long countNightsSession = sleepingSessions.stream()
                    .filter(sleepingSession ->
                            !(sleepingSession.timeFallAsleep.toLocalTime()
                                    .isBefore(LocalTime.of(6, 0))
                                    || sleepingSession.timeWakeUp.toLocalTime()
                                    .isBefore(LocalTime.of(6, 0))))
                    .count();

            LocalDate startDate = sleepingSessions.getFirst().timeFallAsleep.toLocalDate();
            LocalDate endDate = sleepingSessions.getLast().timeWakeUp.toLocalDate();

            Period period = Period.between(startDate, endDate);
            int countNights = period.getDays();
            result = Math.max((countNights - countNightsSession), 0);
        }
        return new SleepAnalysisResult("Количество бессонных ночей: " + result);
    }
}