package com.example.imagemachine.feature.launch.view

import android.os.Bundle
import android.os.Handler
import com.example.imagemachine.R
import com.example.imagemachine.feature.base.view.BaseActivity
import com.example.imagemachine.feature.launch.presenter.ILaunchPresenter
import com.example.imagemachine.feature.launch.presenter.LaunchPresenter
import com.example.imagemachine.feature.main.view.MainActivity

class LaunchActivity: BaseActivity(), ILaunchView {

    private lateinit var presenter: ILaunchPresenter<ILaunchView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        this.presenter = LaunchPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        this.presenter.viewDidAttach()
        Handler().postDelayed({ goToMainActivity() }, DELAY)
    }

    override fun goToMainActivity() {
        startActivity(MainActivity.startIntent(this))
        finish()
    }

    companion object {
        const val DELAY: Long = 2000 // in milliseconds
    }
}