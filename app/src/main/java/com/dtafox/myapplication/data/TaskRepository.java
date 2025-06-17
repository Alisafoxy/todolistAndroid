package com.dtafox.myapplication.data;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/** Repository handling task persistence using Firestore */
public class TaskRepository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public interface TaskCallback {
        void onResult(List<Task> tasks);
    }

    /** Fetch tasks from Firestore and return them through the callback */
    public void getTasks(TaskCallback callback) {
        db.collection("tasks").get().addOnCompleteListener(task -> {
            List<Task> result = new ArrayList<>();
            if (task.isSuccessful() && task.getResult() != null) {
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Task t = doc.toObject(Task.class);
                    t.id = doc.getId();
                    result.add(t);
                }
            }
            callback.onResult(result);
        });
    }

    /** Add a task to Firestore */
    public void addTask(Task task) {
        db.collection("tasks").add(task);
    }
}
