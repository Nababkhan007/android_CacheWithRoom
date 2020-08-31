package com.example.cachewithroom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cachewithroom.R;
import com.example.cachewithroom.room.AnotherUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnotherUserListAdapter extends RecyclerView.Adapter<AnotherUserListAdapter.AnotherUserListViewHolder> {
    private Context context;
    private List<AnotherUser> anotherUserList;

    public AnotherUserListAdapter(Context context, List<AnotherUser> anotherUserList) {
        this.context = context;
        this.anotherUserList = anotherUserList;
    }

    @NonNull
    @Override
    public AnotherUserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_user_list_design, viewGroup, false);
        return new AnotherUserListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final AnotherUserListViewHolder anotherUserListViewHolder, int i) {
        final AnotherUser anotherUser = anotherUserList.get(i);

        Picasso.get()
                .load(anotherUser.getImageUrl())
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(anotherUserListViewHolder.imageView);
        anotherUserListViewHolder.userIdTv.setText(context.getText(R.string.user_id_text) + ": " + anotherUser.getUserId());
        anotherUserListViewHolder.nameTv.setText(context.getText(R.string.name_text) + ": " + anotherUser.getName());
        anotherUserListViewHolder.userNameTv.setText(context.getText(R.string.user_name_text) + ": " + anotherUser.getUserName());
        anotherUserListViewHolder.emailTv.setText(context.getText(R.string.email_text) + ": " + anotherUser.getEmail());
        anotherUserListViewHolder.addressTv.setText(context.getText(R.string.address_text) + ": " + anotherUser.getAddress());
        anotherUserListViewHolder.phoneNumberTv.setText(context.getText(R.string.phone_number_text) + ": " + anotherUser.getPhone());
        anotherUserListViewHolder.websiteTv.setText(context.getText(R.string.website_text) + ": " + anotherUser.getWebsite());
        anotherUserListViewHolder.companyTv.setText(context.getText(R.string.company_text) + ": " + anotherUser.getCompany());
    }

    @Override
    public int getItemCount() {
        return anotherUserList.size();
    }

    public static class AnotherUserListViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView
                userIdTv, nameTv, userNameTv,
                emailTv, addressTv, phoneNumberTv,
                websiteTv, companyTv;

        AnotherUserListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewId);
            userIdTv = itemView.findViewById(R.id.userIdTvId);
            nameTv = itemView.findViewById(R.id.nameTvId);
            userNameTv = itemView.findViewById(R.id.userNameTvId);
            emailTv = itemView.findViewById(R.id.emailTvId);
            addressTv = itemView.findViewById(R.id.addressTvId);
            phoneNumberTv = itemView.findViewById(R.id.phoneNumberTvId);
            websiteTv = itemView.findViewById(R.id.websiteTvId);
            companyTv = itemView.findViewById(R.id.companyTvId);
        }
    }
}
