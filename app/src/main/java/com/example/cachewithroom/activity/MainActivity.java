package com.example.cachewithroom.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cachewithroom.R;
import com.example.cachewithroom.adapter.AnotherUserListAdapter;
import com.example.cachewithroom.adapter.UserListAdapter;
import com.example.cachewithroom.apiClass.ApiInterface;
import com.example.cachewithroom.apiClass.RetrofitInstance;
import com.example.cachewithroom.interfaceClass.ReceiveUserInfo;
import com.example.cachewithroom.pojoClass.User;
import com.example.cachewithroom.room.AnotherUser;
import com.example.cachewithroom.room.AnotherUserDao;
import com.example.cachewithroom.room.AnotherUserDatabase;
import com.example.cachewithroom.utilities.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ReceiveUserInfo {
    private RecyclerView recyclerView;
    private List<User> userList;
    private UserListAdapter userListAdapter;
    private AnotherUserDao anotherUserDao;
    private AnotherUserDatabase anotherUserDatabase;
    private List<AnotherUser> anotherUserList;
    private AnotherUserListAdapter anotherUserListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

        fetchData();

        getOfflineData();
    }

    private void getOfflineData() {
        if (!Utility.isNetworkConnected(this)) {
            new GetDataAsyncTask(this, anotherUserDao, anotherUserList,
                    anotherUserListAdapter, recyclerView).execute();
        }
    }

    private void fetchData() {
        if (Utility.isNetworkConnected(this)) {
            fetchUser();
        }
    }

    private void insert(AnotherUser anotherUser) {
        new InsertAsyncTask(anotherUserDao, anotherUserList).execute(anotherUser);
    }

    private void fetchUser() {
        userList.clear();
        ApiInterface apiInterface = RetrofitInstance.retrofit().create(ApiInterface.class);
        Call<List<User>> call = apiInterface.getUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        userList = response.body();

                        userListAdapter = new UserListAdapter(MainActivity.this, true,
                                userList, MainActivity.this);
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
        userList = new ArrayList<>();
        anotherUserDatabase = AnotherUserDatabase.getAnotherUserDatabase(this);
        anotherUserDao = anotherUserDatabase.anotherUserDao();
    }

    private static class InsertAsyncTask extends AsyncTask<AnotherUser, Void, Void> {
        AnotherUserDao anotherUserDao;
        List<AnotherUser> anotherUserList;

        @SuppressLint("StaticFieldLeak")

        public InsertAsyncTask(AnotherUserDao anotherUserDao, List<AnotherUser> anotherUserList) {
            this.anotherUserDao = anotherUserDao;
            this.anotherUserList = anotherUserList;
        }

        @Override
        protected Void doInBackground(AnotherUser... anotherUsers) {
            anotherUserDao.insert(anotherUsers[0]);
            anotherUserList = anotherUserDao.getAllAnotherUserList();
            return null;
        }
    }

    private static class GetDataAsyncTask extends AsyncTask<Void, Void, Void> {
        @SuppressLint("StaticFieldLeak")
        Context context;
        AnotherUserDao anotherUserDao;
        List<AnotherUser> anotherUserList;
        @SuppressLint("StaticFieldLeak")
        AnotherUserListAdapter anotherUserListAdapter;
        @SuppressLint("StaticFieldLeak")
        RecyclerView recyclerView;

        public GetDataAsyncTask(Context context, AnotherUserDao anotherUserDao, List<AnotherUser> anotherUserList,
                                AnotherUserListAdapter anotherUserListAdapter, RecyclerView recyclerView) {
            this.context = context;
            this.anotherUserDao = anotherUserDao;
            this.anotherUserList = anotherUserList;
            this.anotherUserListAdapter = anotherUserListAdapter;
            this.recyclerView = recyclerView;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            anotherUserList = anotherUserDao.getAllAnotherUserList();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (anotherUserList.size() != 0) {
                anotherUserListAdapter = new AnotherUserListAdapter(context, anotherUserList);
                recyclerView.setLayoutManager(new LinearLayoutManager
                        (context, RecyclerView.VERTICAL, false));
                recyclerView.setAdapter(anotherUserListAdapter);
                anotherUserListAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void userInfo(String id, String name) {
        insert(new AnotherUser(id, name));
    }
}