package com.example.mz.customer_sale.Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mz.customer_sale.Models.CustomerModel;
import com.example.mz.customer_sale.Models.staticUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by MZ on 28.12.2017.
 */

public class customersService {

    ArrayList<CustomerModel> list = new ArrayList<>();
    Context con;


    public customersService(Context con) {
        this.con = con;
    }



    public void users(final customerInterface.Responsum ff) {

        final ProgressDialog dialog = new ProgressDialog(con);
        dialog.setMessage("Gözləyin");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        String id= String.valueOf(staticUser.getId_());
        String url = "http://beylerheshimli-com.stackstaging.com/api/customers/"+id;

        RequestQueue queue = Volley.newRequestQueue(con);


        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("value");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                CustomerModel customerModel=new CustomerModel();
                              customerModel.setName(jsonObject.getString("name"));
                                customerModel.setId(jsonObject.getString("id"));
                                list.add(customerModel);

                            }

                            ff.netice(list);
                            dialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR: ", error.toString());
                    }
                }
        );
        queue.add(getRequest);
    }
}