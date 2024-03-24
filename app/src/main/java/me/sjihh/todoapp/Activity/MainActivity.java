package me.sjihh.todoapp.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

import me.sjihh.todoapp.Handler.Handlers.DBHandler;
import me.sjihh.todoapp.Handler.Handlers.ProgressHandler;
import me.sjihh.todoapp.Handler.MainHandler;
import me.sjihh.todoapp.Handler.Handlers.ToDoHandler;
import me.sjihh.todoapp.R;
import me.sjihh.todoapp.Utils.ToDoAdapter;
import me.sjihh.todoapp.ToDoItem;

public class MainActivity extends AppCompatActivity  implements ToDoAdapter.OnItemClickListener {
    private MainActivity mainActivity;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ToDoItem> dataList; // Change to list of ToDoItem
    private DBHandler dbHandler;
    private ProgressHandler progressHandler;
    private FloatingActionButton fab;
    private static final String _TABLE_NAME_ = "todo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        initializeHandlers();
        initializeViews();
        setupListeners();
        loadDataFromDatabase();
        setupRecyclerView(); // Change to setupRecyclerView instead of setupListView
    }

    private void initializeHandlers() {
        dbHandler = new DBHandler(this);
        MainHandler.addOrUpdateHandler(dbHandler);
        ToDoHandler toDoHandler = new ToDoHandler();
        MainHandler.addOrUpdateHandler(toDoHandler);
        progressHandler = new ProgressHandler();
        MainHandler.addOrUpdateHandler(progressHandler);
    }

    private void initializeViews() {
        fab = findViewById(R.id.fab_add_todo);
        recyclerView = findViewById(R.id.recyclerView); // Change to recyclerView
        dataList = new ArrayList<>();
    }

    private void setupListeners() {
        fab.setOnClickListener(v -> startActivity(new Intent(mainActivity, AddActivity.class)));
    }

    private void loadDataFromDatabase() {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + _TABLE_NAME_, null);
        while (cursor.moveToNext()) {
            ToDoItem item = createToDoItemFromCursor(cursor);
            System.out.println(item.toString());
            dataList.add(item);
        }
        System.out.println();
        if (dataList.isEmpty()) {
            dataList.add(new ToDoItem("Empty to do list", "", 0)); // Add default empty item
        }
        cursor.close();
        db.close();
    }

    private ToDoItem createToDoItemFromCursor(Cursor cursor) {
        int idColumnIndex = cursor.getColumnIndex("id");
        int titleColumnIndex = cursor.getColumnIndex("title");
        int descriptionColumnIndex = cursor.getColumnIndex("description");
        int dueDateColumnIndex = cursor.getColumnIndex("dueDate");

        // Check if columns exist in the cursor
        if (idColumnIndex == -1 || titleColumnIndex == -1 || descriptionColumnIndex == -1 || dueDateColumnIndex == -1) {
            // Handle the case where one or more columns are missing
            return null; // or throw an exception, log an error, etc.
        }

        // Retrieve column values
        int id = cursor.getInt(idColumnIndex);
        String title = cursor.getString(titleColumnIndex);
        String description = cursor.getString(descriptionColumnIndex);
        long dueDate = cursor.getLong(dueDateColumnIndex);

        ToDoItem dummy = new ToDoItem(title, description, dueDate);
        dummy.setId(id);

        return  dummy;
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ToDoAdapter(dataList, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(ToDoItem item) {
        progressHandler.setTrackingItem(item);
        Intent intent = new Intent(this, UpdateActivity.class);
        startActivity(intent);
    }
}
