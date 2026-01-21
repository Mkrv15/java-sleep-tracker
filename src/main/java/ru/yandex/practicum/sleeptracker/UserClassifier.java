package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;

public class UserClassifier implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long countOwl = sleepingSessions.stream()
                .filter(s -> (s.timeFallAsleep.toLocalTime().isAfter(LocalTime.of(23, 0))
                        || s.timeFallAsleep.toLocalTime().isBefore(LocalTime.of(3, 0)))
                        && s.timeWakeUp.toLocalTime().isAfter(LocalTime.of(9, 0)))
                .count();

        long countLark = sleepingSessions.stream()
                .filter(s -> s.timeFallAsleep.toLocalTime().isBefore(LocalTime.of(22, 0))
                        && s.timeWakeUp.toLocalTime().isBefore(LocalTime.of(7, 0)))
                .count();
        long countNights = sleepingSessions.stream()
                .filter(s -> s.timeFallAsleep.toLocalTime().isAfter(LocalTime.of(18, 0))
                        && s.timeWakeUp.toLocalTime().isBefore(LocalTime.of(13, 0)))
                .count();
        long countPigeon = countNights - countLark - countOwl;

        UserType userType;
        if (countOwl > countLark && countOwl > countPigeon) {
            userType = UserType.OWL;
        } else if (countLark > countOwl && countLark > countPigeon) {
            userType = UserType.LARK;
        } else {
            userType = UserType.PIGEON;
        }

        return new SleepAnalysisResult("Тип пользователя: " + userType);
    }
}
