package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity=0;
    int price;
    String priceMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void  submitOrder(View view){
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.notify_me_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        CheckBox ChocolateCheckbox = (CheckBox) findViewById(R.id.checkBox);
        boolean Chocolate = ChocolateCheckbox.isChecked();
        EditText nameOfCustomer =  findViewById(R.id.name_field);
        String name = nameOfCustomer.getText().toString();


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, (createOrderSummary( price, hasWhippedCream,Chocolate,name)));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+name);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private int calculatePrice(int price,boolean whippedCream,boolean Chocolate){
        price = quantity*5;



        if(whippedCream){

            price += 1*quantity;
        }


         if(Chocolate){

             price += 3*quantity;
        }

        return price;

    }
    public String createOrderSummary(int price,boolean addWhippedCream,boolean addChocolate,String addName ){
         price = calculatePrice(price,addWhippedCream,addChocolate);
         priceMessage = "Name: "+ addName;
         priceMessage += "\nAdd Whipped Cream?" + addWhippedCream;
         priceMessage += "\nAdd Chocolate toppings?" + addChocolate;
         priceMessage = priceMessage + "\nQuantity " + quantity;
         priceMessage = priceMessage + "\nPrice: Rs" + price;
         priceMessage = priceMessage + "\nThankyou";
         return priceMessage;


    }
    public void increment(View view){
        if(quantity == 100){
            Toast.makeText(this,"You cannot have more than 100 coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity= quantity+1;
        display(quantity);
    }
    public void decrement(View view){
        if(quantity == 1){
            Toast.makeText(this,"You cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        display(quantity);
    }
    private void display(int number){
        TextView quantityTextView= (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }

}
