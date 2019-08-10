package com.example.imagemachine.feature.main.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import com.example.imagemachine.R;
import com.example.imagemachine.feature.base.view.BaseActivity;
import com.example.imagemachine.feature.machine.list.view.MachineListActivity;
import com.example.imagemachine.feature.main.presenter.IMainPresenter;
import com.example.imagemachine.feature.main.presenter.MainPresenter;
import com.example.imagemachine.feature.scanner.view.ScannerActivity;

public class MainActivity extends BaseActivity implements
        IMainView, View.OnClickListener {

    //
    // MARK: - Dependencies
    //
    private IMainPresenter presenter;

    //
    // MARK: - Component View
    //
    private Button machineListButton;
    private Button qrCodeButton;

    //
    // MARK: - Override Function of Activity Lifecycle
    //
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter<IMainView>(this);
        this.machineListButton = findViewById(R.id.buttonMachineList);
        this.qrCodeButton = findViewById(R.id.buttonQrCode);
        this.preparingListener();
        this.preparingView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.presenter.viewDidAttach();
    }

    @Override
    protected void onDestroy() {
        this.presenter.viewDidDetach();
        super.onDestroy();
    }

    //
    // MARK: - Override Function of BaseActivity
    //
    @Override
    protected void preparingListener() {
        this.machineListButton.setOnClickListener(this);
        this.qrCodeButton.setOnClickListener(this);
    }

    @Override
    protected void preparingView() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    BaseActivity.REQUEST_ID_PERMISSION_CAMERA);
        }
    }

    //
    // MARK: - Override Function of View OnClickListener
    //
    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.buttonMachineList: {
                    goToMachineListActivity(new Bundle());
                    break;
                }
                case R.id.buttonQrCode: {
                    goToScannerActivity(new Bundle());
                    break;
                }
            }
        }
    }

    @Override
    public void goToMachineListActivity(@NonNull Bundle bundle) {
        startActivity(MachineListActivity.startIntent(this));
    }

    @Override
    public void goToScannerActivity(@NonNull Bundle bundle) {
        startActivity(ScannerActivity.startIntent(this));
    }

    //
    // MARK: - Function Static
    //
    public static Intent startIntent(@NonNull Context context) {
        return new Intent(context, MainActivity.class);
    }
}
