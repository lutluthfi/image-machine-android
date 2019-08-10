package com.example.imagemachine.feature.machine.list.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.imagemachine.R;
import com.example.imagemachine.data.RoomModule;
import com.example.imagemachine.data.model.LocalMachineDataSource;
import com.example.imagemachine.data.model.Machine;
import com.example.imagemachine.feature.adapter.MachineAdapter;
import com.example.imagemachine.feature.base.view.BaseActivity;
import com.example.imagemachine.feature.machine.list.presenter.IMachineListPresenter;
import com.example.imagemachine.feature.machine.list.presenter.MachineListPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MachineListActivity extends BaseActivity implements
        IMachineListView, View.OnClickListener {

    //
    // MARK: - Variables
    //
    private String[] sorts = new String[]{"Name", "Type"};

    //
    // MARK: - Dependencies
    //
    private IMachineListPresenter presenter;
    private MachineAdapter adapter;

    //
    // MARK: - Component View
    //
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    //
    // MARK: - Override Function of Activity Lifecycle
    //
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_list);

        this.recyclerView = findViewById(R.id.recyclerMachine);
        this.floatingActionButton = findViewById(R.id.fabFilter);

        this.presenter = new MachineListPresenter<IMachineListView>(this,
                new LocalMachineDataSource(RoomModule.getInstance(this).machineDao()));
        this.adapter = new MachineAdapter();

        this.preparingListener();
        this.preparingView();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
        this.floatingActionButton.setOnClickListener(this);
        this.adapter.addItemClickListener((view, machine, position) -> {
            Toast.makeText(this, machine.getQrCode(), Toast.LENGTH_SHORT).show();
        });
        this.adapter.addRemoveClickListener(((view, machine, position) -> {
            this.presenter.onMachineRemoveClicked(machine);
        }));
    }

    @Override
    protected void preparingView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.setSmoothScrollbarEnabled(true);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.adapter);
    }

    //
    // MARK: - Override Function of IMachineListView
    //
    @Override
    public void bindMachinesToRecycler(@NonNull List<Machine> machines) {
        runOnUiThread(() -> {
            if (this.adapter.getItemCount() == 0) {
                this.adapter.addItems(machines);
            } else {
                this.adapter.clear();
                this.adapter.addItems(machines);
            }
        });
    }

    @Override
    public void onSuccessMachineRemove() {
        runOnUiThread(() -> {
            Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();
        });
    }

    //
    // MARK: - Override Function of View OnClickListener
    //
    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.fabFilter:
                    runOnUiThread(() -> {
                        new AlertDialog.Builder(this)
                                .setCancelable(true)
                                .setTitle("Sort Alphabetically")
                                .setItems(this.sorts, (dialogInterface, i) -> {
                                    this.presenter.onMachineSortClicked(this.sorts[i].toLowerCase());
                                })
                                .show();
                    });
                    break;
            }
        }
    }

    //
    // MARK: - Function of Static
    //
    public static Intent startIntent(@NonNull Context context) {
        return new Intent(context, MachineListActivity.class);
    }
}
