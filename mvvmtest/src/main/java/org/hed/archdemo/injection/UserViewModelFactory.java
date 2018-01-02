package org.hed.archdemo.injection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import org.hed.archdemo.TestApp;
import org.hed.archdemo.viewmodel.UserViewModel;

/**
 * UserViewModel工厂类，注入UserComponent的module到ViewModel
 * Created by hedong on 2017/11/15.
 */
public class UserViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private TestApp application;

    public UserViewModelFactory(TestApp application) {
        this.application = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        UserViewModel t = new UserViewModel(application);
        t.inject(application.getUserComponent());
        return (T) t;
    }
}
