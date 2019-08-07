package com.example.imagemachine.feature.base.view

interface IBaseView {

    fun dismissLoadingView()

    fun isLoadingViewDidShow(): Boolean

    fun showLoadingView(message: String)

}