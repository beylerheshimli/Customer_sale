package com.example.mz.customer_sale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toolbar;

import com.example.mz.customer_sale.Models.CustomerModel;
import com.example.mz.customer_sale.Services.Interfeysim;
import com.example.mz.customer_sale.Services.customerInterface;
import com.example.mz.customer_sale.Services.customersService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listcustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listcustomer = findViewById(R.id.listCustomer);

        customersService servis = new customersService(this);

        customerInterface.Responsum respons = new customerInterface.Responsum() {
            @Override
            public void netice(final ArrayList output) {


                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, output);
                listcustomer.setAdapter(adapter);

                listcustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        CustomerModel listModel = (CustomerModel) parent.getItemAtPosition(position);
                        Log.i("idsi",listModel.getId());

                        Intent intent = new Intent(MainActivity.this, esasMenu.class);
                        //  intent.putExtra("id",id);

                        startActivity(intent);


                    }
                });


            }
        };


        servis.users(respons);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

        }
        return true;
    }


}
