package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerApp {
    public static void main(String[] args) {
        List<SleepingSession> sleepingSessions = new ArrayList<>();
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
    }
}