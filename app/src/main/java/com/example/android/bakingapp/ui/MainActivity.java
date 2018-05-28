package com.example.android.bakingapp.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.RecipeAdapter;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.utils.ApiClient;
import com.example.android.bakingapp.utils.ApiInterface;

import java.nio.channels.NotYetConnectedException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {



    RecipeAdapter.ViewHolder mAdapter;
    RecipeAdapter mRecipeAdapter;
    public static ArrayList<Recipe> mRecipeList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    /*Resources res = getResources();
    int numbersOfColumns = res.getInteger(R.integer.list_columns);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.plant(new Timber.DebugTree());

        recyclerView = findViewById(R.id.rv_recipes);
        Resources res = getResources();
        int numbersOfColumns = res.getInteger(R.integer.list_columns);

        // use a linearLayout manager
        layoutManager = new GridLayoutManager(this, numbersOfColumns);
        recyclerView.setLayoutManager(layoutManager);


        //Load recipe data
        loadRecipeData();

    }

    private void loadRecipeData() {

        try{
            Timber.d("Estoy dentro del Try en loadRecipeData en MainActivity");
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ArrayList<Recipe>> call = apiService.getRecipe();
            Timber.d("call: " + call);

            call.enqueue(new Callback<ArrayList<Recipe>>() {
                @Override
                public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()){
                       mRecipeList = response.body();

                       if (mRecipeAdapter == null){
                           recyclerView.setAdapter(new RecipeAdapter(mRecipeList,
                                  R.layout.list_item_recipe ,getApplicationContext()));
                           recyclerView.setHasFixedSize(false);
                       } else {
                           mRecipeAdapter.updateRecyclerData(mRecipeList);
                           mRecipeAdapter.notifyDataSetChanged();
                       }

                    } else {
                        Timber.d("onResponse: StatusCode; " + statusCode);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                    Timber.d("onResponse: Error en Failure; ");

                }
            });
        } catch (NotYetConnectedException e){
            Timber.d("loadRecipeData: No connectivity: " + e.getMessage());
        }
    }


}
