package com.example.imagemachine.feature.machine.insert.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.imagemachine.R;
import com.example.imagemachine.feature.base.view.BaseActivity;
import com.example.imagemachine.feature.machine.insert.presenter.IMachineInsertPresenter;
import com.example.imagemachine.feature.machine.insert.presenter.MachineInsertPresenter;

public class MachineInsertActivity extends BaseActivity implements IMachineInsertView {

    //
    // MARK: - Dependencies
    //
    private IMachineInsertPresenter presenter;

    //
    // MARK: - Component View
    //
    private TextView textViewMachineQrCode;
    private EditText editTextMachineName;
    private EditText editTextMachineType;
    private TextView textViewMaintainDate;
    private Button buttonActionSave;

    //
    // MARK: - Override Function of Activity Lifecycle
    //
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_insert);
        this.textViewMachineQrCode = findViewById(R.id.textViewMachineQrCode);
        this.editTextMachineName = findViewById(R.id.editTextMachineName);
        this.editTextMachineType = findViewById(R.id.editTextMachineType);
        this.textViewMaintainDate = findViewById(R.id.textViewMaintainDate);
        this.buttonActionSave = findViewById(R.id.buttonActionSave);
        this.presenter = new MachineInsertPresenter<IMachineInsertView>(this);
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
    }

    @Override
    protected void preparingView() {
    }
}
