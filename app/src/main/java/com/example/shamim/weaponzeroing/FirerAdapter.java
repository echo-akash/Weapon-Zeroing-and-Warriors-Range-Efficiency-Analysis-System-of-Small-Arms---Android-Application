package com.example.shamim.weaponzeroing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FirerAdapter extends RecyclerView.Adapter<FirerAdapter.FirerViewHolder> {


    private Context mCtx;
    private List<Firer> productList;

    public FirerAdapter(Context mCtx, List<Firer> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public FirerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.firer_list, null);
        return new FirerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FirerViewHolder holder, int position) {
        Firer product = productList.get(position);

        //loading the image
        /*Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);*/

        holder.textViewName.setText(String.valueOf(product.getName()));
        holder.textViewDOB.setText(String.valueOf(product.getDOB()));
        holder.textViewCell.setText(String.valueOf(product.getCell()));
        holder.textViewNID.setText(String.valueOf(product.getNID()));
        holder.textViewReligion.setText(String.valueOf(product.getReligion()));
        holder.textViewVillage.setText(String.valueOf(product.getVillage()));
        holder.textViewPO.setText(String.valueOf(product.getPO()));
        holder.textViewPS.setText(String.valueOf(product.getPS()));
        holder.textViewDist.setText(String.valueOf(product.getDist()));
        holder.textViewGender.setText(String.valueOf(product.getGender()));
        holder.textViewUnit.setText(String.valueOf(product.getUnit()));
        holder.textViewCompany.setText(String.valueOf(product.getCompany()));
        //holder.textViewCorrection.setText(String.valueOf(product.getCorrectionMethod()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class FirerViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewDOB, textViewCell, textViewNID,
                textViewReligion, textViewVillage, textViewPO, textViewPS,textViewDist,textViewGender,textViewUnit,textViewCompany;
        //ImageView imageView;

        public FirerViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDOB= itemView.findViewById(R.id.textViewDOB);
            textViewCell = itemView.findViewById(R.id.textViewCell);
            textViewNID = itemView.findViewById(R.id.textViewNID);
            textViewReligion = itemView.findViewById(R.id.textViewReligion);
            textViewVillage = itemView.findViewById(R.id.textViewVillage);
            textViewPO = itemView.findViewById(R.id.textViewPO);
            textViewPS = itemView.findViewById(R.id.textViewPS);
            textViewDist = itemView.findViewById(R.id.textViewDist);
            textViewGender = itemView.findViewById(R.id.textViewGender);
            textViewUnit = itemView.findViewById(R.id.textViewUnit);
            textViewCompany = itemView.findViewById(R.id.textViewCompany);
            //imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
