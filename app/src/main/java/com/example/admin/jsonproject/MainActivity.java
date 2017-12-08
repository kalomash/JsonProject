package com.example.admin.jsonproject;

import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin,btnSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void veiwAll(View view)
    {
        Resources res =getResources();
        InputStream is =res.openRawResource(R.raw.student);

        Scanner scanner = new Scanner(is);

        StringBuilder builder = new StringBuilder();

        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }
        parseJson(builder.toString());
    }

    private void parseJson(String s)
    {
        TextView tvDisplay=(TextView)findViewById(R.id.tvDisplay);
        StringBuilder builder = new StringBuilder();
        try {
            JSONObject root = new JSONObject(s);
            JSONObject student = root.getJSONObject("student-grades");
            builder.append("Name:  ").append(student.getString("name")).append("\n");
            builder.append("full-time:  ").append(student.getString("full-time")).append("\n");

            JSONArray courses =student.getJSONArray("courses");
            for(int i=0;i<courses.length();i++)
            {
                JSONObject course = courses.getJSONObject(i);
                builder.append(course.getString("name"))
                        .append(": ")
                        .append(course.getString("grade"))
                        .append("\n");
            }

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        tvDisplay.setText(builder.toString());
    }
}