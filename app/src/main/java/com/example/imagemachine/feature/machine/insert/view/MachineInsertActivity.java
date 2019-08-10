package com.example.imagemachine.feature.machine.insert.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.imagemachine.R;
import com.example.imagemachine.feature.base.view.BaseActivity;
import com.example.imagemachine.feature.machine.insert.presenter.IMachineInsertPresenter;
import com.example.imagemachine.feature.machine.insert.presenter.MachineInsertPresenter;
import com.example.imagemachine.utils.Constant;

public class MachineInsertActivity extends BaseActivity implements
        IMachineInsertView, View.OnClickListener {

    //
    //
    // MARK: - Variables
    private String BUNDLE_BARCODE_VALUE = "";

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

        if (getIntent().getExtras() != null) {
            this.BUNDLE_BARCODE_VALUE = getIntent().getExtras().getString(Constant.KEY_BARCODE_VALUE);
        } else {
            Toast.makeText(this, "Sorry, try again", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }

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
    }

    @Override
    protected void preparingView() {
        if (!this.BUNDLE_BARCODE_VALUE.isEmpty()) {
            this.textViewMachineQrCode.setText(this.BUNDLE_BARCODE_VALUE);
        }


    }

    //
    // MARK: - Override Function of View OnClickListener
    //

    @Override
    public void onClick(View view) {
        if (view != null) {
            if (view.getId() == R.id.buttonActionSave) {
                if (this.editTextMachineName.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Name can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (this.editTextMachineType.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Type can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                this.presenter.onButtonActionSaveClicked();
            }
            if (view.getId() == R.id.textViewMaintainDate) {

            }
        }
    }

    //
    // MARK: - Function of Static
    //
    public static Intent startIntent(@NonNull Context context) {
        return new Intent(context, MachineInsertActivity.class);
    }
}
