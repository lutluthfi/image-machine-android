package com.example.imagemachine.feature.base.view

import android.support.v7.app.AppCompatActivity
import com.example.imagemachine.utils.LoadingView

abstract class BaseActivity: AppCompatActivity(), IBaseView {

    private lateinit var loadingView: LoadingView

    override fun dismissLoadingView() {
        runOnUiThread {
            if (this.loadingView.isLoadingDialogDidShow()) {
                this.loadingView.dismissLoadingView()
            }
        }
    }

    override fun isLoadingViewDidShow(): Boolean {
        return this.loadingView.isLoadingDialogDidShow()
    }

    override fun showLoadingView(message: String) {
        runOnUiThread {
            if (this.loadingView.isLoadingDialogDidShow()) {
                this.loadingView.dismissLoadingView()
            }
            this.loadingView.showLoadingView(message = message)
        }
    }
}