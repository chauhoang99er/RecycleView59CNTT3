package vn.edu.ntu.chauhoang.recycleview59cntt3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.ntu.chauhoang.controller.ICartController;
import vn.edu.ntu.chauhoang.model.Product;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Product> listProduct;
    ProductAdapter adapter;
    RecyclerView rvListProduct;
    ICartController controller;
    ImageView imvCart;
    TextView txtQuantity;
    //ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
        if (txtQuantity != null)
            txtQuantity.setText(controller.getCartQuantity());
    }

    private void addView() {
        rvListProduct = findViewById(R.id.rvListProduct);
        rvListProduct.setLayoutManager(new LinearLayoutManager(this)); //this là mainactivity
        controller = (ICartController) getApplication();
        listProduct = controller.getListProduct();
        adapter = new ProductAdapter(listProduct);
        rvListProduct.setAdapter(adapter);
    }

    //Tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu_cart, menu);

        MenuItem cartMenu = menu.findItem(R.id.mnu_cart);
        txtQuantity = cartMenu.getActionView().findViewById(R.id.txtQuantity);
        imvCart = cartMenu.getActionView().findViewById(R.id.imvCart);

        txtQuantity.setText(controller.getCartQuantity());
        imvCart.setOnClickListener(this);
        txtQuantity.setOnClickListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    //Chọn ra item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
//            case R.id.mnu_cart:
//                callShoppingCartActivity();
//                break;
            case R.id.mnu_close:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void callShoppingCartActivity() {
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        callShoppingCartActivity();
    }

    //Lớp cào đặt cho việc hiển thị 1 Product
    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtPrice, txtDesc;
        ImageView imBtnCart;
        Product p;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtName);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDesc = this.itemView.findViewById(R.id.txtDesc);
            imBtnCart = this.itemView.findViewById(R.id.imBtnCart);
            //Sự kiện nhấn vào giỏ hàng
            imBtnCart.setOnClickListener(this);
        }

        public void bind(Product p) {
            this.p = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());
            //Gán hình
            imBtnCart.setImageResource(R.drawable.ic_action_add_to_cart);
        }

        @Override
        public void onClick(View v) {
            ICartController controller = (ICartController) getApplication();
            if (!controller.addToShoppingCart(p)) {
                Toast.makeText(MainActivity.this,
                        "SP " + p.getName() + " đã có trong giỏ hàng",
                        Toast.LENGTH_SHORT).show();

            } else {
                txtQuantity.setText(controller.getCartQuantity());
                Toast.makeText(MainActivity.this,
                        "Đã thêm sp " + p.getName() + " vào giỏ hàng",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

    // Lớp adapter kết nối RecycleView và dữ liệu
    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
        ArrayList<Product> listProduct;

        public ProductAdapter(ArrayList<Product> listProduct) {
            this.listProduct = listProduct;
        }

        @NonNull
        @Override
        //tạo ra view holder để hiển thị dữ liệu
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            //Chuyển layout đã thiết kế bằng xml thành một đối tượng View
            View view = layoutInflater.inflate(R.layout.product_item,
                    parent, false);
            return new ProductViewHolder(view);
        }

        //Kêt nối 1 mục dữ liệu trong danh sách với một ViewHolder
        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(listProduct.get(position));
        }

        @Override
        public int getItemCount() {
            return listProduct.size();
        }
    }
}
