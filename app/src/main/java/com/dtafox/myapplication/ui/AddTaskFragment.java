package com.dtafox.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.dtafox.myapplication.R;
import com.dtafox.myapplication.data.Task;
import com.dtafox.myapplication.databinding.FragmentAddTaskBinding;
import com.dtafox.myapplication.viewmodel.TaskViewModel;

/** Form allowing users to create new tasks */
public class AddTaskFragment extends Fragment {
    private FragmentAddTaskBinding binding;
    private TaskViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        binding.saveButton.setOnClickListener(v -> saveTask());
    }

    private void saveTask() {
        String title = binding.titleInput.getText().toString();
        String desc = binding.descriptionInput.getText().toString();
        viewModel.addTask(new Task(null, title, desc));
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addTaskFragment_to_taskListFragment);
    }
}
