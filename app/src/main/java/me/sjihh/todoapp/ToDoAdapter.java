package me.sjihh.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import me.sjihh.todoapp.R;
import me.sjihh.todoapp.ToDoItem;
import me.sjihh.todoapp.Utils.Langs;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<ToDoItem> dataList;
    private OnItemClickListener listener;
    public ToDoAdapter(List<ToDoItem> dataList, OnItemClickListener listener) {
        this.dataList = dataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoItem item = dataList.get(position);
        holder.bind(item, listener);
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView dueDateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.todo_item_title);
            descriptionTextView = itemView.findViewById(R.id.todo_item_description);
            dueDateTextView = itemView.findViewById(R.id.todo_item_due_date);
        }

        // Bind data to views and set click listener
        public void bind(ToDoItem item, OnItemClickListener listener) {
            titleTextView.setText(Langs.id + item.getId());
            titleTextView.setText(Langs.title + item.getTitle());
            descriptionTextView.setText(Langs.description + item.getDescription());
            dueDateTextView.setText(Langs.due_date + String.valueOf(item.getDueDate()));

            // Set click listener on the itemView
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    // Interface to handle item clicks
    public interface OnItemClickListener {
        void onItemClick(ToDoItem item);
    }
}
