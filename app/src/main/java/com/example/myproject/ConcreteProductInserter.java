package com.example.myproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class ConcreteProductInserter implements ProductInserter {
    private DatabaseHelper databaseHelper;

    public ConcreteProductInserter(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public boolean insertProduct(String name, String description, byte[] image, String barcode, int catId, String price,int sale, String quantity) {
        SQLiteDatabase myDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("image", image);
        contentValues.put("barcode", barcode);
        contentValues.put("category", catId);
        contentValues.put("price", price);
        contentValues.put("sale", sale);
        contentValues.put("quantity", quantity);

        long result = myDatabase.insert("product", null, contentValues);
        return result != -1;
    }
}