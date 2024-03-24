package me.sjihh.todoapp.Handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.sjihh.todoapp.Handler.Handlers.NuLLHandler;

public class MainHandler implements IHandler {
    private static List<IHandler> workingHandler = new ArrayList<>();
    @Override
    public EnumHandler type() {
        return EnumHandler.MAIN;
    }

    public static IHandler getHandler(EnumHandler handler) {
        for (IHandler h : workingHandler) {
            if (h.type().equals(handler)) {
                return h;
            }
        }
        return new NuLLHandler();
    }

    public static boolean hasHandler(IHandler handler) {
        for (IHandler h : workingHandler) {
            if (h.type().equals(handler.type())) {
                return true;
            }
        }
        return false;
    }
    public static void addOrUpdateHandler(IHandler handler) {
        if (!hasHandler(handler)) {
            workingHandler.add(handler);
        } else {
            updateHandler(handler);
        }
    }

    private static void updateHandler(IHandler handler) {
        if (hasHandler(handler)) {
            removeHandler(handler);
            workingHandler.add(handler);
        }
    }

    private static void removeHandler(IHandler handler) {
        if (hasHandler(handler)) {
            Iterator<IHandler> iterator = workingHandler.iterator();
            while (iterator.hasNext()) {
                IHandler h = iterator.next();
                if (h.type().equals(handler.type())) {
                    iterator.remove();
                }
            }
        }
    }
}
