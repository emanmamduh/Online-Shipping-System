package com.example.myproject;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;


public class userlistFragment extends Fragment {

    public userlistFragment() {
    }

    ValidationStrategy cardValidationStrategy = new CardValidationStrategy();
    ValidationStrategy cvcValidationStrategy = new CVCValidationStrategy();
    ValidationStrategy yearValidationStrategy = new YearValidationStrategy();
    CardValidatorContext cardValidatorContext = new CardValidatorContext();
    CardValidatorContext cvcValidatorContext = new CardValidatorContext();
    CardValidatorContext yearValidatorContext = new CardValidatorContext();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userlist, container, false);

        DatabaseHelper databaseHelper = new DatabaseHelper(requireContext());
        List<User> users = databaseHelper.getUsers();
        TableLayout tableLayout = view.findViewById(R.id.Usertable);

        for (User user : users) {
            int numberOfOrders = databaseHelper.getAllUserOrders(user.getId()).size();
            TableRow tableRow = new TableRow(requireContext());
            TableRow.LayoutParams rowParams = new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            tableRow.setLayoutParams(rowParams);

            TextView usernameTextView = new TextView(requireContext());
            TableRow.LayoutParams textParams = new TableRow.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1.0f
            );
            usernameTextView.setLayoutParams(textParams);
            usernameTextView.setText(user.getUsername());
            usernameTextView.setTextSize(18);
            usernameTextView.setTextColor(Color.BLACK);

            cardValidatorContext.setValidationStrategy(cardValidationStrategy);
            boolean isCardValid = cardValidatorContext.validate(user.getcard());
            cvcValidatorContext.setValidationStrategy(cvcValidationStrategy);
            boolean isCVCValid = cvcValidatorContext.validate(user.getcvc());
            yearValidatorContext.setValidationStrategy(yearValidationStrategy);
            boolean isYearValid = yearValidatorContext.validate(user.getyear());

            // Display validation result
            TextView validationResultsTextView = new TextView(requireContext());
            validationResultsTextView.setLayoutParams(textParams);
            validationResultsTextView.setText("Card: " + (isCardValid ? "Valid" : "Not Valid") +
                    "\nCVC: " + (isCVCValid ? "Valid" : "Not Valid") +
                    "\nYear: " + (isYearValid ? "Valid" : "Not Valid"));
            validationResultsTextView.setTextSize(18);
            validationResultsTextView.setTextColor(isCardValid && isCVCValid && isYearValid ? Color.GREEN : Color.RED);

            TextView ordersCountTextView = new TextView(requireContext());
            ordersCountTextView.setLayoutParams(textParams);
            String ordersCountText = String.valueOf(numberOfOrders);
            ordersCountTextView.setText(ordersCountText);
            ordersCountTextView.setTextSize(18);
            ordersCountTextView.setTextColor(Color.BLACK);

            LinearLayout buttonLayout1 = new LinearLayout(requireContext());
            buttonLayout1.setLayoutParams(new TableRow.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1.0f
            ));
            buttonLayout1.setOrientation(LinearLayout.HORIZONTAL);

            Button deleteButton = new Button(requireContext());
            LinearLayout.LayoutParams deleteButtonParams = new LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1.0f
            );
            deleteButton.setLayoutParams(deleteButtonParams);
            deleteButton.setText("Delete");
            GradientDrawable deleteDrawable = new GradientDrawable();
            deleteDrawable.setColor(Color.RED);
            deleteDrawable.setCornerRadius(12);
            deleteButton.setBackground(deleteDrawable);
            deleteButton.setBackgroundColor(Color.RED);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int userIdToDelete = user.getId();
                    boolean isDeleted = databaseHelper.deleteuserById(userIdToDelete);
                    if (isDeleted) {
                        tableLayout.removeView(tableRow);
                    } else {
                        // Handle deletion failure
                    }
                }
            });

            buttonLayout1.addView(deleteButton);

            tableRow.addView(usernameTextView);
            tableRow.addView(validationResultsTextView);
            tableRow.addView(ordersCountTextView);
            tableRow.addView(buttonLayout1);

            tableLayout.addView(tableRow);
        }

        return view;
    }
}