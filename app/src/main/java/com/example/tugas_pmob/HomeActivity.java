package com.example.tugas_pmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    Button btnLogOut;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "login_pref";
    private static final String KEY_NAME = "nama";
    private static final String KEY_NIM = "nim";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogOut = findViewById(R.id.btnLogout);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                Toast.makeText(getApplicationContext(),"Sukses melakukan Logout", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}