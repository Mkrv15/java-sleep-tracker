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
    void countOfSleepSessionsForZeroSessions() {
        CountOfSleepSessions countOfSleepSessions = new CountOfSleepSessions();
        sleepTrackerApp.addFunction(countOfSleepSessions);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество сессий сна: 0",results.getFirst().toString());
    }
    @Test
    void countOfSleepSessionsForTenSessions(){
        CountOfSleepSessions countOfSleepSessions = new CountOfSleepSessions();
        for (int i =0; i<10;i++){
        sessions.add(new SleepingSession(LocalDateTime.MIN,LocalDateTime.MAX,SleepAssessment.BAD));
        }
        sleepTrackerApp.addFunction(countOfSleepSessions);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество сессий сна: 10",results.getFirst().toString());
    }

    @Test
    void countOfBadSleepSessionsForZeroSessions() {
        CountOfBadSleepSessions countOfBadSleepSessions = new CountOfBadSleepSessions();
        sleepTrackerApp.addFunction(countOfBadSleepSessions);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество сессий с плохим качество сна: 0",results.getFirst().toString());
    }
    @Test
    void countOfBadSleepSessionsForTenSessionsBad(){
        CountOfBadSleepSessions countOfBadSleepSessions = new CountOfBadSleepSessions();
        for (int i =0; i<10;i++){
            sessions.add(new SleepingSession(LocalDateTime.MIN,LocalDateTime.MAX,SleepAssessment.BAD));
        }
        sleepTrackerApp.addFunction(countOfBadSleepSessions);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество сессий с плохим качество сна: 10",results.getFirst().toString());
    }
    @Test
    void countOfBadSleepSessionsForTenSessionsAndFiveBad(){
        CountOfBadSleepSessions countOfBadSleepSessions = new CountOfBadSleepSessions();
        for (int i =0; i<10;i++){
            if (i%2==0) {
                sessions.add(new SleepingSession(LocalDateTime.MIN, LocalDateTime.MAX, SleepAssessment.BAD));
            }
            sessions.add(new SleepingSession(LocalDateTime.MIN, LocalDateTime.MAX, SleepAssessment.GOOD));
        }
        sleepTrackerApp.addFunction(countOfBadSleepSessions);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Количество сессий с плохим качество сна: 5",results.getFirst().toString());
    }

    @Test
    void maximumDurationSleepSessionForZeroSessions(){
        MaximumDurationSleepSession maximumDurationSleepSession = new MaximumDurationSleepSession();
        sleepTrackerApp.addFunction(maximumDurationSleepSession);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Максимальная продолжительность сессии (в минутах): 0"
                ,results.getFirst().toString());
    }
    @Test
    void maximumDurationSleepSessionForFiveSessions(){
        MaximumDurationSleepSession maximumDurationSleepSession = new MaximumDurationSleepSession();
        LocalDateTime time = LocalDateTime.of(2025,2,2,12,0);

        for (int i =1;i<=5; i++){
            sessions.add(new SleepingSession(time
                    ,time.plusMinutes(i*10),SleepAssessment.BAD));
        }
        sleepTrackerApp.addFunction(maximumDurationSleepSession);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Максимальная продолжительность сессии (в минутах): 50"
                ,results.getFirst().toString());
    }

    @Test
    void minimumDurationSleepSessionForZeroSessions(){
        MinimumDurationSleepSession minimumDurationSleepSession = new MinimumDurationSleepSession();
        sleepTrackerApp.addFunction(minimumDurationSleepSession);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Минимальная продолжительность сессии (в минутах): 0"
                ,results.getFirst().toString());
    }
    @Test
    void minimumDurationSleepSessionForFiveSessions(){
        MinimumDurationSleepSession minimumDurationSleepSession = new MinimumDurationSleepSession();
        LocalDateTime time = LocalDateTime.of(2025,2,2,12,0);

        for (int i =1;i<=5; i++){
            sessions.add(new SleepingSession(time
                    ,time.plusMinutes(i*10),SleepAssessment.BAD));
        }
        sleepTrackerApp.addFunction(minimumDurationSleepSession);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Минимальная продолжительность сессии (в минутах): 10"
                ,results.getFirst().toString());
    }

    @Test
    void averageDurationSleepSessionForZeroSessions(){
        AverageDurationSleepSession averageDurationSleepSession = new AverageDurationSleepSession();
        sleepTrackerApp.addFunction(averageDurationSleepSession);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Средняя продолжительность сессии (в минутах): 0"
                ,results.getFirst().toString());
    }
    @Test
    void averageDurationSleepSessionForFiveSessions(){
        AverageDurationSleepSession averageDurationSleepSession = new AverageDurationSleepSession();
        LocalDateTime time = LocalDateTime.of(2025,2,2,12,0);
        int averageDuration = 0;
        for (int i =1;i<=5; i++){
            sessions.add(new SleepingSession(time
                    ,time.plusMinutes(i*10),SleepAssessment.BAD));
            averageDuration = averageDuration+i*10;
        }
        averageDuration = averageDuration/sessions.size();
        sleepTrackerApp.addFunction(averageDurationSleepSession);
        List<SleepAnalysisResult> results = sleepTrackerApp.executeFunctions(sessions);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Средняя продолжительность сессии (в минутах): "+averageDuration
                ,results.getFirst().toString());
    }
}