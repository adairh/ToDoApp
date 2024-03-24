package me.sjihh.todoapp.Handler.Handlers;

import me.sjihh.todoapp.Handler.EnumHandler;
import me.sjihh.todoapp.Handler.IHandler;
import me.sjihh.todoapp.ToDoItem;

public class ProgressHandler implements IHandler {
    @Override
    public EnumHandler type() {
        return EnumHandler.PRG;
    }

    private ToDoItem trackingItem = null;

    public void setTrackingItem(ToDoItem trackingItem) {
        this.trackingItem = trackingItem;
    }

    public ToDoItem getTrackingItem() {
        return trackingItem;
    }

    public void stopTrackingItem() {
        trackingItem = null;
    }
}
