package com.icarasia.sample.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.icarasia.sample.login.fragment.loginFragment.LoginFragment;
import com.icarasia.sample.login.fragment.registrationFragment.RegistrationFragment;

/**
 * Created by Aveek on 12/06/2017.
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
}
