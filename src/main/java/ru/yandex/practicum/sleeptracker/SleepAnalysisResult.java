package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {
    private String result;

    public SleepAnalysisResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return result;
    }
}
