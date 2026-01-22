package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerAppTest {

    SleepTrackerApp sleepTrackerApp;
    List<SleepingSession> sessions;

    @BeforeEach
    void setUp() {
        sleepTrackerApp = new SleepTrackerApp();
        sessions = new ArrayList<>();
    }

    @Test
    void testCountOfSleepSessionsForZeroSessions() {
        CountOfSleepSessions countOfSleepSessions = new CountOfSleepSessions();
        sleepTrackerApp.addFunction(countOfSleepSessions);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество сессий сна: 0", results.getFirst().toString());
    }

    @Test
    void testCountOfSleepSessionsForTenSessions() {
        CountOfSleepSessions countOfSleepSessions = new CountOfSleepSessions();
        for (int i = 0; i < 10; i++) {
            sessions.add(new SleepingSession(LocalDateTime.MIN, LocalDateTime.MAX, SleepAssessment.BAD));
        }
        sleepTrackerApp.addFunction(countOfSleepSessions);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество сессий сна: 10", results.getFirst().toString());
    }

    @Test
    void testCountOfBadSleepSessionsForZeroSessions() {
        CountOfBadSleepSessions countOfBadSleepSessions = new CountOfBadSleepSessions();
        sleepTrackerApp.addFunction(countOfBadSleepSessions);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество сессий с плохим качество сна: 0", results.getFirst().toString());
    }

    @Test
    void testCountOfBadSleepSessionsForTenSessionsBad() {
        CountOfBadSleepSessions countOfBadSleepSessions = new CountOfBadSleepSessions();
        for (int i = 0; i < 10; i++) {
            sessions.add(new SleepingSession(LocalDateTime.MIN, LocalDateTime.MAX, SleepAssessment.BAD));
        }
        sleepTrackerApp.addFunction(countOfBadSleepSessions);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество сессий с плохим качество сна: 10", results.getFirst().toString());
    }

    @Test
    void testCountOfBadSleepSessionsForTenSessionsAndFiveBad() {
        CountOfBadSleepSessions countOfBadSleepSessions = new CountOfBadSleepSessions();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                sessions.add(new SleepingSession(LocalDateTime.MIN, LocalDateTime.MAX, SleepAssessment.BAD));
            }
            sessions.add(new SleepingSession(LocalDateTime.MIN, LocalDateTime.MAX, SleepAssessment.GOOD));
        }
        sleepTrackerApp.addFunction(countOfBadSleepSessions);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество сессий с плохим качество сна: 5", results.getFirst().toString());
    }

    @Test
    void testMaximumDurationSleepSessionForZeroSessions() {
        MaximumDurationSleepSession maximumDurationSleepSession = new MaximumDurationSleepSession();
        sleepTrackerApp.addFunction(maximumDurationSleepSession);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Максимальная продолжительность сессии (в минутах): 0",
                results.getFirst().toString());
    }

    @Test
    void testMaximumDurationSleepSessionForFiveSessions() {
        MaximumDurationSleepSession maximumDurationSleepSession = new MaximumDurationSleepSession();
        LocalDateTime time = LocalDateTime.of(2025, 2, 2, 12, 0);

        for (int i = 1; i <= 5; i++) {
            sessions.add(new SleepingSession(time, time.plusMinutes(i * 10), SleepAssessment.BAD));
        }
        sleepTrackerApp.addFunction(maximumDurationSleepSession);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Максимальная продолжительность сессии (в минутах): 50",
                results.getFirst().toString());
    }

    @Test
    void testMinimumDurationSleepSessionForZeroSessions() {
        MinimumDurationSleepSession minimumDurationSleepSession = new MinimumDurationSleepSession();
        sleepTrackerApp.addFunction(minimumDurationSleepSession);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Минимальная продолжительность сессии (в минутах): 0",
                results.getFirst().toString());
    }

    @Test
    void testMinimumDurationSleepSessionForFiveSessions() {
        MinimumDurationSleepSession minimumDurationSleepSession = new MinimumDurationSleepSession();
        LocalDateTime time = LocalDateTime.of(2025, 2, 2, 12, 0);

        for (int i = 1; i <= 5; i++) {
            sessions.add(new SleepingSession(time, time.plusMinutes(i * 10), SleepAssessment.BAD));
        }
        sleepTrackerApp.addFunction(minimumDurationSleepSession);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Минимальная продолжительность сессии (в минутах): 10",
                results.getFirst().toString());
    }

    @Test
    void testAverageDurationSleepSessionForZeroSessions() {
        AverageDurationSleepSession averageDurationSleepSession = new AverageDurationSleepSession();
        sleepTrackerApp.addFunction(averageDurationSleepSession);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Средняя продолжительность сессии (в минутах): 0",
                results.getFirst().toString());
    }

    @Test
    void testAverageDurationSleepSessionForFiveSessions() {
        AverageDurationSleepSession averageDurationSleepSession = new AverageDurationSleepSession();
        LocalDateTime time = LocalDateTime.of(2025, 2, 2, 12, 0);
        int averageDuration = 0;
        for (int i = 1; i <= 5; i++) {
            sessions.add(new SleepingSession(time, time.plusMinutes(i * 10), SleepAssessment.BAD));
            averageDuration = averageDuration + i * 10;
        }
        averageDuration = averageDuration / sessions.size();
        sleepTrackerApp.addFunction(averageDurationSleepSession);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Средняя продолжительность сессии (в минутах): " + averageDuration,
                results.getFirst().toString());
    }

    @Test
    void testCountOfSleeplessNightsForZeroSessions() {
        CountOfSleeplessNights count = new CountOfSleeplessNights();
        sleepTrackerApp.addFunction(count);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество бессонных ночей: 0", results.getFirst().toString());

    }

    @Test
    void testCountOfSleeplessNightsWithSleepSession() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 6, 0),
                SleepAssessment.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 7, 0),
                SleepAssessment.GOOD));
        CountOfSleeplessNights count = new CountOfSleeplessNights();
        sleepTrackerApp.addFunction(count);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество бессонных ночей: 0", results.getFirst().toString());
    }

    @Test
    void testCountOfSleeplessNightsForSomeSleepless() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 6, 0),
                SleepAssessment.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 30),
                LocalDateTime.of(2025, 10, 4, 7, 0),
                SleepAssessment.GOOD));
        CountOfSleeplessNights count = new CountOfSleeplessNights();
        sleepTrackerApp.addFunction(count);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество бессонных ночей: 1", results.getFirst().toString());
    }

    @Test
    void testCountOfSleeplessNightsForDaytimeOnly() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 14, 0),
                LocalDateTime.of(2025, 10, 1, 15, 30),
                SleepAssessment.NORMAL));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 13, 0),
                LocalDateTime.of(2025, 10, 2, 14, 0),
                SleepAssessment.NORMAL));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 13, 0),
                LocalDateTime.of(2025, 10, 3, 14, 0),
                SleepAssessment.NORMAL));
        CountOfSleeplessNights count = new CountOfSleeplessNights();
        sleepTrackerApp.addFunction(count);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество бессонных ночей: 0", results.getFirst().toString());
    }

    @Test
    void testUserClassifierForOwl() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 30),
                LocalDateTime.of(2025, 10, 2, 10, 0),
                SleepAssessment.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 15),
                LocalDateTime.of(2025, 10, 3, 9, 30),
                SleepAssessment.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 0, 45),
                LocalDateTime.of(2025, 10, 4, 10, 30),
                SleepAssessment.GOOD));
        UserClassifier userClassifier = new UserClassifier();
        sleepTrackerApp.addFunction(userClassifier);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Тип пользователя: Сова", results.getFirst().toString());
    }

    @Test
    void testUserClassifierForLark() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 20, 30),
                LocalDateTime.of(2025, 10, 2, 6, 0),
                SleepAssessment.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 20, 45),
                LocalDateTime.of(2025, 10, 3, 5, 30),
                SleepAssessment.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 21, 45),
                LocalDateTime.of(2025, 10, 4, 6, 30),
                SleepAssessment.GOOD));
        UserClassifier userClassifier = new UserClassifier();
        sleepTrackerApp.addFunction(userClassifier);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Тип пользователя: Жаворонок", results.getFirst().toString());
    }

    @Test
    void testUserClassifierForPigeon() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 30),
                LocalDateTime.of(2025, 10, 2, 8, 0),
                SleepAssessment.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 45),
                LocalDateTime.of(2025, 10, 3, 9, 0),
                SleepAssessment.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 21, 0),
                LocalDateTime.of(2025, 10, 4, 6, 0),
                SleepAssessment.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, 10, 4, 20, 30),
                LocalDateTime.of(2025, 10, 5, 5, 45),
                SleepAssessment.GOOD));
        UserClassifier userClassifier = new UserClassifier();
        sleepTrackerApp.addFunction(userClassifier);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Тип пользователя: Голубь", results.getFirst().toString());
    }
}