package com.example.mz.customer_sale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.mz.customer_sale.Models.staticUser;
import com.example.mz.customer_sale.Services.Interfeysim;
import com.example.mz.customer_sale.Services.Login;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public static EditText istifadeci, parol;
    public Button btnok;
    String uname, pass;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        istifadeci = findViewById(R.id.username);
        parol = findViewById(R.id.password);
        btnok = findViewById(R.id.ok);
        loginvarmi();
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                hideKeyboard(LoginActivity.this);
                uname = istifadeci.getText().toString();
                pass = parol.getText().toString();

                if (uname.length() > 0 && pass.length() > 0) {


                    Login habx = new Login(LoginActivity.this);

                    Interfeysim.AsyncResponse in = new Interfeysim.AsyncResponse() {
                        @Override
                        public void processFinish(Boolean output, String msg) {

                            if (output) {

//                            try {
//                                Boolean a = output.getBoolean("opr");
//                                String msg = output.getString("msg");
//                                if (a) {
//                                    JSONObject value = output.getJSONObject("value");
//                                    String username = value.getString("username");
//                                    String name = value.getString("name");
//                                    String token=value.getString("token");
//                                    int id=value.getInt("id");
//
//                                    staticUser.setId_(id);
//                                    staticUser.setName_(name);
//                                    staticUser.setUsername(username);
//                                    staticUser.setToken(token);

                                String ad = staticUser.getUsername();


                                SharedPreferences pref = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("name", ad);
                                editor.apply();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                            }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
                        }


                    };
                    habx.login(uname, pass, in);
                } else {
                    Snackbar.make(v, "bos olmazki", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void loginvarmi() {

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String mis = prefs.getString("name", null);
        if (mis != null) {
            istifadeci.setText(mis);
            parol.requestFocus();

        }
    }
}


//    class useriyoxla extends AsyncTask<String, Void, Boolean> {
//        public Interfeysim.AsyncResponse delegate = null;
//
//        URL url;
//        HttpURLConnection connection;
//        StringBuffer bufer;
//        String tamurl, line = "";
//        InputStream stream;
//        BufferedReader reader;
//        String gelenjson;
//        Boolean a;
//
//        @Override
//
//        protected Boolean doInBackground(String... strings) {
//            try {
//
//                tamurl = "http://beylerheshimli-com.stackstaging.com/api/login/" + istifadeci.getText().toString() + "/" + parol.getText().toString();
//                url = new URL(tamurl);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//                stream = connection.getInputStream();
//                reader = new BufferedReader(new InputStreamReader(stream));
//                bufer = new StringBuffer();
//                while ((line = reader.readLine()) != null) {
//                    bufer.append(line);
//                }
//                gelenjson = bufer.toString();
//                JSONObject obje = new JSONObject(gelenjson);
//                a = obje.getBoolean("opr");
//
//                return a;
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            } finally {
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (connection != null) {
//                    connection.disconnect();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            delegate.processFinish(aBoolean);
//        }
//    }
//}