
package com.luisvillalobos.dev.daggerejemplointento2.http.spacex.rocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Engines {

    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("layout")
    @Expose
    private Object layout;
    @SerializedName("engine_loss_max")
    @Expose
    private Object engineLossMax;
    @SerializedName("propellant_1")
    @Expose
    private String propellant1;
    @SerializedName("propellant_2")
    @Expose
    private String propellant2;
    @SerializedName("thrust_sea_level")
    @Expose
    private ThrustSeaLevel_ thrustSeaLevel;
    @SerializedName("thrust_vacuum")
    @Expose
    private ThrustVacuum_ thrustVacuum;
    @SerializedName("thrust_to_weight")
    @Expose
    private Object thrustToWeight;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getLayout() {
        return layout;
    }

    public void setLayout(Object layout) {
        this.layout = layout;
    }

    public Object getEngineLossMax() {
        return engineLossMax;
    }

    public void setEngineLossMax(Object engineLossMax) {
        this.engineLossMax = engineLossMax;
    }

    public String getPropellant1() {
        return propellant1;
    }

    public void setPropellant1(String propellant1) {
        this.propellant1 = propellant1;
    }

    public String getPropellant2() {
        return propellant2;
    }

    public void setPropellant2(String propellant2) {
        this.propellant2 = propellant2;
    }

    public ThrustSeaLevel_ getThrustSeaLevel() {
        return thrustSeaLevel;
    }

    public void setThrustSeaLevel(ThrustSeaLevel_ thrustSeaLevel) {
        this.thrustSeaLevel = thrustSeaLevel;
    }

    public ThrustVacuum_ getThrustVacuum() {
        return thrustVacuum;
    }

    public void setThrustVacuum(ThrustVacuum_ thrustVacuum) {
        this.thrustVacuum = thrustVacuum;
    }

    public Object getThrustToWeight() {
        return thrustToWeight;
    }

    public void setThrustToWeight(Object thrustToWeight) {
        this.thrustToWeight = thrustToWeight;
    }

}
