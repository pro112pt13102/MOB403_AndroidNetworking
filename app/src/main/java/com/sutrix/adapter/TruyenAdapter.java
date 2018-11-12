package com.sutrix.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thucvuong.asm_mob403.R;
import com.sutrix.pojo.Truyen;

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
        View itemView = layoutInflater.inflate(R.layout.view_1o_row, viewGroup, false);



        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

//        viewHolder.textView1.setText(truyens.get(i).getTieuDe());
//        viewHolder.textView2.setText(truyens.get(i).getNoiDung());
//        viewHolder.textView3.setText(truyens.get(i).getMoTa());

    }

    @Override
    public int getItemCount() {
        return truyens.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView1, textView2, textView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            textView1 = itemView.findViewById(R.id.textView1);
//            textView2 = itemView.findViewById(R.id.textView2);
//            textView3 = itemView.findViewById(R.id.textView3);
        }
    }
}
