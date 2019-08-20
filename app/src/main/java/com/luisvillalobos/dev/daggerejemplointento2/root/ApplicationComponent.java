package com.luisvillalobos.dev.daggerejemplointento2.root;



import com.luisvillalobos.dev.daggerejemplointento2.login.LoginActivity;
import com.luisvillalobos.dev.daggerejemplointento2.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity target);
}
