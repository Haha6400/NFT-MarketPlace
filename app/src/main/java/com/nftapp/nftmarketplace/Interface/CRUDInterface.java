package com.nftapp.nftmarketplace.Interface;

import com.nftapp.nftmarketplace.Model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CRUDInterface {
    @GET("items")
    Call<List<Item>> getAll();
}
