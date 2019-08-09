package com.example.imagemachine.feature.machine.list.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.imagemachine.R;
import com.example.imagemachine.feature.base.view.BaseActivity;

public class MachineListActivity extends BaseActivity implements IMachineListView {

    //
    // MARK: - Override Function of Activity Lifecycle
    //
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_list);
        this.preparingListener();
        this.preparingView();
    }

    //
    // MARK: - Override Function of IMachineListView
    //
    @Override
    protected void preparingListener() {

    }

    @Override
    protected void preparingView() {

    }

    //
    // MARK: - Function of Static
    //
    public static Intent startIntent(@NonNull Context context) {
        return new Intent(context, MachineListActivity.class);
    }
}
