package com.example.android.bakingapp.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.annotations.SerializedName;

public class Ingredient extends RecyclerView.ViewHolder {

    @SerializedName("quantity")
    private float quantity;

    @SerializedName("measure")
    private String measure;

    @SerializedName("ingredient")
    private String ingredient;



    /**
     * No args constructor for use in serialization
     *
     */
    //public Ingredient(View view){

    // }

    /**
     *
     * @param measure
     * @param ingredient
     * @param quantity
     */
    public Ingredient(View v, float quantity, String measure, String ingredient) {
        super(v);
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

}
