package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
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
                            sleepingSession.timeFallAsleep.getDayOfMonth() < sleepingSession.timeWakeUp.getDayOfMonth()
                                    || sleepingSession.timeFallAsleep.getHour() < 6
                                    || sleepingSession.timeWakeUp.getHour() > 22).count();

            LocalDate startDate = sleepingSessions.getFirst().timeFallAsleep.toLocalDate();
            LocalDate endDate = sleepingSessions.getLast().timeWakeUp.toLocalDate();

            Period period = Period.between(startDate, endDate);
            int countNights = period.getDays();
            result = countNights - countNightsSession;
        }
        return new SleepAnalysisResult("Количество бессонных ночей: " + result);
    }
}
