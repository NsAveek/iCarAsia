package com.icarasia.sample.main;

import com.icarasia.sample.R;
import com.icarasia.sample.application.ICarAsia;
import com.icarasia.sample.model.User;
import com.icarasia.sample.model.Validator;

/**
 * Created by Aveek on 06/12/2017.
 */

public class MainPresenterImpl implements IMainPresenter,IMainModel,IMainModel.OnFinishedListener{

    private IMainView mainView;
    private IMainModel mainModel;
    private Validator mValidator;


    public MainPresenterImpl (IMainView mainView,IMainModel mainModel){
        this.mainView = mainView;
        this.mainModel= mainModel;
        mValidator = new Validator();
    }

    @Override
    public void editMobileNumber(){
        mainView.editMobileDialog();
    }

    @Override
    public void updateMobileNumber(String email, String newMobileNumber) {
        if (mValidator.validateMobile(newMobileNumber)) {
            try {
                if (mainModel.updateUserMobileModel(email, newMobileNumber)) {
                    mainView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.success_update));
                    setTextViewValues(email);
                } else {
                    mainView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.failed_update));
                }
            } catch (Exception e) {
                mainView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.error_general));
            }
        }else{
            mainView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.error_mobile_validation));
        }
    }

    @Override
    public String showUserType(String email) {
        try {
           return "Your account type is "+getUserInfo(email).getUserType();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Override
    public boolean logout() {
        mainModel.logoutModel(this);
        return true;
    }

    @Override
    public void setTextViewValues(String email) {
        try {
           User user = mainModel.getUserInfo(email);
           if (user!=null) mainView.assignTextValues(user.getFirstName(),user.getLastName(),user.getMobileNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
