package com.example.imagemachine.feature.base.presenter

import com.example.imagemachine.feature.base.view.IBaseView

abstract class BasePresenter<V : IBaseView>(protected var view: V) : IBasePresenter<V> {

    var isViewDidAttach: Boolean = false
        private set

    override fun viewDidAttach() {
        this.isViewDidAttach = true
    }

    override fun viewDidDetach() {
        this.isViewDidAttach = false
    }
}