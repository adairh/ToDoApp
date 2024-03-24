package me.sjihh.todoapp.Handler.Handlers;


import me.sjihh.todoapp.Handler.EnumHandler;
import me.sjihh.todoapp.Handler.IHandler;

public class NuLLHandler implements IHandler {
    @Override
    public EnumHandler type() {
        return EnumHandler.NULL;
    }
}
