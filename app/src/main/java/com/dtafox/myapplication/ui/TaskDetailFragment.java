package com.dtafox.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dtafox.myapplication.data.Task;
import com.dtafox.myapplication.databinding.FragmentTaskDetailBinding;
import com.dtafox.myapplication.viewmodel.TaskViewModel;

/** Shows details of a single task */
public class TaskDetailFragment extends Fragment {
    private FragmentTaskDetailBinding binding;
    private TaskViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTaskDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        // Display selected task (for simplicity, just take first)
        viewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
            if (tasks != null && !tasks.isEmpty()) {
                Task task = tasks.get(0);
                binding.taskTitle.setText(task.title);
                binding.taskDescription.setText(task.description);
            }
        });
    }
}
