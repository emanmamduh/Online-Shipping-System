package com.example.myproject;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class updatepersonalinfoFragment extends Fragment{
    private EditText signupEmail, signupusernaem, signupphone, birthday;
    private DatabaseHelper databaseHelper;

    private Button update_button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_updatepersonalinfo, container, false);

        databaseHelper = new DatabaseHelper(requireContext());

        birthday = view.findViewById(R.id.signup_date);
        signupEmail = view.findViewById(R.id.signup_email);
        signupusernaem = view.findViewById(R.id.username);
        signupphone = view.findViewById(R.id.phone);
        update_button=view.findViewById(R.id.update_button);
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        int userId = preferences.getInt("user_id", -1);
        User user = databaseHelper.getUserById(userId);
        if (user != null) {
            signupEmail.setText(user.getEmail());
            signupusernaem.setText(user.getUsername());
            signupphone.setText(String.valueOf(user.getPhone()));

        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedEmail = signupEmail.getText().toString();
                String updatedUsername = signupusernaem.getText().toString();
                String updatedPhone = signupphone.getText().toString();
                updateinfo updateInfo = new updateinfo(requireContext(), databaseHelper);
                updateInfo.updateData(userId, updatedEmail, updatedUsername, updatedPhone);
            }
        });
        Calendar calendar1 = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatecalendar();
            }

            private void updatecalendar() {
                String format = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                birthday.setText(sdf.format(calendar1.getTime()));
            }
        };

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(requireContext(), date, calendar1.get(Calendar.YEAR),
                        calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return view;
    }

}