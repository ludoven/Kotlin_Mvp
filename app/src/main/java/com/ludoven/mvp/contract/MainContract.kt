package com.ludoven.mvp.contract

import com.example.myapplication.base.BaseContract
import com.example.myapplication.base.BaseContract.BaseView
import com.ludoven.mvp.base.BaseBean

interface MainContract : BaseContract {
    interface View : BaseView {
        fun testView(string : String)
    }

    interface Presenter {
        fun testPresenter()
    }
}