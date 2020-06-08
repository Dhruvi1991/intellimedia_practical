package com.intellimedia.practical.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.intellimedia.practical.auth.model.UserTable;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertDetails(UserTable data);

    @Query("select * from UserDetails WHERE userName = :userName")
    UserTable getUserDetailsByUsername(String userName);

    @Query("select * from UserDetails WHERE email = :email")
    UserTable getUserDetails(String email);

}
