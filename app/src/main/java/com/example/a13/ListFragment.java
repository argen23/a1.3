package com.example.a13;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;


public class ListFragment extends Fragment  {

    private RecyclerView rv;
    private Adapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new Adapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv  = view.findViewById(R.id.rv);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Fragment fragment = new FormFragment();
            requireActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mContainer,fragment).commit();
        });
        rv.setAdapter(adapter);

        requireActivity().getSupportFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Students students = (Students) result.getSerializable("students");
                Log.e("List","text" + students.getName());
                Log.e("List","text" + students.getSurname());
                adapter.addItem(students);
            }
        });


    }



}