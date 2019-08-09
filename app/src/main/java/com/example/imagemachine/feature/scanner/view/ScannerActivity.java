package com.example.imagemachine.feature.scanner.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.imagemachine.R;
import com.example.imagemachine.feature.base.view.BaseActivity;
import com.example.imagemachine.feature.scanner.presenter.IScannerPresenter;
import com.example.imagemachine.feature.scanner.presenter.ScannerPresenter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends BaseActivity implements
        IScannerView, ZXingScannerView.ResultHandler {

    //
    // MARK: - Dependencies
    //
    private IScannerPresenter presenter;
    private ZXingScannerView scannerView;

    //
    // MARK: - Override Function of Activity Lifecycle
    //
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        this.presenter = new ScannerPresenter<IScannerView>(this);
        this.scannerView = new ZXingScannerView(this);
        FrameLayout frameLayout = findViewById(R.id.frameCamera);
        frameLayout.addView(this.scannerView);
        this.preparingListener();
        this.preparingView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.presenter.viewDidAttach();
        this.scannerView.setResultHandler(this);
        this.scannerView.startCamera(-1);
        this.scannerView.setFlash(false);
        this.scannerView.setAutoFocus(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.scannerView.stopCamera();
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
    }

    @Override
    protected void preparingView() {
        List<Integer> selectedIndices = new ArrayList<>();
        List<BarcodeFormat> formats = new ArrayList<>();

        for(int i = 0; i < ZXingScannerView.ALL_FORMATS.size(); i++) {
            selectedIndices.add(i);
        }

        for(int index : selectedIndices) {
            formats.add(ZXingScannerView.ALL_FORMATS.get(index));
        }

        if(this.scannerView != null) {
            this.scannerView.setFormats(formats);
        }
    }

    //
    // MARK: - Override Function of ZXingScannerView ResultHandler
    //
    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_SHORT).show();
    }

    //
    // MARK: - Function Static
    //
    public static Intent startIntent(@NonNull Context context) {
        return new Intent(context, ScannerActivity.class);
    }
}
