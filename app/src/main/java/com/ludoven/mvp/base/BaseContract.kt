package com.example.myapplication.base


/**
 * @author ludoven
 */
interface BaseContract {
    interface BasePresenter {
        fun attachView(view: BaseView)
        fun detachView()
        fun cancelAll()
    }

    interface BaseView {
        /**
         * @param flag 用于标记对应接口
         * @param e 错误信息
         */
        fun showError( e: Throwable?)
    }



}