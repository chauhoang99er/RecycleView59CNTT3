package vn.edu.ntu.chauhoang.recycleview59cntt3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.ntu.chauhoang.controller.ICartController;
import vn.edu.ntu.chauhoang.model.Product;

public class ShoppingCartActivity extends AppCompatActivity {
    TextView txtCartInfo;
    Button btnSubmit, btnClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addView();
        addEvents();
    }

    private void addView()
    {
        txtCartInfo = findViewById(R.id.txtCartInfor);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnClear = findViewById(R.id.btnClear);
        viewCartInfor();
    }

    private void addEvents()
    {
        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ICartController controller = (ICartController) getApplication();
                controller.clearShoppingCart();
                Toast.makeText(ShoppingCartActivity.this,
                        "Giỏ hàng đã được xóa", Toast.LENGTH_SHORT).show();
                txtCartInfo.setText("Không có mặt hàng này trong giỏ hàng");
//                if (controller.getShoppingCart().size() > 0)
//                {
//                    confirm();
//                } else
//                    Toast.makeText(ShoppingCartActivity.this,
//                            "Không có mặt hàng này trong giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void confirm()
    {
        Intent intent = new Intent(this, ConfirmActivity.class);
        startActivity(intent);
    }

    private void viewCartInfor()
    {
        ICartController controller = (ICartController) ShoppingCartActivity.this.getApplication();
        ArrayList<Product> listProducts = controller.getShoppingCart();
        StringBuilder builder = new StringBuilder();
        for(Product p: listProducts)
        {
            builder.append(p.getName() + "\t\t\t" + p.getPrice()+ " vnd\n");
        }
        if(builder.toString().length()>0)
            txtCartInfo.setText(builder.toString());
        else
            txtCartInfo.setText("Không có mặt hàng nào trong giỏ hàng");
    }
}
