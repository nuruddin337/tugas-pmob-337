package com.example.tugas_pmob;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas_pmob.database.UserDB;
import com.example.tugas_pmob.database.dao.UserDAO;
import com.example.tugas_pmob.database.entity.User;

public class MainActivity extends AppCompatActivity {
    EditText nimLogin, passLogin;
    Button btnLogin, btnReg;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "login_pref";
    private static final String KEY_NAME = "nama";
    private static final String KEY_NIM = "nim";

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_login);

        nimLogin = findViewById(R.id.txNIM);
        passLogin = findViewById(R.id.txPassword);
        btnLogin = findViewById(R.id.btn_Login);
        btnReg = findViewById(R.id.btn_Regs);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String nama = sharedPreferences.getString(KEY_NAME, null);

        if(nama!=null){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nimUSER = nimLogin.getText().toString();
                String passUSER = passLogin.getText().toString();

                if(nimUSER.isEmpty()||passUSER.isEmpty()){
                    Toast.makeText(getApplicationContext(), "ISI DENGAN BENAR!!", Toast.LENGTH_SHORT).show();
                }else{
                    UserDB userDB = UserDB.getUserDB(getApplicationContext());
                    UserDAO userDAO = userDB.userDAO();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User user = userDAO.login(nimUSER, passUSER);
                            if(user == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),"INVALID LOGIN",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
