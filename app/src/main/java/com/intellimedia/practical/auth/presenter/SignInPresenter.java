package com.intellimedia.practical.auth.presenter;

import android.os.AsyncTask;

import com.intellimedia.practical.auth.model.UserTable;
import com.intellimedia.practical.db.UserDao;
import com.intellimedia.practical.db.UserDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SignInPresenter {

    private static UserTable userTable;

    public boolean isUserAvailable(String userName) {
        try {
            userTable=  new FetchUserDetailsTask(userName).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        return false;
        }
        return userTable != null;
    }

    public UserTable getUser(String userName) {
        new FetchUserDetailsTask(userName).execute();
        return userTable;
    }

    static class FetchUserDetailsTask extends AsyncTask<UserTable, Void, UserTable> {

        UserDao userDao;
        String userName;

        FetchUserDetailsTask(String userName) {
            this.userName = userName;
            userDao = UserDatabase.INSTANCE.userDao();
        }

        @Override
        protected UserTable doInBackground(UserTable... userTables) {
            return userDao.getUserDetailsByUsername(userName);
        }

        @Override
        protected void onPostExecute(UserTable userTable) {
            super.onPostExecute(userTable);
            SignInPresenter.userTable =userTable;
        }
    }

}

