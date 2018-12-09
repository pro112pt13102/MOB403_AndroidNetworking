package com.sutrix.adapter;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thucvuong.asm_mob403.MainActivity;
import com.example.thucvuong.asm_mob403.MotaActivity;
import com.example.thucvuong.asm_mob403.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;
import com.sutrix.inter.OnBottomReachedListener;
import com.sutrix.model.Truyen;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TruyenTranhAdapter extends RecyclerView.Adapter<TruyenTranhAdapter.ViewHolder> {



    List<String> noiDungs;
    Context context;


    public TruyenTranhAdapter(List<String> noiDungs, Context context) {
        this.noiDungs = noiDungs;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.view_1_o_truyentranh, viewGroup, false);



        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Picasso.get().load(noiDungs.get(i)).into(viewHolder.imageViewTruyenTranh);

    }

    @Override
    public int getItemCount() {
        return noiDungs.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewTruyenTranh;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewTruyenTranh = itemView.findViewById(R.id.imageViewTruyenTranh);

        }


    }


}
