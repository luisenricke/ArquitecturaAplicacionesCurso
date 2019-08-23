package com.luisvillalobos.dev.daggerejemplointento2;

import com.luisvillalobos.dev.daggerejemplointento2.login.LoginActivityMVP;
import com.luisvillalobos.dev.daggerejemplointento2.login.LoginActivityPresenter;
import com.luisvillalobos.dev.daggerejemplointento2.login.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterUnitTest {

    LoginActivityPresenter presenter;
    User user;

    LoginActivityMVP.Model mocketModel;
    LoginActivityMVP.View mocketView;

    @Before
    public void init() {
        //Configurar la instancias de la clase a simular
        mocketModel = mock(LoginActivityMVP.Model.class);
        mocketView = mock(LoginActivityMVP.View.class);

        //Configurar variables randoms
        user = new User("Luis", "Villalobos");

        //Injectar variables a las instancias simuladas
        //when(mocketModel.getUser()).thenReturn(user);
        //when(mocketView.getFirstName()).thenReturn("Antonio");
        //when(mocketView.getLastName()).thenReturn("Banderas");

        //Configurar las instancias concretas
        presenter = new LoginActivityPresenter(mocketModel);
        presenter.setView(mocketView);
    }

    @Test
    public void noExisttInteractionsWithView() {
        presenter.getCurrentUser();
        //verifyZeroInteractions(mocketView);
        verify(mocketView, times(1)).showUserNotAvailable();
    }

    @Test
    public void loadUserFromTheRepositoryWhenValidUserISPresent() {
        when(mocketModel.getUser()).thenReturn(user);
        presenter.getCurrentUser();

        //Comprobamos la interaccion con el modelo de usuario
        verify(mocketModel, times(1)).getUser();

        //Comprobamos la interaccion con la vista
        verify(mocketView, times(1)).setFirstName("Luis");
        verify(mocketView, times(1)).setLastName("Villalobos");
        verify(mocketView, never()).showUserNotAvailable();
    }

    @Test
    public void showErrorMessagesWhenUserIsNull() {
        when(mocketModel.getUser()).thenReturn(null);
        presenter.getCurrentUser();

        verify(mocketModel, times(1)).getUser();

        verify(mocketView, never()).setFirstName("Antonio");
        verify(mocketView, never()).setLastName("Banderas");
        verify(mocketView, times(1)).showUserNotAvailable();
    }

    @Test
    public void createErrorMessageIfAnyFieldisEmpty() {
        //Vacio el primer elemento
        when(mocketView.getFirstName()).thenReturn("");
        //when(mocketView.getLastName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mocketView, times(1)).getFirstName();
        verify(mocketView, never()).getLastName();
        verify(mocketView, times(1)).showInputError();

        //Vacio el segundo elemento
        when(mocketView.getFirstName()).thenReturn("Luis");
        when(mocketView.getLastName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mocketView, times(2)).getFirstName();
        verify(mocketView, times(1)).getLastName();
        verify(mocketView, times(2)).showInputError();
    }

    @Test
    public void saveValidUser(){
        when(mocketView.getFirstName()).thenReturn("Luis");
        when(mocketView.getLastName()).thenReturn("Villalobos");

        presenter.loginButtonClicked();

        verify(mocketView, times(2)).getFirstName();
        verify(mocketView, times(2)).getLastName();

        verify(mocketModel,times(1)).createUser("Luis","Villalobos");

        verify(mocketView,times(1)).showUserSaved();
    }
}
