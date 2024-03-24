package me.sjihh.todoapp.Handler.Handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.sjihh.todoapp.Handler.EnumHandler;
import me.sjihh.todoapp.Handler.IHandler;
import me.sjihh.todoapp.ToDoItem;

public class DBHandler extends SQLiteOpenHelper implements IHandler {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todo_db";
    private static final String TABLE_TODO = "todo";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DUE_DATE = "dueDate";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_TODO + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT,"
                + "description TEXT,"
                + "dueDate LONG)";
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    public boolean addTodoItem(ToDoItem todoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHandler.COLUMN_TITLE, todoItem.getTitle());
        values.put(DBHandler.COLUMN_DESCRIPTION, todoItem.getDescription());
        values.put(DBHandler.COLUMN_DUE_DATE, todoItem.getDueDate());

        long newRowId = db.insert(DBHandler.TABLE_TODO, null, values);
        if (newRowId >= 0) {
            todoItem.setId((int) newRowId);
            return true;
        }
        return false;
    }

    public boolean removeTodoItem(ToDoItem todoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = DBHandler.COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(todoItem.getId()) };
        int rowsDeleted = db.delete(DBHandler.TABLE_TODO, selection, selectionArgs);
        return rowsDeleted > 0;
    }

    public boolean updateTodoItem(ToDoItem todoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHandler.COLUMN_TITLE, todoItem.getTitle());
        values.put(DBHandler.COLUMN_DESCRIPTION, todoItem.getDescription());
        values.put(DBHandler.COLUMN_DUE_DATE, todoItem.getDueDate());

        String selection = DBHandler.COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(todoItem.getId()) };

        int rowsUpdated = db.update(DBHandler.TABLE_TODO, values, selection, selectionArgs);
        return (rowsUpdated > 0);
    }

    @Override
    public EnumHandler type() {
        return EnumHandler.DB;
    }
}