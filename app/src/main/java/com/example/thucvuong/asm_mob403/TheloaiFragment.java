package com.example.thucvuong.asm_mob403;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


public class TheloaiFragment extends Fragment {

    String arr[]={"Ngôn tình","Kinh dị","Tản Văn","Cổ tích","Tiên Hiệp","Kiếm Hiệp","Viễn Tưởng","Truyện Cười","Lịch Sử","Xuyên không","Người Lớn"};
    public GridView gridView;
    public TheloaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_theloai, container, false);

        gridView = view.findViewById(R.id.Grid_view);
        // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.view_1o_grid,arr);
//        gridView.setAdapter(arrayAdapter);
        return view;
    }

}
