package com.example.imagemachine.feature.machine.insert.view;

import android.app.DatePickerDialog;
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
import androidx.appcompat.widget.Toolbar;

import com.example.imagemachine.R;
import com.example.imagemachine.data.RoomModule;
import com.example.imagemachine.data.model.LocalMachineDataSource;
import com.example.imagemachine.feature.base.view.BaseActivity;
import com.example.imagemachine.feature.machine.insert.presenter.IMachineInsertPresenter;
import com.example.imagemachine.feature.machine.insert.presenter.MachineInsertPresenter;
import com.example.imagemachine.feature.main.view.MainActivity;
import com.example.imagemachine.utils.Constant;
import com.example.imagemachine.utils.DateUtil;

import java.util.Calendar;

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
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    //
    // MARK: - Component View
    //
    private Toolbar toolbar;
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

        if (getIntent().getExtras() != null) {
            this.BUNDLE_BARCODE_VALUE = getIntent().getExtras().getString(Constant.KEY_BARCODE_VALUE);
        } else {
            Toast.makeText(this, "Sorry, try again", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }

        this.toolbar = findViewById(R.id.toolbar);
        this.textViewMachineQrCode = findViewById(R.id.textViewMachineQrCode);
        this.editTextMachineName = findViewById(R.id.editTextMachineName);
        this.editTextMachineType = findViewById(R.id.editTextMachineType);
        this.textViewMaintainDate = findViewById(R.id.textViewMaintainDate);
        this.buttonActionSave = findViewById(R.id.buttonActionSave);

        this.presenter = new MachineInsertPresenter<IMachineInsertView>(this,
                new LocalMachineDataSource(RoomModule.getInstance(this).machineDao()));
        this.calendar = Calendar.getInstance();
        int year = this.calendar.get(Calendar.YEAR);
        int month = this.calendar.get(Calendar.MONTH);
        int day = this.calendar.get(Calendar.DAY_OF_MONTH);
        this.datePickerDialog = new DatePickerDialog(this, (datePicker, y, m, d) -> {
            this.calendar.set(y, m, d);
            this.textViewMaintainDate.setText(DateUtil.formatToDmy(this.calendar.getTime()));
        }, year, month, day);

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
        this.textViewMaintainDate.setOnClickListener(this);
        this.buttonActionSave.setOnClickListener(this);
    }

    @Override
    protected void preparingView() {
        setSupportActionBar(this.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if (!this.BUNDLE_BARCODE_VALUE.isEmpty()) {
            this.textViewMachineQrCode.setText(this.BUNDLE_BARCODE_VALUE);
        }

        this.textViewMaintainDate.setText(DateUtil.formatToDmy(Calendar.getInstance().getTime()));
    }

    //
    // MARK: - Override Function of IMachineInsertView
    //
    @Override
    public void goToMainActivity() {
        startActivity(MainActivity.startIntent(this));
        finish();
    }

    @Override
    public void onSuccessInsertMachineData() {
        Toast.makeText(this, "Success inserted machine data", Toast.LENGTH_SHORT).show();
        goToMainActivity();
    }

    //
    // MARK: - Override Function of View OnClickListener
    //
    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.buttonActionSave:
                    if (this.editTextMachineName.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Name can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (this.editTextMachineType.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Type can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.KEY_NAME, this.editTextMachineName.getText().toString());
                    bundle.putString(Constant.KEY_TYPE, this.editTextMachineType.getText().toString());
                    bundle.putString(Constant.KEY_DATE, this.textViewMaintainDate.getText().toString());
                    bundle.putString(Constant.KEY_BARCODE_VALUE, this.textViewMachineQrCode.getText().toString());

                    this.presenter.onButtonActionSaveClicked(bundle);
                    break;
                case R.id.textViewMaintainDate:
                    this.datePickerDialog.show();
                    break;
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
