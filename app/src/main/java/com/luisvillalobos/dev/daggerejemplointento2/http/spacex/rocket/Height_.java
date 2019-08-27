
package com.luisvillalobos.dev.daggerejemplointento2.http.spacex.rocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Height_ {

    @SerializedName("meters")
    @Expose
    private Object meters;
    @SerializedName("feet")
    @Expose
    private Object feet;

    public Object getMeters() {
        return meters;
    }

    public void setMeters(Object meters) {
        this.meters = meters;
    }

    public Object getFeet() {
        return feet;
    }

    public void setFeet(Object feet) {
        this.feet = feet;
    }

}
