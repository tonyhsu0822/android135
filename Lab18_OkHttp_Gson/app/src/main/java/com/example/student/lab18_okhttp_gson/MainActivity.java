package com.example.student.lab18_okhttp_gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadJson();
    }

    private void downloadJson() {

        String url = "https://drive.google.com/uc?export=download&id=1HQK6vH06RLtFRUWKI06c6LBasNZwDLXF";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                                .url(url)
                                .build();
        Response response = null;


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = null;
                try {
                    body = response.body();
                    if (!response.isSuccessful()) {
                        Log.d("failed reason", "" + response);
                        return;
                    }
                    String jsonString = body.string();
                    Log.d("JSON", jsonString);

                    EmployeeModel model = new Gson().fromJson(jsonString, EmployeeModel.class);
                    EmployeeModel.Employee[] employees = model.getEmployees();

                    Log.d("Employees", Arrays.toString(employees));

                } finally {
                    if (body != null) {
                        body.close();
                    }
                }
            }
        });

    }

}

