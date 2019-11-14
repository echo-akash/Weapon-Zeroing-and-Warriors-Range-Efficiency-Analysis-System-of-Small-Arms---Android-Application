package com.example.shamim.weaponzeroing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SosnAdapter extends RecyclerView.Adapter<SosnAdapter.SosnViewHolder> {


    private Context mCtx;
    private List<SOSN> productList;

    public SosnAdapter(Context mCtx, List<SOSN> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public SosnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.sosn_list, null);
        return new SosnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SosnViewHolder holder, int position) {
        SOSN product = productList.get(position);

        //loading the image
        /*Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);*/

        holder.textViewTitle.setText(String.valueOf(product.getDate()));
        holder.textViewRangeNum.setText(String.valueOf(product.getRangeNum()));
        holder.textViewDetailNum.setText(String.valueOf(product.getDetailNum()));
        holder.textViewFiringTargetNum.setText(String.valueOf(product.getFiringTargetNum()));
        holder.textViewTarget1A.setText(String.valueOf(product.getTarget1A()));
        holder.textViewTarget2A.setText(String.valueOf(product.getTarget2A()));
        holder.textViewTarget3A.setText(String.valueOf(product.getTarget3A()));
        holder.textViewTotal.setText(String.valueOf(product.getTotal()));
        //holder.textViewCorrection.setText(String.valueOf(product.getCorrectionMethod()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class SosnViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewRangeNum, textViewDetailNum, textViewFiringTargetNum, textViewTarget1A, textViewTarget2A, textViewTarget3A, textViewTotal;
        //ImageView imageView;

        public SosnViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewRangeNum= itemView.findViewById(R.id.textViewRangeNum);
            textViewDetailNum = itemView.findViewById(R.id.textViewDetailNum);
            textViewFiringTargetNum = itemView.findViewById(R.id.textViewFiringTargetNum);
            textViewTarget1A = itemView.findViewById(R.id.textViewTarget1A);
            textViewTarget2A = itemView.findViewById(R.id.textViewTarget2A);
            textViewTarget3A = itemView.findViewById(R.id.textViewTarget3A);
            textViewTotal = itemView.findViewById(R.id.textViewTotal);
            //imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
