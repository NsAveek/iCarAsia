package com.icarasia.sample.main;

import com.icarasia.sample.model.User;

/**
 * Created by Aveek on 06/12/2017.
 */

public class MainPresenterImpl implements IMainPresenter,IMainModel,IMainModel.OnFinishedListener{

    private IMainView mainView;
    private IMainModel mainModel;


    public MainPresenterImpl (IMainView mainView,IMainModel mainModel){
        this.mainView = mainView;
        this.mainModel= mainModel;
    }

    @Override
    public void editMobileNumber(){
        mainView.editMobileDialog();
    }

    @Override
    public void updateMobileNumber(String email, String newMobileNumber) {
        try{
            if (mainModel.updateUserMobileModel(email,newMobileNumber)){
                mainView.showMessage("Successfully Updated");
            }else {
                mainView.showMessage("Update Unsuccessful");
            }
        }catch (Exception e){
            mainView.showMessage("Error Occurred ! ");
        }
    }

    @Override
    public String showUserType(String email) {
        try {
           return "Your account type is "+getUserInfo(email).getUserType();
        }catch (Exception e){
            return "Error Occurred!";
        }
    }

    @Override
    public boolean logout() {
        mainModel.logoutModel(this);
        return true;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public User getUserInfo(String email) throws Exception{
        return mainModel.getUserInfo(email);
    }

    @Override
    public boolean updateUserMobileModel(String email, String newMobileNumber) throws Exception {
        return false;
    }

    @Override
    public void logoutModel(OnFinishedListener listener) {

    }

    @Override
    public void onRunning() {
        mainView.showDialog("Logging Out");
    }

    @Override
    public void onFinished() {
        mainView.switchActivity();
    }
}
