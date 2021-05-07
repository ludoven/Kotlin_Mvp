package com.ludoven.mvp.presenter

import android.util.Log
import com.example.myapplication.base.BasePresenter
import com.ludoven.mvp.base.BaseBean
import com.ludoven.mvp.contract.MainContract
import com.ludoven.mvp.contract.MainContract.Presenter
import com.ludoven.mvp.http.Api
import com.ludoven.mvp.http.BaseResourceObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomePresenter :
    BasePresenter<MainContract.View>(),
    MainContract.Presenter {
    override fun testPresenter() {
        Api.getInstance().getApiService()
            .getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (object : BaseResourceObserver<String>() {
                override fun onSubscribe(d: Disposable) {
                    addReqs(d)
                }

                override fun onNext(t: String) {
                    mView!!.testView(t)
                }
            })
    }
}