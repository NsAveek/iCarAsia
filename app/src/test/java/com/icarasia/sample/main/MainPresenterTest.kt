package com.icarasia.sample.main

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*

class MainPresenterTest {

    lateinit var presenter: MainPresenterImpl
    @Before
    fun setup() {
        presenter=Mockito.mock(MainPresenterImpl::class.java)

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