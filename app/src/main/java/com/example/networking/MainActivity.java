package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Mountain[] mountains;



    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            InputStream is = getApplicationContext().getAssets().open("mountains.json");
            String s = convertStreamToString(is);
            Log.d("MainActivity","The following text was found in textfile:\n\n"+s);

            Gson gson = new Gson();
            mountains = gson.fromJson(s,Mountain[].class);


            ArrayAdapter<Mountain> adapter = new ArrayAdapter<>(this,R.layout.list_item_textview,mountains);
            ListView first_listview=(ListView) findViewById(R.id.list_item);
            first_listview.setAdapter(adapter);
            first_listview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Berg:", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Log.e("MainActivity","Something went wrong when reading textfile:\n\n"+ e.getMessage());
        }
    }
}
