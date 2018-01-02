package org.hed.archdemo.ui.user;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import org.hed.archdemo.model.User;
import org.hed.archdemo.repository.UserRepository;

/**
 * Created by hedong on 2017/11/28.
 */

public class UserViewModel extends ViewModel {
    private LiveData<List<User>> users;

    @Inject
    public UserViewModel(UserRepository userRepository){
        users=userRepository.loadAllUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }
}
