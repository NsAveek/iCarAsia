package com.icarasia.sample.login.fragment.loginFragment;

import com.icarasia.sample.model.User;

import io.realm.Realm;

/**
 * Created by Aveek on 07/12/2017.
 */

public class LoginFragmentModel implements ILoginFragmentModel{

    private Realm realm;

    public LoginFragmentModel(){
        realm = Realm.getDefaultInstance();
    }

    @Override
    public User getUserInfo(String email) throws Exception {
        return null;
    }

    @Override
    public boolean validateLogin(String email, String password) {

        try {
            if (!realm.isInTransaction()) {
                realm.beginTransaction();
            }

            User userRealmResults = realm.where(User.class).equalTo("email", email).equalTo("password",password).findFirst();
            if (userRealmResults!=null) return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
