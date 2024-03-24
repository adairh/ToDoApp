package me.sjihh.todoapp.Utils;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import me.sjihh.todoapp.R;
import me.sjihh.todoapp.ToDoItem;

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
        private TextView idTextView;
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView dueDateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.id);
            titleTextView = itemView.findViewById(R.id.todo_item_title);
            descriptionTextView = itemView.findViewById(R.id.todo_item_description);
            dueDateTextView = itemView.findViewById(R.id.todo_item_due_date);
        }

        // Bind data to views and set click listener
        @SuppressLint("SetTextI18n")
        public void bind(ToDoItem item, OnItemClickListener listener) {
            idTextView.setText("#" + item.getId());
            titleTextView.setText(item.getTitle());
            descriptionTextView.setText(item.getDescription());
            dueDateTextView.setText(String.valueOf(item.getDueDate()));

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
