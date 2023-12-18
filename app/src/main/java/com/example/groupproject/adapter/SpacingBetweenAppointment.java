package com.example.groupproject.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacingBetweenAppointment extends RecyclerView.ItemDecoration {
    private final int space;

    public SpacingBetweenAppointment(Context context, int space) {
        this.space = context.getResources().getDimensionPixelSize(space);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = space;
    }
}
