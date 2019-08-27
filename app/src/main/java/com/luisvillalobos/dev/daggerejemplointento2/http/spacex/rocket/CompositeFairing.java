
package com.luisvillalobos.dev.daggerejemplointento2.http.spacex.rocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompositeFairing {

    @SerializedName("height")
    @Expose
    private Height_ height;
    @SerializedName("diameter")
    @Expose
    private Diameter_ diameter;

    public Height_ getHeight() {
        return height;
    }

    public void setHeight(Height_ height) {
        this.height = height;
    }

    public Diameter_ getDiameter() {
        return diameter;
    }

    public void setDiameter(Diameter_ diameter) {
        this.diameter = diameter;
    }

}
