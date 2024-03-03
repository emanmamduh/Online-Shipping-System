package com.example.myproject;

public abstract class ProductInserterDecorator implements ProductInserter {
    private ProductInserter productInserter;

    public ProductInserterDecorator(ProductInserter productInserter) {
        this.productInserter = productInserter;
    }

    @Override
    public boolean insertProduct(String name, String description, byte[] image, String barcode, int catId, String price, int sale,String quantity) {
        return decorateInsert(name, description, image, barcode, catId, price,sale, quantity) &&
                productInserter.insertProduct(name, description, image, barcode, catId, price, sale, quantity);
    }

    protected abstract boolean decorateInsert(String name, String description, byte[] image, String barcode, int catId, String price,int sale, String quantity);
}
