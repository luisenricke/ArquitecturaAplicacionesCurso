package com.luisvillalobos.dev.daggerejemplointento2.http;

import com.luisvillalobos.dev.daggerejemplointento2.http.spacex.rocket.Rocket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpaceXAPI {

    @GET("rockets")
    Call<List<Rocket>> getAllRockets();
}
