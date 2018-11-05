package com.rajchandan.codingchallenge;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class getPage extends AppCompatActivity
{

    private ViewStub stubList;
    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private List<serverInfo> serverInfoList = new ArrayList<>();
    ArrayList<HashMap<String,String>> serverhash = new ArrayList<HashMap<String,String>>();
    private String TAG = getPage.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_page);

        stubList = (ViewStub)findViewById(R.id.stub_list);
        stubList.inflate();

        listView = (ListView)findViewById(R.id.my_listview);

        stubList.setVisibility(View.VISIBLE);

        listViewAdapter = new ListViewAdapter( this, R.layout.list_item,serverInfoList);
        listView.setAdapter(listViewAdapter);

        getData();
    }

    private void getData()
    {
        APIHandler hand = new APIHandler();

        //making a reguest to url and getting the data
        hand.makeAPICalls();
        while (APIHandler.isdoneconn != true);

        String jsonStr = APIHandler.response;
        Log.e(TAG, "Response from url: " + jsonStr);

        if(jsonStr != null)
        {
            try
            {
                JSONObject jsonObj = new JSONObject(jsonStr);

                JSONArray jsonArray = jsonObj.getJSONArray("data");

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject inside = jsonArray.getJSONObject(i);
                    String apiFirstName = inside.getString("first_name");
                    String apiLastName = inside.getString("last_name");
                    String apiAvatar = inside.getString("avatar");

                    serverInfoList.add(new serverInfo("First Name: " + apiFirstName, "last Name: " + apiLastName, apiAvatar));
                }

            }catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        }
        else
        {
            Log.e(TAG, "Couldn't get json from server.");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server.",
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
    }
}
