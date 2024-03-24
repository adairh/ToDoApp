package me.sjihh.todoapp.Handler;

public enum EnumHandler {
    MAIN("Main"),
    TODO("ToDo"),
    DB("DB"),
    PRG("Progress"),
    NULL("Null");

    private String name;
    EnumHandler(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
