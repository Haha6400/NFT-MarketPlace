package com.nftapp.nftmarketplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.nftapp.nftmarketplace.Interface.CRUDInterface;
import com.nftapp.nftmarketplace.Model.Item;
import com.nftapp.nftmarketplace.Utils.Constants;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

//    RecyclerView recyclerView;

    List<Item> itemList;
    CRUDInterface crudInterface;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        callAPI();
        getAll();
    }

    private void getAll(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface = retrofit.create(CRUDInterface.class);
        Call<List<Item>> callItem = crudInterface.getAll();
        callItem.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.message());
                    return;
                }
                itemList = response.body();
                itemList.forEach(item->System.out.println(item.toString()));
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

//    protected void callAPI(){
//        // Initialize OkHttpClient to get data
//        OkHttpClient client = new OkHttpClient();
//
//        // Initialize Moshi adapter to convert json to User model
//        Moshi moshi = new Moshi.Builder().build();
//        Type usersType = Types.newParameterizedType(List.class, User.class);
//        final JsonAdapter<List<User>> jsonAdapter = moshi.adapter(usersType);
//
//        String baseUrl = "https://api.opensea.io/";
//        String apiKey = "98f9c9e4354c4c24a3d7e866bb85718c";
//        String ownerAddress = "0xDE8e6D7F75b940f83507187373187fAFbF53226d";
//        String orderDirection = "desc";
//        int limit = 100;
//
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + "api/v1/assets").newBuilder();
//        urlBuilder.addQueryParameter("owner", ownerAddress);
//        urlBuilder.addQueryParameter("order_direction", orderDirection);
//        urlBuilder.addQueryParameter("limit", String.valueOf(limit));
//
//        String url = urlBuilder.build().toString();
//
//        // Make a request to the server
//        Request request = new Request.Builder()
//                .url(url)
//                .header("X-API-KEY", apiKey)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                Log.e("Error", "Network Error");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                // Get return JSON information
//                String json = response.body().string();
//                final List<User> users = jsonAdapter.fromJson(json);
//
//                //Display lên RecyclerView
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        recyclerView.setAdapter(new UserAdapter(users, MainActivity.this));
//                    }
//                });
//            }
//        });
//    }
}