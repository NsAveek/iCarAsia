package com.icarasia.sample.login.fragment.loginFragment

import android.text.Editable
import android.widget.EditText
import com.icarasia.sample.R
import com.icarasia.sample.model.Validator
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.`when` as localWhen


class LoginFragmentPresenterImplTest {

    lateinit var parentView : LoginFragment
    lateinit var view: ILoginFragmentView
    lateinit var model: ILoginFragmentModel
    lateinit var validator: Validator
    lateinit var presenter : LoginFragmentPresenterImpl
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText

    @Before
    fun `setup`(){
//        parentView = spy(LoginFragment.newInstance("","")::class.java)
        parentView = spy(LoginFragment.newInstance("","")::class.java)
        view = mock(ILoginFragmentView::class.java)
        model = mock(ILoginFragmentModel::class.java)
        validator = mock(Validator::class.java)
        presenter = LoginFragmentPresenterImpl(view,model)
        emailEditText = parentView.view!!.findViewById(R.id.et_login_email)
        passwordEditText = parentView.thisActivity.findViewById(R.id.et_login_password)
    }

    @Test
    fun `validate fields`(){

    }
    @Test
    fun `check empty fields`(){
        localWhen(emailEditText.text).thenReturn(Editable.Factory.getInstance().newEditable("Abc"))
        localWhen(passwordEditText.text).thenReturn(Editable.Factory.getInstance().newEditable(""))
//        emailEditText.setText("Abc")
//        passwordEditText.setText("")
        Assert.assertTrue(presenter.checkEmpty(emailEditText,passwordEditText))
    }
}