package com.gxuwz.beethoven.adapter.my.member;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.current.SetMeal;

import java.util.List;

public class SetMealAdapter extends RecyclerView.Adapter<SetMealAdapter.SetMealViewHolder> {

    Context context;
    List<SetMeal> setMealList;
    LinearLayout last;
    TextView payPriceTv;

    public SetMealAdapter(Context context, List<SetMeal> setMealList,TextView payPriceTv) {
        this.context = context;
        this.setMealList = setMealList;
        this.payPriceTv = payPriceTv;
    }

    @NonNull
    @Override
    public SetMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SetMealViewHolder(LayoutInflater.from(context).inflate(R.layout.set_meal_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SetMealViewHolder holder, int position) {
        SetMeal setMeal = setMealList.get(position);
        if(position==0) {
            holder.box.setBackground(context.getResources().getDrawable(R.drawable.shape_unl_red));
            last = holder.box;
        }
        if(setMeal.getDetail()==null) {
            holder.detailLin.setVisibility(View.GONE);
        } else {
            holder.detailLin.setVisibility(View.VISIBLE);
            holder.detail.setText(setMeal.getDetail());
        }
        holder.costPrice.setText(setMeal.getCostPrice()+"");
        holder.currentPrices.setText(setMeal.getCurrentPrices()+"");
        holder.smName.setText(setMeal.getSmName());
        holder.pId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPriceTv.setText(setMeal.getCurrentPrices()+"");
                last.setBackground(context.getResources().getDrawable(R.drawable.shape_unlinner_black));
                holder.box.setBackground(context.getResources().getDrawable(R.drawable.shape_unl_red));
                last = holder.box;
            }
        });
    }

    @Override
    public int getItemCount() {
        return setMealList.size();
    }

    class SetMealViewHolder extends RecyclerView.ViewHolder{

        LinearLayout detailLin,pId,box;
        TextView detail,smName,currentPrices,costPrice;


        public SetMealViewHolder(@NonNull View itemView) {
            super(itemView);
            pId = itemView.findViewById(R.id.p_id);
            box = itemView.findViewById(R.id.box);
            detailLin = itemView.findViewById(R.id.detail_lin);
            detail = itemView.findViewById(R.id.detail);
            smName = itemView.findViewById(R.id.sm_name);
            currentPrices = itemView.findViewById(R.id.current_prices);
            costPrice = itemView.findViewById(R.id.cost_price);
        }
    }
}
