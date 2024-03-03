package com.example.myproject;

import android.content.Context;
import android.widget.Toast;

public class updateinfo implements UpdateObserver{

    private DatabaseHelper databaseHelper;

    private Context context;

    public updateinfo(Context context, DatabaseHelper databaseHelper) {
        this.context = context;
        this.databaseHelper = databaseHelper;
    }

    public void updateData(int userId, String updatedEmail, String updatedUsername, String updatedPhone) {
        boolean success = databaseHelper.updateData(userId, updatedEmail, updatedUsername, updatedPhone);
        notifyDataUpdated(success);
    }

    private void notifyDataUpdated(boolean success) {
        onDataUpdated(success);
        databaseHelper.removeObserver(this);
    }

    @Override
    public void onDataUpdated(boolean success) {
        if (success) {
            Toast.makeText(context, "Data updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to update data", Toast.LENGTH_SHORT).show();
        }
    }
}
