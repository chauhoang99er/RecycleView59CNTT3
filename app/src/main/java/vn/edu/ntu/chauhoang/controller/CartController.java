package vn.edu.ntu.chauhoang.controller;

import android.app.Application;

import java.util.ArrayList;

import vn.edu.ntu.chauhoang.model.Product;

public class CartController extends Application implements ICartController {
    ArrayList<Product> listProduct = new ArrayList<>();
    ArrayList<Product> shoppingCart = new ArrayList<>();
    public CartController()
    {
        listProduct.add(new Product("Xoai lac chua cay",60000,"Xoài Nha Trang bao ngon"));
        listProduct.add(new Product("Khoai lang",20000,"Khoai lang mật"));
        listProduct.add(new Product("Me rim",40000,"Me rim Thailand"));
        listProduct.add(new Product("Chuoi vang chin cay",35000,"Chuối mọc từ cây"));
        listProduct.add(new Product("Coc ngam",60000,"Cóc ngâm bao cay"));
        listProduct.add(new Product("Dua hau ruot do",60000,"Dưa hấu ít hạt"));
        listProduct.add(new Product("Banh trang tron",10000,"Bánh tráng trộn bò khô"));

    }
    @Override
    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

    @Override
    public ArrayList<Product> getShoppingCart() { return shoppingCart;}

    @Override
    public boolean addToShoppingCart(Product p)
    {
        if(!shoppingCart.contains(p))
        {
            shoppingCart.add(p);
            return true;
        }
        return false;
    }

    @Override
    public void clearShoppingCart()
    {
        shoppingCart.clear();
    }
}
