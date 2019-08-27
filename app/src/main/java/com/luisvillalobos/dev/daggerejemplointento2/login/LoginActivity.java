package com.luisvillalobos.dev.daggerejemplointento2.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luisvillalobos.dev.daggerejemplointento2.R;
import com.luisvillalobos.dev.daggerejemplointento2.http.SpaceXAPI;
import com.luisvillalobos.dev.daggerejemplointento2.http.spacex.rocket.Rocket;
import com.luisvillalobos.dev.daggerejemplointento2.root.App;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {

    @Inject
    LoginActivityMVP.Presenter presenter;

    @Inject
    SpaceXAPI spaceXAPI;

    EditText firstName, lastName;
    Button loginButton;

    EditText rocket;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        firstName = findViewById(R.id.txtNombre);
        lastName = findViewById(R.id.txtApellido);
        loginButton = findViewById(R.id.btnEntrar);

        rocket = findViewById(R.id.txtRocket);
        search = findViewById(R.id.btnBuscar);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginButtonClicked();
            }
        });

        //Ejemplo de uso de la api de SpaceX
        Call<List<Rocket>> call = spaceXAPI.getAllRockets();
        call.enqueue(new Callback<List<Rocket>>() {
            @Override
            public void onResponse(Call<List<Rocket>> call, Response<List<Rocket>> response) {
                Iterator<Rocket> iterator = response.body().iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next().getRocketName());
                }
            }

            @Override
            public void onFailure(Call<List<Rocket>> call, Throwable t) {
                Log.e("Peticion", t.getMessage());
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Rocket> call = spaceXAPI.getOneRocketByID(rocket.getText().toString());
                call.enqueue(new Callback<Rocket>() {
                    @Override
                    public void onResponse(Call<Rocket> call, Response<Rocket> response) {

                        if (response.code() == 404) {
                            Log.e("Respuesta", "error");
                            return;
                        }

                        System.out.println(response.body().getRocketName());
                        System.out.println(response.body().getDescription());
                    }

                    @Override
                    public void onFailure(Call<Rocket> call, Throwable t) {
                        Log.e("Peticion", t.getMessage());
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrentUser();
    }

    @Override
    public String getFirstName() {
        return firstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return lastName.getText().toString();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this, "Usuario no disponible", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Error nombre o apellido pueden estar vacios", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(this, "Se guardo el usuario correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }
}
