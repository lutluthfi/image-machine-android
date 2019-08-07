package com.example.imagemachine.feature.main.presenter

import com.example.imagemachine.feature.base.presenter.BasePresenter
import com.example.imagemachine.feature.main.view.IMainView

internal class MainPresenter<V: IMainView>(view: V): BasePresenter<V>(view), IMainPresenter<V> {
}