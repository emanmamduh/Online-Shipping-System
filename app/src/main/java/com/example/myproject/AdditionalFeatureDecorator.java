package com.example.myproject;

public class AdditionalFeatureDecorator extends ProductInserterDecorator {
    public AdditionalFeatureDecorator(ProductInserter productInserter) {
        super(productInserter);
    }

    @Override
    protected boolean decorateInsert(String name, String description, byte[] image, String barcode, int catId, String price, int sale, String quantity) {
        return true;
    }
}