package com.example.shamim.weaponzeroing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    TextView textViewId, textViewUsername;

    //a list to store all the products
    List<Firer> productList;

    //the recyclerview
    RecyclerView recyclerView1;

    Button profile, sosn, new_image;

    static int ba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //getting the recyclerview from xml
        recyclerView1 = findViewById(R.id.recylcerView1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();



        //this method will fetch and parse json
        //to display it in recyclerview


        //if the user is not logged in
        //starting the login activity
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }


        textViewId = (TextView) findViewById(R.id.textViewId);
        textViewUsername = (TextView) findViewById(R.id.textViewUsername);

        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews
        textViewId.setText(String.valueOf(user.getId()));
        textViewUsername.setText(user.getUsername());
        ba = Integer.parseInt(String.valueOf(user.getId()));



        /*//when the user presses logout button
        //calling the logout method
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                Toast.makeText(getApplicationContext(), "Logged Out",Toast.LENGTH_LONG).show();

            }
        });*/


        profile = (Button) findViewById(R.id.profile);
        sosn = (Button) findViewById(R.id.sosn);
        new_image = (Button) findViewById(R.id.new_image);

        new_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddNewRecord.class));
            }
        });

        sosn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SosnDetail.class));
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PrevRecord.class));
            }
        });

        loadProducts1();

    }

    private void loadProducts1() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, URLs.URL_FIRER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Firer(
                                        product.getInt("ID"),
                                        product.getString("Name"),
                                        product.getString("DOB"),
                                        product.getString("Cell"),
                                        product.getString("NID"),
                                        product.getString("Religion"),
                                        product.getString("Village"),
                                        product.getString("PO"),
                                        product.getString("PS"),
                                        product.getString("Dist"),
                                        product.getString("Gender"),
                                        product.getString("Unit"),
                                        product.getString("Company")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            FirerAdapter adapter = new FirerAdapter(Profile.this, productList);
                            recyclerView1.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.credit:
                Toast.makeText(this, "Credit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.signout:
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_LONG).show();
                break;
        }

        return true;
    }
}