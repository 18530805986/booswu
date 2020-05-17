package com.sawapps.baymaxhealthcare.Network.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("day")
    @Expose
    public Integer day;

    @SerializedName("slot")
    @Expose
    public Integer slot;


    @SerializedName("value")
    @Expose
    public String value;

}