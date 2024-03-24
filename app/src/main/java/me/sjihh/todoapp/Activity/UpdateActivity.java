package me.sjihh.todoapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import me.sjihh.todoapp.Handler.EnumHandler;
import me.sjihh.todoapp.Handler.Handlers.ProgressHandler;
import me.sjihh.todoapp.Handler.Handlers.ToDoHandler;
import me.sjihh.todoapp.Handler.MainHandler;
import me.sjihh.todoapp.R;
import me.sjihh.todoapp.ToDoItem;
import me.sjihh.todoapp.Utils.Utils;

public class UpdateActivity extends AppCompatActivity {
    private ProgressHandler progressHandler = null;
    private ToDoHandler toDoHandler = null;
    private EditText title, des, due;
    private Button delete, update;
    private ToDoItem track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initializeHandlers();
        track = progressHandler.getTrackingItem();
        if (track == null) {
            handleInvalidTrackingItem();
            return;
        }

        initializeViews();
        loadItemToField(track);
        setupListeners();
    }

    private void initializeHandlers() {
        if (MainHandler.getHandler(EnumHandler.PRG).type() != EnumHandler.NULL) {
            progressHandler = (ProgressHandler) MainHandler.getHandler(EnumHandler.PRG);
        }
        if (MainHandler.getHandler(EnumHandler.TODO).type() != EnumHandler.NULL) {
            toDoHandler = (ToDoHandler) MainHandler.getHandler(EnumHandler.TODO);
        }
    }

    private void handleInvalidTrackingItem() {
        progressHandler.stopTrackingItem();
        startActivity(new Intent(this, MainActivity.class));
        showToast("Fail! Try again!");
    }

    private void initializeViews() {
        title = findViewById(R.id.edit_text_title);
        des = findViewById(R.id.edit_text_description);
        due = findViewById(R.id.edit_text_due_date);
        delete = findViewById(R.id.button_delete);
        update = findViewById(R.id.button_update);
    }

    private void loadItemToField(ToDoItem item) {
        title.setText(item.getTitle());
        des.setText(item.getDescription());
        due.setText(String.valueOf(item.getDueDate()));
    }

    private void setupListeners() {
        delete.setOnClickListener(v -> handleDeleteClick());
        update.setOnClickListener(v -> handleUpdateClick());
    }

    private void handleDeleteClick() {
        if (toDoHandler.deleteToDo(track)) {
            progressHandler.stopTrackingItem();
            startActivity(new Intent(this, MainActivity.class));
            showToast("Success");
        } else {
            showToast("Fail! Try again!");
        }
    }

    private void handleUpdateClick() {
        if (title.getText().toString().equals("")) {
            showToast("Title cannot be null");
            return;
        }
        if (des.getText().toString().equals("")) {
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
        toDoItem.setId(track.getId());

        if (toDoHandler.updateToDo(toDoItem)) {
            startActivity(new Intent(this, MainActivity.class));
            showToast("Success!");
        } else {
            showToast("Fail! Try again");
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}