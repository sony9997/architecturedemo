package org.hed.archdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import org.hed.archdemo.injection.UserComponent;
import org.hed.archdemo.model.User;
import org.hed.archdemo.repository.UserRepository;

/**
 * Created by hedong on 2017/11/28.
 */

public class UserViewModel extends AndroidViewModel implements UserComponent.Injectable{
    private LiveData<List<User>> users;

    @Inject
    UserRepository userRepository;

    public UserViewModel(@NonNull Application application){
        super(application);
    }

    @Override
    public void inject(UserComponent userComponent) {
        userComponent.inject(this);
        users=userRepository.loadAllUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }
}
