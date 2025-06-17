package com.dtafox.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.dtafox.myapplication.R;
import com.dtafox.myapplication.data.Task;
import com.dtafox.myapplication.databinding.FragmentTaskListBinding;
import com.dtafox.myapplication.viewmodel.TaskViewModel;

import java.util.ArrayList;

/** Displays a list of tasks from Firestore */
public class TaskListFragment extends Fragment {
    private FragmentTaskListBinding binding;
    private TaskViewModel viewModel;
    private final ArrayAdapter<String> adapter;

    public TaskListFragment() {
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTaskListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        binding.taskRecyclerView.setAdapter(new SimpleTaskAdapter());
        binding.addTaskFab.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_taskListFragment_to_addTaskFragment));
        observeTasks();
    }

    /** Observe task changes from the ViewModel */
    private void observeTasks() {
        viewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
            ((SimpleTaskAdapter)binding.taskRecyclerView.getAdapter()).setTasks(tasks);
        });
        viewModel.loadTasks();
    }
}
