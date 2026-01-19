package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    final LocalDateTime timeFallAsleep;
    final LocalDateTime timeWakeUp;
    final SleepAssessment assessment;

    public SleepingSession(LocalDateTime timeFallAsleep, LocalDateTime timeWakeUp, SleepAssessment assessment) {
        this.timeFallAsleep = timeFallAsleep;
        this.timeWakeUp = timeWakeUp;
        this.assessment = assessment;
    }
}
