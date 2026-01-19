package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class UserClassifier implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long countOwl = sleepingSessions.stream()
                .filter(s -> s.timeFallAsleep.getHour() > 22 || s.timeFallAsleep.getHour() <= 3)
                .count();

        long countLark = sleepingSessions.stream()
                .filter(s -> s.timeFallAsleep.getHour() < 22 &&
                        s.timeWakeUp.getHour() <= 7)
                .count();

        UserType userType;
        if (countOwl > countLark) {
            userType = UserType.OWL;
        } else if (countLark > countOwl) {
            userType = UserType.LARK;
        } else {
            userType = UserType.PIGEON;
        }

        return new SleepAnalysisResult("Тип пользователя: " + userType);
    }
}
