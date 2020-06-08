package com.intellimedia.practical.auth.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.intellimedia.practical.R;

import java.util.List;

public class CustomSpinnerAdapter extends BaseAdapter {
    private final LayoutInflater inflter;
    private String[] items;

    public CustomSpinnerAdapter(Context applicationContext, String[] items) {
        this.items = items;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.row_item_spinner, null);
        TextView txtDays = view.findViewById(R.id.txtDays);
        txtDays.setText(items[i]);
        return view;
    }
}

