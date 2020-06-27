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

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {
    private Context context;
    private List<User> userList;

    public UserListAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_user_list_design, viewGroup, false);
        return new UserListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final UserListViewHolder userListViewHolder, int i) {
        final User user = userList.get(i);

        userListViewHolder.userIdTv.setText(context.getText(R.string.user_id_txt) + ": " + user.getId());
        userListViewHolder.nameTv.setText(context.getText(R.string.name_txt) + ": " + user.getName());
        userListViewHolder.userNameTv.setText(context.getText(R.string.user_name_txt) + ": " + user.getUsername());
        userListViewHolder.emailTv.setText(context.getText(R.string.email_txt) + ": " + user.getEmail());
        userListViewHolder.addressTv.setText(context.getText(R.string.address_txt) + ": "
                + user.getAddress().getStreet() + " " + user.getAddress().getSuite()
                + " " + user.getAddress().getCity() + " " + user.getAddress().getZipcode());
        userListViewHolder.phoneNumberTv.setText(context.getText(R.string.phone_number_txt) + ": " + user.getPhone());
        userListViewHolder.websiteTv.setText(context.getText(R.string.website_txt) + ": " + user.getWebsite());
        userListViewHolder.companyTv.setText(context.getText(R.string.company_txt) + ": " + user.getCompany().getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserListViewHolder extends RecyclerView.ViewHolder {
        private TextView
                userIdTv, nameTv, userNameTv,
                emailTv, addressTv, phoneNumberTv,
                websiteTv, companyTv;

        UserListViewHolder(@NonNull View itemView) {
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
