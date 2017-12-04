package com.icarasia.sample.presenter.adapter;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.icarasia.sample.view.fragment.LoginFragment;
import com.icarasia.sample.view.fragment.RegistrationFragment;

/**
 * Created by admin on 11/17/2016.
 */
public class LoginPagerAdapter extends FragmentStatePagerAdapter{
    int mNumOfTabs;
    LoginFragment tabLogin;
    RegistrationFragment tabRegistration;

    public LoginPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tabLogin = new LoginFragment();
                return tabLogin;
            case 1:
                tabRegistration = new RegistrationFragment();
                return tabRegistration;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

//    @Override
//    public void onDBTByDoFragmentInteraction() {
//        if (tabDBT!=null){
//            tabDBT.someMethod();
//        }
//    }

//    public interface OnDBTPagerInteractionListener {
//        // TODO: Update argument type and name
//        void onDBTPagerInteraction(Uri uri);
//    }
}
