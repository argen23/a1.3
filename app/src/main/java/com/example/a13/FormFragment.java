package com.example.a13;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a13.databinding.FragmentFormBinding;


public class FormFragment extends Fragment {

    private FragmentFormBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSave.setOnClickListener(v -> {
            save();
        });
    }

    private void save() {
        Fragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        String text1 = binding.etName.getText().toString();
        String text2 = binding.etSurname.getText().toString();
        Students students = new Students(text1, text2);
        bundle.putSerializable("students", students);
//        getParentFragmentManager().setFragmentResult("requestKey", bundle);
//        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mContainer, fragment).commit();
        FormFragment.this.getActivity().getSupportFragmentManager().setFragmentResult("requestKey",bundle);
        FormFragment.this.getActivity().getSupportFragmentManager().popBackStack();
    }
}
