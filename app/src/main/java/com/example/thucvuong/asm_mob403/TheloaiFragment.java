package com.example.thucvuong.asm_mob403;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.GridView;

import com.sutrix.adapter.TheLoaiAdapter;
import com.sutrix.adapter.TruyenAdapter;
import com.sutrix.model.Truyen;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class TheloaiFragment extends Fragment {

    Button btnTest;

    RecyclerView recyclerView;

    ArrayList<String> theloais = new ArrayList<String>();
    ArrayList<Truyen> truyens;

    public TheloaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_theloai, container, false);

        _addTheLoai();

        recyclerView = view.findViewById(R.id.rc_theloai);

        recyclerView.setHasFixedSize(true);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);

        TheLoaiAdapter theLoaiAdapter = new TheLoaiAdapter(theloais, getActivity().getApplicationContext(), getFragmentManager(), getActivity());

        recyclerView.setAdapter(theLoaiAdapter);



        return view;
    }

    private void _addTheLoai(){

        theloais.add("NgonTinh");
        theloais.add("TienHiep");
        theloais.add("KiemHiep");
        theloais.add("TruyenTeen");
        theloais.add("DoThi");
        theloais.add("HaiHuoc");
        theloais.add("QuanSu");
        theloais.add("LichSu");
        theloais.add("TruyenTranh");

    }

}
