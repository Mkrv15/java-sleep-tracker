package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class CountOfSleeplessNights implements SleepAnalysisFunction{
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        List<SleepingSession> countNightsSession = sleepingSessions.stream()
                .filter(sleepingSession ->
                        sleepingSession.timeFallAsleep.getDayOfMonth()<sleepingSession.timeWakeUp.getDayOfMonth()
                                ||sleepingSession.timeFallAsleep.getHour()<6
                                ||sleepingSession.timeWakeUp.getHour()>22)
                .toList();
        int countNights = Period.between(countNightsSession.getFirst().timeFallAsleep.toLocalDate()
                ,countNightsSession.getLast().timeFallAsleep.toLocalDate()).getDays();

        return new SleepAnalysisResult("Количество бессонных ночей: " + countNights);
    }
}
