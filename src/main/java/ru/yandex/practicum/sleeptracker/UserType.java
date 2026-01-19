package ru.yandex.practicum.sleeptracker;

public enum UserType {
    PIGEON("Голубь"),
    OWL("Сова"),
    LARK("Жаворонок");

    private final String russianName;

    UserType(String russianName) {
        this.russianName = russianName;
    }

    @Override
    public String toString() {
        return russianName;
    }
}
