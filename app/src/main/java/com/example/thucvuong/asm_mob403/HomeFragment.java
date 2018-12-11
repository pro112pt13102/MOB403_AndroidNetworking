package com.example.thucvuong.asm_mob403;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.sutrix.common.HTTPDataHandler;
import com.sutrix.inter.OnBottomReachedListener;
import com.sutrix.model.Id;
import com.sutrix.model.Truyen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HomeFragment extends Fragment {

    public static boolean flagLoadMore = true;

    ArrayList<Truyen> truyens;

    RecyclerView recyclerView;

    TextView tvSo_trang;
    Button btn_refresh, btn_search;
    EditText edtSearch;

    TruyenAdapter truyenAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public HomeFragment(ArrayList<Truyen> truyens) {
        this.truyens = truyens;
    }

    @SuppressLint("ValidFragment")
    public HomeFragment(ArrayList<Truyen> truyens, boolean flagLoadMore){
        this.truyens = truyens;
        this.flagLoadMore = flagLoadMore;
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


                String oid = truyens.get(0).get_id().getOid();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("oid", oid);
                startActivity(intent);

//                Intent i = new Intent(getActivity(), MainActivity.class);
//                startActivity(i);


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

        truyenAdapter = new TruyenAdapter(truyens, getActivity().getApplicationContext());

        recyclerView.setAdapter(truyenAdapter);

        tvSo_trang = view.findViewById(R.id.tvSo_trang);

        tvSo_trang.setText("1/"+truyens.size());


        truyenAdapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                //your code goes here
//                Toast.makeText(getActivity(), "asd", Toast.LENGTH_SHORT).show();
                if (flagLoadMore){
                    SplashScreen.limitTruyen += 10;
                    new GetData().execute(Common.getAddressWithLimit(SplashScreen.limitTruyen));
                }


            }
        });



        return view;


    }


    //DucNguyen getData
    class GetData extends AsyncTask<String, Void, String> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//            Toast.makeText(getActivity(), "Please wait ...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {

            String temp = null;

            String urlString = strings[0];

            HTTPDataHandler http = new HTTPDataHandler();

            temp = http.getHTTPData(urlString);

            return temp;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONArray jsonArray = null;

            Object temp = null;
            String $oid = null;

            try {

                jsonArray = new JSONArray(s);

                for (int i=0; i<jsonArray.length(); i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    JSONObject _id = jsonObject.getJSONObject("_id");
                    $oid = _id.getString("$oid");

                    String tieude = jsonObject.getString("TieuDe");
                    String tacgia = jsonObject.getString("TacGia");
                    String trangthai = jsonObject.getString("TrangThai");
                    int sochuong = Integer.parseInt(jsonObject.getString("SoChuong"));
                    String ngayup = jsonObject.getString("NgayUp");
                    String ngaycapnhat = jsonObject.getString("NgayCapNhat");
                    String nhomdich = jsonObject.getString("NhomDich");
                    String mota = jsonObject.getString("MoTa");
                    String hinh = jsonObject.getString("Hinh");

                    //Get The Loai
                    JSONArray tempTheLoais = jsonObject.getJSONArray("TheLoai");
                    List<String> theloais = new ArrayList<>();
                    for (int x = 0; x < tempTheLoais.length(); x++){
                        theloais.add(tempTheLoais.getString(x));
                    }

                    //Get Noi Dung
                    JSONArray tempNoiDungs = jsonObject.getJSONArray("NoiDung");
                    List<String> noidungs = new ArrayList<>();
                    for (int z = 0; z < tempNoiDungs.length(); z++){
                        noidungs.add(tempNoiDungs.getString(z));
                    }

                    Truyen truyen = new Truyen();
                    Id id = new Id();

                    id.setOid($oid);
                    truyen.set_id(id);
                    truyen.setTieuDe(tieude);
                    truyen.setTacGia(tacgia);
                    truyen.setTheLoai(theloais);
                    truyen.setTrangThai(trangthai);
                    truyen.setSoChuong(sochuong);
                    truyen.setNgayUp(ngayup);
                    truyen.setNgayCapNhat(ngaycapnhat);
                    truyen.setNhomDich(nhomdich);
                    truyen.setMoTa(mota);
                    truyen.setNoiDung(noidungs);
                    truyen.setHinh(hinh);


                    //custom
                    boolean flag = false;
                    for (Truyen truyen1 : truyens){
                        if (truyen1.getTieuDe().equalsIgnoreCase(truyen.getTieuDe())){
                            flag = true;
                        }
                    }
                    if (!flag){
                        truyens.add(truyen);
                    }


                }


            }catch (Exception e){
                e.printStackTrace();
            }




//            Toast.makeText(SplashScreen.this, truyens.get(1).getHinh()+"", Toast.LENGTH_SHORT).show();

            tvSo_trang.setText("1/"+truyens.size());

            truyenAdapter.notifyDataSetChanged();


        }
    }






}
