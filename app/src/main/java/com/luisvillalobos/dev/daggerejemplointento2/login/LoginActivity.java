package com.luisvillalobos.dev.daggerejemplointento2.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luisvillalobos.dev.daggerejemplointento2.R;
import com.luisvillalobos.dev.daggerejemplointento2.http.SpaceXAPI;
import com.luisvillalobos.dev.daggerejemplointento2.http.spacex.rocket.Rocket;
import com.luisvillalobos.dev.daggerejemplointento2.root.App;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {

    @Inject
    LoginActivityMVP.Presenter presenter;

    @Inject
    SpaceXAPI spaceXAPI;
/*
    EditText firstName, lastName;
    Button loginButton;

    EditText rocketE;
    Button search;
    */

    @BindView(R.id.txtNombre)
    EditText txtNombre;
    @BindView(R.id.txtApellido)
    EditText txtApellido;
    @BindView(R.id.btnEntrar)
    Button btnEntrar;
    @BindView(R.id.txtRocket)
    EditText txtRocket;
    @BindView(R.id.btnBuscar)
    Button btnBuscar;

    @BindString(R.string.app_name)
    String app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);
/*
        firstName = findViewById(R.id.txtNombre);
        lastName = findViewById(R.id.txtApellido);
        loginButton = findViewById(R.id.btnEntrar);

        rocketE = findViewById(R.id.txtRocket);
        search = findViewById(R.id.btnBuscar);
*/
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginButtonClicked();
            }
        });

        //Ejemplo de uso de la api de SpaceX
        /*Call<List<Rocket>> call = spaceXAPI.getAllRockets();
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
        */
        /*
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
        */

        spaceXAPI.getAllRocketsObservable().flatMap(new Function<List<Rocket>, Observable<Rocket>>() {
            @Override
            public Observable<Rocket> apply(List<Rocket> rockets) {
                return Observable.fromIterable(rockets);
            }
        }).flatMap(new Function<Rocket, Observable<String>>() {
            @Override
            public Observable<String> apply(Rocket rocket) {
                return Observable.just(rocket.getRocketName());
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("RxJava: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        txtRocket.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                spaceXAPI.getAllRocketsObservable().flatMap(new Function<List<Rocket>, Observable<Rocket>>() {
                    @Override
                    public Observable<Rocket> apply(List<Rocket> rockets) {
                        return Observable.fromIterable(rockets);
                    }
                }).flatMap(new Function<Rocket, Observable<String>>() {
                    @Override
                    public Observable<String> apply(Rocket rocket) {
                        return Observable.just(rocket.getRocketName());
                    }
                }).filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.contains(txtRocket.getText().toString());
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                System.out.println("RxJava: " + s);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }

            @Override
            public void afterTextChanged(Editable s) {

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
        return txtNombre.getText().toString();
    }

    @Override
    public String getLastName() {
        return txtApellido.getText().toString();
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
        this.txtNombre.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        this.txtApellido.setText(lastName);
    }
}
