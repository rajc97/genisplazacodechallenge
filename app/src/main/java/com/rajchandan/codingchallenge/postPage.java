package com.rajchandan.codingchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class postPage extends AppCompatActivity
{

    private EditText firstName;
    private EditText lastName;
    private EditText avatar;
    private Button createAccount;
    private String TAG = getPage.class.getSimpleName();
    ArrayList<HashMap<String, String>> sendInfo = new ArrayList<HashMap<String, String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_page);

        firstName = (EditText)findViewById(R.id.createfirstName);
        lastName = (EditText)findViewById(R.id.createlastName);
        avatar = (EditText)findViewById(R.id.createAvatar);
        createAccount = (Button)findViewById(R.id.createAccountbtn);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData();
            }
        });
    }

    private void postData()
    {
        APIHandler hand = new APIHandler();

        while (APIHandler.isdoneconn != true);

        final String first = firstName.getText().toString();
        final String last = lastName.getText().toString();
        final String ava = avatar.getText().toString();

        HashMap<String, String> data = new HashMap<>();
        data.put("first_name", first);
        data.put("last_namr", last);
        data.put("avatar", ava);

        sendInfo.add(data);

        hand.pushAPICalls(sendInfo);

        startActivity(new Intent(postPage.this,MainActivity.class));

    }

}
