package com.example.imagemachine.feature.base.presenter

import com.example.imagemachine.feature.base.view.IBaseView

interface IBasePresenter<V: IBaseView> {
    fun viewDidAttach()

    fun viewDidDetach()
}