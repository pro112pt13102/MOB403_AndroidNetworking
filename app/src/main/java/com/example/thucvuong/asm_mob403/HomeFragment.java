package com.example.thucvuong.asm_mob403;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sutrix.adapter.TruyenAdapter;
import com.sutrix.common.Common;
import com.sutrix.model.Truyen;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ArrayList<Truyen> truyens;

    RecyclerView recyclerView;

    TextView tvSo_trang;
    Button btn_refresh, btn_search;
    EditText edtSearch;

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

        btn_refresh = view.findViewById(R.id.btn_refresh);
        btn_search = view.findViewById(R.id.btn_search);
        edtSearch = view.findViewById(R.id.edtSearch);


        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Refreshing...", Toast.LENGTH_SHORT).show();
                
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);


            }
        });


// Này làm tiếp nha để đổ dữ liệu truyện qua cái Result nha anh Đức
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtSearch.isShown()){
                    edtSearch.setVisibility(View.VISIBLE);
                    return;
                }
                String searchName = edtSearch.getText().toString().trim().toUpperCase();
                if(searchName.equals("")){
                    Toast.makeText(getActivity(), "Bạn chưa nhập tên truyện", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra("searchName", searchName);
                getActivity().startActivity(intent);
                edtSearch.setVisibility(View.INVISIBLE);
            }
        });

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        TruyenAdapter truyenAdapter = new TruyenAdapter(truyens, getActivity().getApplicationContext());

        recyclerView.setAdapter(truyenAdapter);

        tvSo_trang = view.findViewById(R.id.tvSo_trang);

        tvSo_trang.setText("1/"+truyens.size());



        return view;


    }








}
