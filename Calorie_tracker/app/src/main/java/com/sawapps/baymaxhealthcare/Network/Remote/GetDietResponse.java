package com.sawapps.baymaxhealthcare.Network.Remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sawapps.baymaxhealthcare.Network.Responses.Item;

import java.util.ArrayList;
import java.util.List;


public class GetDietResponse {


    @SerializedName("items")
    @Expose
    public List<Item> items = new ArrayList<Item>();

}