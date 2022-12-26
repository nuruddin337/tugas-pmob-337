package com.example.tugas_pmob.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tugas_pmob.database.dao.UserDAO;
import com.example.tugas_pmob.database.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDB extends RoomDatabase {
    private static final String DBname = "tugasPMOB_337";
    private static UserDB userDB;

    public static synchronized UserDB getUserDB(Context context){
        if(userDB == null){
            userDB = Room.databaseBuilder(context, UserDB.class, DBname).fallbackToDestructiveMigration().build();
        }
        return userDB;
    }

    public abstract UserDAO userDAO();
}
