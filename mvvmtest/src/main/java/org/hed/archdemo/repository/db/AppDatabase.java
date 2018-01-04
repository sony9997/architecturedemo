package org.hed.archdemo.repository.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import org.hed.archdemo.vo.User;
import org.hed.archdemo.repository.db.dao.UserDao;

/**
 * Created by hedong on 2017/11/28.
 */
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "basic-admin-db";

    public abstract UserDao userDao();
}
