package com.sutrix.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Picasso.get().load(truyens.get(i).getHinh()).into(viewHolder.imgView);

        viewHolder.textViewTenTruyen.setText(truyens.get(i).getTieuDe());
        viewHolder.textViewMoTa.setText(truyens.get(i).getMoTa());
        viewHolder.textViewNgayUp.setText(truyens.get(i).getNgayUp());

    }

    @Override
    public int getItemCount() {
        return truyens.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgView;
        TextView textViewTenTruyen, textViewMoTa, textViewNgayUp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            textView1 = itemView.findViewById(R.id.textView1);
//            textView2 = itemView.findViewById(R.id.textView2);
//            textView3 = itemView.findViewById(R.id.textView3);

            imgView = itemView.findViewById(R.id.imgStory);
            textViewTenTruyen = itemView.findViewById(R.id.tv_ten_truyen);
            textViewMoTa = itemView.findViewById(R.id.tv_mo_ta);
            textViewNgayUp = itemView.findViewById(R.id.tv_thoi_gian);

        }
    }
}
