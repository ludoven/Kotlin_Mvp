package com.ludoven.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ludoven.mvp.base.BaseActivity
import com.ludoven.mvp.base.BaseBean
import com.ludoven.mvp.contract.MainContract
import com.ludoven.mvp.databinding.ActivityMainBinding
import com.ludoven.mvp.presenter.HomePresenter

class MainActivity : BaseActivity<ActivityMainBinding, HomePresenter>(),MainContract.View {


    override fun getPresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun initView() {
        binding.homeText.text="test"
        mPresenter?.testPresenter()
    }

    override fun testView(string: String) {
        binding.homeText.text=string
    }

}