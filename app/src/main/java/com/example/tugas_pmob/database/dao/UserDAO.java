package com.example.tugas_pmob.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tugas_pmob.database.entity.User;

@Dao
public interface UserDAO {
    @Insert
    void regUser(User user);

    @Query("SELECT * FROM user WHERE nim=:nim AND password=:password")
    User login(String nim, String password);
}
