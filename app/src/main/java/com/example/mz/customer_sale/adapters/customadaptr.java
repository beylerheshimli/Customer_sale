package com.example.mz.customer_sale.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.mz.customer_sale.Models.CustomerModel;

import java.util.List;

/**
 * Created by MZ on 04.01.2018.
 */

public class customadaptr extends ArrayAdapter{
    public customadaptr(@NonNull Context context, int resource, @NonNull List<CustomerModel> objects) {
        super(context, resource, objects);
    }
}
