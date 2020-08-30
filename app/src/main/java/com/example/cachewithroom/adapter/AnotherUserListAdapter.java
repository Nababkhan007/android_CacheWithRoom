package com.example.cachewithroom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cachewithroom.R;
import com.example.cachewithroom.pojoClass.User;
import com.example.cachewithroom.room.AnotherUser;

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

        anotherUserListViewHolder.userIdTv.setText(context.getText(R.string.user_id_txt) + ": " + anotherUser.getUserId());
        anotherUserListViewHolder.nameTv.setText(context.getText(R.string.name_txt) + ": " + anotherUser.getName());
        anotherUserListViewHolder.userNameTv.setText(context.getText(R.string.user_name_txt) + ": " + anotherUser.getUserName());
        anotherUserListViewHolder.emailTv.setText(context.getText(R.string.email_txt) + ": " + anotherUser.getEmail());
        anotherUserListViewHolder.addressTv.setText(context.getText(R.string.address_txt) + ": " + anotherUser.getAddress());
        anotherUserListViewHolder.phoneNumberTv.setText(context.getText(R.string.phone_number_txt) + ": " + anotherUser.getPhone());
        anotherUserListViewHolder.websiteTv.setText(context.getText(R.string.website_txt) + ": " + anotherUser.getWebsite());
        anotherUserListViewHolder.companyTv.setText(context.getText(R.string.company_txt) + ": " + anotherUser.getCompany());
    }

    @Override
    public int getItemCount() {
        return anotherUserList.size();
    }

    public static class AnotherUserListViewHolder extends RecyclerView.ViewHolder {
        private TextView
                userIdTv, nameTv, userNameTv,
                emailTv, addressTv, phoneNumberTv,
                websiteTv, companyTv;

        AnotherUserListViewHolder(@NonNull View itemView) {
            super(itemView);
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
