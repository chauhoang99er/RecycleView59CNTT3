package vn.edu.ntu.chauhoang.controller;

import java.util.ArrayList;

import vn.edu.ntu.chauhoang.model.Product;

public interface ICartController
{
    public ArrayList<Product> getListProduct();
    public boolean addToShoppingCart(Product p);
    public ArrayList<Product> getShoppingCart();
    public void clearShoppingCart();
}
