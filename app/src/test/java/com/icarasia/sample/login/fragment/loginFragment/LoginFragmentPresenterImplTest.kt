package com.icarasia.sample.login.fragment.loginFragment

import android.test.mock.MockContext
import android.widget.EditText
import com.icarasia.sample.R
import com.icarasia.sample.model.Validator
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

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
        parentView = mock(LoginFragment()::class.java)
        view = mock(ILoginFragmentView::class.java)
        model = mock(ILoginFragmentModel::class.java)
        validator = mock(Validator::class.java)
        presenter = LoginFragmentPresenterImpl(view,model)
        emailEditText = parentView.thisActivity.findViewById(R.id.et_login_email)
        passwordEditText = mock(EditText::class.java)
    }

    @Test
    fun `validate fields`(){

    }
    @Test
    fun `check empty fields`(){
//        `when`(emailEditText.text).thenReturn()
//        `when`(passwordEditText.text).thenReturn()
        emailEditText.setText("A")
        passwordEditText.setText("")
        Assert.assertTrue(presenter.checkEmpty(emailEditText,passwordEditText))
    }
}