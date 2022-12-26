package com.example.tugas_pmob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas_pmob.database.UserDB;
import com.example.tugas_pmob.database.dao.UserDAO;
import com.example.tugas_pmob.database.entity.User;

public class RegisterActivity extends AppCompatActivity {

    EditText nimREG, namaREG, passREG;
    Button btnREG;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nimREG = findViewById(R.id.txNIM);
        namaREG = findViewById(R.id.txNama);
        passREG = findViewById(R.id.txPassword);
        btnREG = findViewById(R.id.btn_Regs);

        btnREG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();

                user.setNim(nimREG.getText().toString());
                user.setNama(namaREG.getText().toString());
                user.setPassword(passREG.getText().toString());

                if(validateInput(user)) {
                    UserDB userDB = UserDB.getUserDB(getApplicationContext());
                    UserDAO userDAO = userDB.userDAO();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // register
                            userDAO.regUser(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Register Successful!" ,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();

                }else{
                    Toast.makeText(getApplicationContext(), "Please Fill All Fields!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validateInput(User user) {
        if(user.getNama().isEmpty() || user.getNim().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }
}
