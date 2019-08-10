package com.example.imagemachine.feature.machine.list.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagemachine.R;
import com.example.imagemachine.data.RoomModule;
import com.example.imagemachine.data.model.LocalMachineDataSource;
import com.example.imagemachine.data.model.Machine;
import com.example.imagemachine.feature.adapter.MachineAdapter;
import com.example.imagemachine.feature.base.view.BaseActivity;
import com.example.imagemachine.feature.machine.list.presenter.IMachineListPresenter;
import com.example.imagemachine.feature.machine.list.presenter.MachineListPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MachineListActivity extends BaseActivity implements
        IMachineListView, View.OnClickListener {

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
        this.floatingActionButton.setOnClickListener(this);
        this.adapter.addItemClickListener((view, machine, position) -> {
            Toast.makeText(this, machine.getQrCode(), Toast.LENGTH_SHORT).show();
        });
        this.adapter.addRemoveClickListener(((view, machine, position) -> {
            Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();
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
        this.adapter.addItems(machines);
    }

    //
    // MARK: - Override Function of View OnClickListener
    //
    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.fabFilter:
                    Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();
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
