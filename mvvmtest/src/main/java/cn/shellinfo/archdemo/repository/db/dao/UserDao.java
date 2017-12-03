package org.hed.archdemo.repository.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import org.hed.archdemo.model.User;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by hedong on 2017/11/28.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    LiveData<List<User>> loadAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User userEntity);

    @Delete
    void deleteUser(User event);

    @Update(onConflict = REPLACE)
    void updateUser(User event);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUsers(List<User> users);
}
