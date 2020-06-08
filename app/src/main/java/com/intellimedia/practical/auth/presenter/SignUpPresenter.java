package com.intellimedia.practical.auth.presenter;

import android.os.AsyncTask;

import com.intellimedia.practical.auth.model.UserTable;
import com.intellimedia.practical.db.UserDao;
import com.intellimedia.practical.db.UserDatabase;

import java.util.concurrent.ExecutionException;

public class SignUpPresenter {
    private static UserTable userTable;
    private static UserDao userDao = UserDatabase.INSTANCE.userDao();

    public boolean isUserNameAvailable(String userName) {
        try {
            userTable = new FetchUserDetailsTask(userName, true).execute().get();
        } catch (ExecutionException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }
        return userTable != null;
    }

    public boolean isUserEmailAvailable(String email) {
        try {
            userTable = new FetchUserDetailsTask(email, false).execute().get();
        } catch (ExecutionException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }
        return userTable != null;
    }

    public void addUser(UserTable userTable) {
        new AddUserTask(userTable).execute();
    }

    static class FetchUserDetailsTask extends AsyncTask<UserTable, Void, UserTable> {

        String field;
        boolean isUserName;

        FetchUserDetailsTask(String field, boolean isUserName) {
            this.field = field;
            this.isUserName = isUserName;
        }

        @Override
        protected UserTable doInBackground(UserTable... userTables) {
            return isUserName ? userDao.getUserDetailsByUsername(field) : userDao.getUserDetails(field);
        }

        @Override
        protected void onPostExecute(UserTable userTable) {
            super.onPostExecute(userTable);
            SignUpPresenter.userTable = userTable;
        }
    }

    static class AddUserTask extends AsyncTask<String, Void, UserTable> {

        UserTable userTable;

        AddUserTask(UserTable userTable) {
            this.userTable = userTable;
        }

        @Override
        protected UserTable doInBackground(String... userTables) {
            userDao.insertDetails(userTable);
            return userTable;
        }

        @Override
        protected void onPostExecute(UserTable userTable) {
            super.onPostExecute(userTable);
            SignUpPresenter.userTable = userTable;
        }
    }
}
