package com.luisvillalobos.dev.daggerejemplointento2.login;

public interface LoginRepository {

    void saveUser(User user);

    User getUser();
}
