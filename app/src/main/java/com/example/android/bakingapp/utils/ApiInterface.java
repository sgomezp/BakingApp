package com.example.android.bakingapp.utils;

import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Step;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipe();

    @GET("baking.json")
    Call<ArrayList<Step>> getSteps();




}
