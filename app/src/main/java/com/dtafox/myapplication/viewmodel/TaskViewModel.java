package com.dtafox.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dtafox.myapplication.data.Task;
import com.dtafox.myapplication.data.TaskRepository;

import java.util.List;

/** ViewModel exposing tasks from {@link TaskRepository} */
public class TaskViewModel extends ViewModel {
    private final TaskRepository repository = new TaskRepository();
    private final MutableLiveData<List<Task>> tasks = new MutableLiveData<>();

    /** Load tasks from the repository */
    public void loadTasks() {
        repository.getTasks(tasks::setValue);
    }

    /** Add a task using the repository */
    public void addTask(Task task) {
        repository.addTask(task);
        loadTasks();
    }

    public LiveData<List<Task>> getTasks() {
        return tasks;
    }
}
