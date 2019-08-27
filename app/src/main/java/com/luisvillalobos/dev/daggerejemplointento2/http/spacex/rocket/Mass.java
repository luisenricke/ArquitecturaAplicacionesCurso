
package com.luisvillalobos.dev.daggerejemplointento2.http.spacex.rocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mass {

    @SerializedName("kg")
    @Expose
    private Integer kg;
    @SerializedName("lb")
    @Expose
    private Integer lb;

    public Integer getKg() {
        return kg;
    }

    public void setKg(Integer kg) {
        this.kg = kg;
    }

    public Integer getLb() {
        return lb;
    }

    public void setLb(Integer lb) {
        this.lb = lb;
    }

}
