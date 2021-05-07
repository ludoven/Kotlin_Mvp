package com.ludoven.mvp.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.dylanc.viewbinding.inflateBindingWithGeneric
import com.example.myapplication.base.BaseContract

/**
 * @author ludoven
 */
abstract class BaseActivity<VB : ViewBinding, P : BaseContract.BasePresenter> : AppCompatActivity(),
    BaseContract.BaseView {
    protected var mPresenter: P? = null
    protected lateinit var binding: VB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = getPresenter()
        mPresenter?.attachView(this)
        binding = this.inflateBindingWithGeneric(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    abstract fun getPresenter(): P
    protected abstract fun initView()
    override fun showError(e: Throwable?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.cancelAll()
            mPresenter!!.detachView()
        }
        System.gc()

    }

}