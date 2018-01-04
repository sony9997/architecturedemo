package org.hed.archdemo.ui.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import org.hed.archdemo.vo.User;
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
