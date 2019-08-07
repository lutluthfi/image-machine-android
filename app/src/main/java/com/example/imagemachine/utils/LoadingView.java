package com.example.imagemachine.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.imagemachine.R;

import org.jetbrains.annotations.NotNull;

public class LoadingView {

    @NonNull
    private Dialog dialog;
    @NonNull
    private TextView textView;

    public LoadingView(@NotNull Context context) {
        this.dialog = new Dialog(context);
        this.dialog.setCancelable(true);
        this.dialog.setCanceledOnTouchOutside(false);
        if (this.dialog.getWindow() != null) {
            this.dialog.getWindow().setContentView(R.layout.view_loading);
            this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        this.textView = this.dialog.findViewById(R.id.text_util_loading_message);
    }

    public void addCancelListener(@NonNull DialogInterface.OnCancelListener listener) {
        this.dialog.setOnCancelListener(listener);
    }

    public void dismissLoadingView() {
        this.dialog.dismiss();
    }

    public Boolean isLoadingViewDidShow() {
        return this.dialog.isShowing();
    }

    public void showLoadingView(@Nullable String message) {
        if (message != null) {
            textView.setText(message);
        }
        this.dialog.show();
    }
}
