package com.example.thucvuong.asm_mob403;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sutrix.adapter.TruyenAdapter;
import com.sutrix.model.Truyen;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ArrayList<Truyen> truyens;

    RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public HomeFragment(ArrayList<Truyen> truyens) {
        this.truyens = truyens;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rc_trangchu);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        TruyenAdapter truyenAdapter = new TruyenAdapter(truyens, getActivity().getApplicationContext());

        recyclerView.setAdapter(truyenAdapter);



        return view;
    }

}
