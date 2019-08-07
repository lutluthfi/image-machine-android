package com.example.imagemachine.feature.launch.presenter

import com.example.imagemachine.feature.base.presenter.BasePresenter
import com.example.imagemachine.feature.launch.view.ILaunchView

internal class LaunchPresenter<V : ILaunchView>
internal constructor(view: V) : BasePresenter<V>(view), ILaunchPresenter<V> {

    override fun viewDidAttach() {
        super.viewDidAttach()
    }

    override fun viewDidDetach() {
        super.viewDidDetach()
    }
}