package com.sutrix.adapter;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thucvuong.asm_mob403.HomeFragment;
import com.example.thucvuong.asm_mob403.LoginFragment;
import com.example.thucvuong.asm_mob403.MainActivity;
import com.example.thucvuong.asm_mob403.MotaActivity;
import com.example.thucvuong.asm_mob403.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;
import com.sutrix.common.Common;
import com.sutrix.common.HTTPDataHandler;
import com.sutrix.model.Id;
import com.sutrix.model.Truyen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {

    ArrayList<String> theloais;
    Context context;
    FragmentManager fragmentManager;
    FragmentActivity fragmentActivity;

    ArrayList<Truyen> truyens = new ArrayList<>();

    public TheLoaiAdapter(ArrayList<String> theloais, Context context, FragmentManager fragmentManager, FragmentActivity fragmentActivity) {
        this.theloais = theloais;
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View itemView = layoutInflater.inflate(R.layout.view_1o_grid, viewGroup, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //bấm vào item sẽ execute hàm getdata với query tìm tất cả truyện theo thể loại

                new GetData().execute(Common.getAddressWithTheLoai(theloais.get(viewGroup.indexOfChild(itemView))));
            }
        });

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

//        viewHolder.textViewTheLoai.setText(theloais.get(i).toString());

        String theloai = theloais.get(i).toString();

        switch (theloai){
            case "NgonTinh": {
                viewHolder.textViewTheLoai.setText("Ngôn Tình");break;
            }

            case "TienHiep": {
                viewHolder.textViewTheLoai.setText("Tiên Hiệp");break;
            }

            case "KiemHiep": {
                viewHolder.textViewTheLoai.setText("Kiếm Hiệp");break;
            }

            case "TruyenTeen": {
                viewHolder.textViewTheLoai.setText("Truyện Teen");break;
            }

            case "DoThi": {
                viewHolder.textViewTheLoai.setText("Đô Thị");break;
            }

            case "HaiHuoc": {
                viewHolder.textViewTheLoai.setText("Hài Hước");break;
            }

            case "QuanSu": {
                viewHolder.textViewTheLoai.setText("Quân Sự");break;
            }

            case "LichSu": {
                viewHolder.textViewTheLoai.setText("Lịch Sử");break;
            }

            case "TruyenTranh": {
                viewHolder.textViewTheLoai.setText("Truyện Tranh");break;
            }

            default: break;
        }

    }

    @Override
    public int getItemCount() {
        return theloais.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTheLoai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTheLoai = itemView.findViewById(R.id.textViewTheLoai);

        }
    }

    class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(fragmentActivity);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd.setTitle("Please Wait ...");
            pd.show();
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

//                    Log.d("zzdzdzdzdzd", noidungs.toString());

//                    truyen.setTieuDe(jsonObject.getString("TieuDe"));
//                    truyen.setNoiDung(jsonObject.getString("NoiDung"));
//                    truyen.setMoTa(jsonObject.getString("MoTa"));

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

                    truyens.add(truyen);


                }


            }catch (Exception e){
                e.printStackTrace();
            }

            // Get Data rồi đổ vào arraylist truyện -> replace fragment hiện tại thành home fragment
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment(truyens, false))
                    .commit();

//            Toast.makeText(SplashScreen.this, truyens.get(1).getHinh()+"", Toast.LENGTH_SHORT).show();

            pd.dismiss();
        }
    }

}
