package com.academy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.academy.mbank.R;
import com.academy.mbank.TypeSwapMoney;

import java.text.DecimalFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private List<TypeSwapMoney> topics;
    private final Context mContext;

    public HistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<TypeSwapMoney> topics){
        this.topics = topics;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TypeSwapMoney topic = topics.get(position);
        holder.tvDate.setText(topic.getDate());
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.tvPrice.setText("-"+formatter.format(topic.getPrice()));
        holder.tvSoTaiKhoan.setText(topic.getAccountTo());
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate,tvSoTaiKhoan,tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvSoTaiKhoan = itemView.findViewById(R.id.tvSoTaiKhoan);
            tvPrice = itemView.findViewById(R.id.tvPrice);

        }

    }

}
