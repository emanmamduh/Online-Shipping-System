package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class signupActivity extends AppCompatActivity {

    EditText signupEmail, signupPassword,signupusernaem,signupphone, signupConfirm,birthday;
    EditText signupcard, signupcvc,signupyear;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseHelper = new DatabaseHelper(this);

        birthday=findViewById(R.id.signup_date);
        signupEmail = findViewById(R.id.signup_email);
        signupusernaem = findViewById(R.id.username);
        signupphone = findViewById(R.id.phone);
        signupPassword = findViewById(R.id.signup_password);
        signupConfirm = findViewById(R.id.signup_confirm);
        signupcard = findViewById(R.id.card);
        signupcvc = findViewById(R.id.cvc);
        signupyear = findViewById(R.id.year);
        Calendar calendar1=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar1.set(Calendar.YEAR,year);
                calendar1.set(Calendar.MONTH,month);
                calendar1.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updatecalendar();
            }
            private void updatecalendar(){
                String formate="MM/dd/yy";
                SimpleDateFormat sdf=new SimpleDateFormat(formate, Locale.US);
                birthday.setText(sdf.format(calendar1.getTime()));
            }
        };
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(signupActivity.this,date,calendar1.get(Calendar.YEAR),calendar1.get(Calendar.MONTH),
                        calendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        findViewById(R.id.signup_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = signupEmail.getText().toString();
                final String password = signupPassword.getText().toString();
                final String username = signupusernaem.getText().toString();
                final String phone = signupphone.getText().toString();
                final String card = signupcard.getText().toString();
                final String cvc = signupcvc.getText().toString();
                final String year = signupyear.getText().toString();
                final String confirmPassword = signupConfirm.getText().toString();
                final String birthdate = birthday.getText().toString();

                if (email.equals("") || password.equals("") || confirmPassword.equals(""))
                    Toast.makeText(signupActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(confirmPassword)) {
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);
                        if (!checkUserEmail) {
                            Boolean insert = databaseHelper.insertData(email, password,username, phone,card,cvc,year, birthdate);
                            if (insert) {
                                Toast.makeText(signupActivity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(signupActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(signupActivity.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(signupActivity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        findViewById(R.id.loginRedirectText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signupActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
    }
}