package com.icarasia.sample.main

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*

class MainPresenterTest {

    lateinit var presenter: MainPresenterImpl
    lateinit var view: IMainView
    lateinit var model: IMainModel
    @Before
    fun setup() {
        view = mock(IMainView::class.java)
        model = mock(IMainModel::class.java)
        presenter = MainPresenterImpl(view,model)

    }

    @Test
    fun `when editMobileNumber called verify editMobileDialog`(){

        presenter.editMobileNumber()
        verify(view, times(1)).editMobileDialog()

    }

    @Test
    fun `Wrong mobile number should show error message`(){

        presenter.updateMobileNumber("","")
        verify(view, times(1)).showMessage()
    }


    @Test
    fun `throw exception if email is null`() {
        `when`(presenter.showUserType(null)).thenReturn(NullPointerException::class.java.name);
    }

    @Test
    fun `return true when logout called`(){

//        `when`(presenter.logout()).thenReturn(false)
//        Assert.assertFalse(presenter.logout())
        Assert.assertTrue(presenter.logout())
//        Assert.assertEquals(, false);
    }


}