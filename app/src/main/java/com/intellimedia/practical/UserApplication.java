package com.intellimedia.practical;

import android.app.Application;

import com.intellimedia.practical.db.UserDatabase;

public class UserApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UserDatabase.getDatabase(this);
    }
}
