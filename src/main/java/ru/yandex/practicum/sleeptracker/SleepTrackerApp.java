package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SleepTrackerApp {
    private List<SleepAnalysisFunction> functions = new ArrayList<>();
    private static List<SleepingSession> sleepingSessions = new ArrayList<>();
    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))){
            bufferedReader.lines()
                    .map(s -> s.split(";"))
                    .filter(strings -> strings.length == 3)
                    .forEach(strings -> sleepingSessions.add(
                            new SleepingSession(
                                    LocalDateTime.parse(strings[0], formatter),
                                    LocalDateTime.parse(strings[1], formatter),
                                    SleepAssessment.valueOf(strings[2]))));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        SleepTrackerApp sleepTrackerApp = new SleepTrackerApp();
//        sleepTrackerApp.addFunction(new CountOfSleepSessions());
//        sleepTrackerApp.addFunction(new MinimumDurationSleepSession());
//        sleepTrackerApp.addFunction(new MaximumDurationSleepSession());
//        sleepTrackerApp.addFunction(new AverageDurationSleepSession());
//        sleepTrackerApp.addFunction(new CountOfBadSleepSessions());
//        sleepTrackerApp.addFunction(new CountOfSleeplessNights());
//        System.out.println(sleepTrackerApp.executeFunctions(sleepingSessions));

    }

    public void addFunction(SleepAnalysisFunction function){
        functions.add(function);
    }
    public List<SleepAnalysisResult> executeFunctions(List<SleepingSession> sleepingSessions){
        List<SleepAnalysisResult> results = functions.stream()
                .map(function -> function.apply(sleepingSessions))
                .collect(Collectors.toList());
        return results;
    }
}