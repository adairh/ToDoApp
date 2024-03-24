package me.sjihh.todoapp.Handler.Handlers;

import me.sjihh.todoapp.Handler.EnumHandler;
import me.sjihh.todoapp.Handler.Handlers.DBHandler;
import me.sjihh.todoapp.Handler.IHandler;
import me.sjihh.todoapp.Handler.MainHandler;
import me.sjihh.todoapp.ToDoItem;

public class ToDoHandler implements IHandler {

    DBHandler dbHandler = null;

    public ToDoHandler() {
        if (MainHandler.getHandler(EnumHandler.DB).type() != EnumHandler.NULL) {
            dbHandler = (DBHandler) MainHandler.getHandler(EnumHandler.DB);
        }
    }

    public boolean addToDo(ToDoItem item) {
        if (dbHandler != null) {
            return dbHandler.addTodoItem(item);
        } else {
            return false;
        }
    }

    public boolean deleteToDo(ToDoItem item) {
        if (dbHandler != null) {
            if (item.getId() >= 0) {
                return dbHandler.removeTodoItem(item);
            }
        }
        return false;
    }

    public boolean updateToDo(ToDoItem item) {
        if (dbHandler != null) {
            if (item.getId() >= 0) {
                return dbHandler.updateTodoItem(item);
            }
        }
        return false;
    }

    @Override
    public EnumHandler type() {
        return EnumHandler.TODO;
    }
}
