package com.example.cachewithroom.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.cachewithroom.R;
import com.example.cachewithroom.adapter.UserListAdapter;
import com.example.cachewithroom.apiClass.ApiInterface;
import com.example.cachewithroom.apiClass.RetrofitInstance;
import com.example.cachewithroom.pojoClass.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<User> userList;
    private UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

        fetchUser();
    }

    private void fetchUser() {
        ApiInterface apiInterface = RetrofitInstance.retrofit().create(ApiInterface.class);
        Call<List<User>> call = apiInterface.getUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        userList = response.body();

                        userListAdapter = new UserListAdapter(MainActivity.this, userList);
                        recyclerView.setLayoutManager(new LinearLayoutManager
                                (MainActivity.this, RecyclerView.VERTICAL, false));
                        recyclerView.setAdapter(userListAdapter);
                        userListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initialization() {
        recyclerView = findViewById(R.id.recyclerViewId);
    }
}