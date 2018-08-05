package com.icarasia.sample.splash

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertThat
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.eq
import org.mockito.MockitoAnnotations
import java.util.*

class SplashPresenterTest {

    lateinit var presenter : SplashPresenterImpl;
    lateinit var imageList : IntArray

    @Mock
    lateinit var view : ISplashView

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = Mockito.spy(SplashPresenterImpl(view))
        imageList = intArrayOf(34,23,54,12,65)
    }

    @Test
    fun `Get random images from list`(){
//        `when`(presenter.getRandomImageFromList(imageList)).thenReturn(imageList.indexOf())
    }
}