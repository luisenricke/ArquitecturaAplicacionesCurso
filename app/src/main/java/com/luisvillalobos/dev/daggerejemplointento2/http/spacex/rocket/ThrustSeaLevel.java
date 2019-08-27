
package com.luisvillalobos.dev.daggerejemplointento2.http.spacex.rocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThrustSeaLevel {

    @SerializedName("kN")
    @Expose
    private Integer kN;
    @SerializedName("lbf")
    @Expose
    private Integer lbf;

    public Integer getKN() {
        return kN;
    }

    public void setKN(Integer kN) {
        this.kN = kN;
    }

    public Integer getLbf() {
        return lbf;
    }

    public void setLbf(Integer lbf) {
        this.lbf = lbf;
    }

}
