package com.example.myproject;

public class AddToCartCommand implements ShoppingCartCommand{
    private ShoppingCartItem shoppingCartItem;
    private DatabaseHelper databaseHelper;

    public AddToCartCommand(ShoppingCartItem shoppingCartItem, DatabaseHelper databaseHelper) {
        this.shoppingCartItem = shoppingCartItem;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void execute() {
        databaseHelper.insertShoppingCartItem(shoppingCartItem);
    }
}
