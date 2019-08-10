package com.example.imagemachine.feature.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagemachine.R;
import com.example.imagemachine.data.model.Machine;

import java.util.ArrayList;
import java.util.List;

public class MachineAdapter extends RecyclerView.Adapter<MachineAdapter.ViewHolder> {

    @NonNull
    private List<Machine> machines;
    private OnItemClickListener itemClickListener;
    private OnRemoveClickListener removeClickListener;

    public MachineAdapter() {
        this.machines = new ArrayList<>();
    }

    public void addItemClickListener(@NonNull OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void addRemoveClickListener(@NonNull OnRemoveClickListener removeClickListener) {
        this.removeClickListener = removeClickListener;
    }

    public void addItems(List<Machine> machines) {
        this.machines.addAll(machines);
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.machines.clear();
        this.notifyDataSetChanged();
    }

    public void removeItem(Machine machine) {
        this.machines.remove(machine);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_machine, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(this.machines.get(position));
        if (this.itemClickListener != null) {
            holder.machineCardView.setOnClickListener(view -> {
                this.itemClickListener.onItemClickListener(view, this.machines.get(position), position);
            });
        }

        if (this.removeClickListener != null) {
            holder.removeImageView.setOnClickListener(view -> {
                this.removeClickListener.onRemoveClickListener(view, this.machines.get(position), position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.machines.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CardView machineCardView;
        private TextView nameTextView;
        private TextView typeTextView;
        private ImageView removeImageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.machineCardView = itemView.findViewById(R.id.cardViewMachine);
            this.nameTextView = itemView.findViewById(R.id.textViewMachineName);
            this.typeTextView = itemView.findViewById(R.id.textViewMachineType);
            this.removeImageView = itemView.findViewById(R.id.imageViewRemove);
        }

        void onBind(Machine machine) {
            this.nameTextView.setText(machine.getName());
            this.typeTextView.setText(machine.getType());
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(@NonNull View view, @NonNull Machine machine, int position);
    }

    public interface OnRemoveClickListener {
        void onRemoveClickListener(@NonNull View view, @NonNull Machine machine, int position);
    }
}
