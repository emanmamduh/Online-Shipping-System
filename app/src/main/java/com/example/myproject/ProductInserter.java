package com.example.myproject;

public interface ProductInserter {

    boolean insertProduct(String name, String description, byte[] image, String barcode, int catId, String price,int sale, String quantity);

}
