package com.sutrix.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thucvuong.asm_mob403.HomeFragment;
import com.example.thucvuong.asm_mob403.MotaActivity;
import com.example.thucvuong.asm_mob403.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;
import com.sutrix.model.Truyen;

import java.util.ArrayList;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.ViewHolder> {

    ArrayList<Truyen> truyens;
    Context context;

    public TruyenAdapter(ArrayList<Truyen> truyens, Context context) {
        this.truyens = truyens;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.view_1_o, viewGroup, false);



        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Picasso.get().load(truyens.get(i).getHinh()).into(viewHolder.imgView);

        viewHolder.textViewTenTruyen.setText(truyens.get(i).getTieuDe());
        //viewHolder.textViewMoTa.setText(truyens.get(i).getMoTa());
        viewHolder.textViewSochuong.setText("Số chương: "+String.valueOf(truyens.get(i).getSoChuong()+" - "));
        viewHolder.textViewTacgia.setText("Tác giả: "+truyens.get(i).getTacGia());
        viewHolder.textViewNgayUp.setText(truyens.get(i).getNgayUp());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, ""+i, Toast.LENGTH_SHORT).show();
                Context context = view.getContext();
                Intent intent = new Intent( context, MotaActivity.class);

                intent.putExtra("index", i);

                Bundle bundle = new Bundle();
                bundle.putSerializable("truyens", truyens);
                intent.putExtra("BUNDLE", bundle);


                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return truyens.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgView;
        TextView textViewTenTruyen, textViewMoTa, textViewNgayUp, textViewSochuong, textViewTacgia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            textView1 = itemView.findViewById(R.id.textView1);
//            textView2 = itemView.findViewById(R.id.textView2);
//            textView3 = itemView.findViewById(R.id.textView3);

            imgView = itemView.findViewById(R.id.imgStory);
            textViewTenTruyen = itemView.findViewById(R.id.tv_ten_truyen);
            //textViewMoTa = itemView.findViewById(R.id.tv_mo_ta);
            textViewSochuong = itemView.findViewById(R.id.tv_so_chuong);
            textViewTacgia = itemView.findViewById(R.id.tv_tac_gia);
            textViewNgayUp = itemView.findViewById(R.id.tv_thoi_gian);

        }
    }
}
