package com.icarasia.sample.login.fragment.registrationFragment;

import android.util.Log;

import com.icarasia.sample.model.User;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Aveek on 07/12/2017.
 */

public class RegistrationFragmentModel implements IRegistrationFragmentModel{
    private Realm realm;

    public RegistrationFragmentModel(){
        realm = Realm.getDefaultInstance();
    }
    @Override
    public boolean checkEmailExistence(String email) {
        try {
            if (!realm.isInTransaction()) {
                realm.beginTransaction();
            }

            RealmResults<User> userRealmResults = realm.where(User.class).findAll();
            for (User result : userRealmResults) {
                if (result.getEmail().equals(email.trim())) {
                    Log.d("email already existed: ", result.getEmail());
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveIntoDatabase(String firstName,String lastName,String email,String pass,String mobile,String userType) {
        try {

            if (!realm.isInTransaction()) {
                realm.beginTransaction();
            }

            Number currentIdNum = realm.where(User.class).max("userId");
            int nextId = currentIdNum == null? 1 : currentIdNum.intValue() + 1;

            User user = new User();
            user.setUserId(nextId);
            user.setFirstName(firstName.trim());
            user.setLastName(lastName.trim());
            user.setEmail(email.trim());
            user.setPassword(pass.trim());
            user.setMobileNumber(mobile.trim());
            user.setUserType(userType);


            realm.copyToRealmOrUpdate(user);
            realm.commitTransaction();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("error : ",e.toString());
        }
        return false;
    }
}
