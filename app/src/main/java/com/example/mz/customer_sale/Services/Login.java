package com.example.mz.customer_sale.Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mz.customer_sale.LoginActivity;
import com.example.mz.customer_sale.Models.staticUser;


import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by MZ on 21.12.2017.
 */

public class Login {
    Context con;

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public Login(Context con) {
        this.con = con;
    }


    public void login(String username, String pass, final Interfeysim.AsyncResponse ff) {

        final ProgressDialog dialog=new ProgressDialog(con);
        dialog.setMessage("Gözləyin");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        dialog.show();
        String url = "http://beylerheshimli-com.stackstaging.com/api/login/" + username + "/" + pass;
        RequestQueue queue = Volley.newRequestQueue(con);


        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Boolean a = response.getBoolean("opr");
                            String msg = response.getString("msg");

                            if (a) {
                                JSONObject value = response.getJSONObject("value");
                                String username = value.getString("username");
                                String name = value.getString("name");
                                String token = value.getString("token");
                                int id = value.getInt("id");

                                staticUser.setId_(id);
                                staticUser.setName_(name);
                                staticUser.setUsername(username);
                                staticUser.setToken(token);




                                ff.processFinish(a,msg);
                                dialog.dismiss();
                            }
                            else {

                                Toast.makeText(con,"bele istifadeci yoxdu",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }

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
