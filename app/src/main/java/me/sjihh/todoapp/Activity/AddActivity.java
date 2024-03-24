package me.sjihh.todoapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import me.sjihh.todoapp.Handler.EnumHandler;
import me.sjihh.todoapp.Handler.MainHandler;
import me.sjihh.todoapp.Handler.Handlers.ToDoHandler;
import me.sjihh.todoapp.R;
import me.sjihh.todoapp.ToDoItem;
import me.sjihh.todoapp.Utils.Utils;

public class AddActivity extends AppCompatActivity {
    private ToDoHandler toDoHandler;
    private EditText title, des, due;
    private Button cancel, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initializeHandler();
        initializeViews();
        setupListeners();
    }

    private void initializeHandler() {
        if (MainHandler.getHandler(EnumHandler.TODO).type() != EnumHandler.NULL) {
            toDoHandler = (ToDoHandler) MainHandler.getHandler(EnumHandler.TODO);
        }
    }

    private void initializeViews() {
        title = findViewById(R.id.edit_text_title);
        des = findViewById(R.id.edit_text_description);
        due = findViewById(R.id.edit_text_due_date);
        cancel = findViewById(R.id.button_cancel);
        save = findViewById(R.id.button_save);
    }

    private void setupListeners() {
        cancel.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        save.setOnClickListener(v -> handleSaveClick());
    }

    private void handleSaveClick() {
        if (title.getText().toString().isEmpty()) {
            showToast("Title cannot be null");
            return;
        }
        if (des.getText().toString().isEmpty()) {
            showToast("Description cannot be null");
            return;
        }
        if (!Utils.isInteger(due.getText().toString())) {
            showToast("Due date must be a number!");
            return;
        }

        ToDoItem toDoItem = new ToDoItem(
                title.getText().toString(),
                des.getText().toString(),
                Utils.getLongFromString(due.getText().toString())
        );

        if (addToDoItem(toDoItem)) {
            startActivity(new Intent(this, MainActivity.class));
            showToast("Success!");
        } else {
            showToast("Fail! Try again");
        }
    }

    private boolean addToDoItem(ToDoItem toDoItem) {
        if (toDoHandler != null) {
            return toDoHandler.addToDo(toDoItem);
        } else {
            return false;
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}