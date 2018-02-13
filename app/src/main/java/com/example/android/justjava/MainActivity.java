
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int price = 0;
    boolean whippedcream = false;
    boolean chocolate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(getString(R.string.totalPrice, price));
    }


    public void submitOrder(View view) {
        String priceMessage = createOrderSummary();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"gueneznassim@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        emailIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        emailIntent.setType("message/rfc822");
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }

    }


   public void increment(View view) {
        quantity = quantity + 1;
        calculatePrice();
    }


    public void decrement(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
            calculatePrice();
        }
    }

    private void calculatePrice() {
        CheckBox addWhippedCream = (CheckBox) findViewById(R.id.whiped_cream);
        CheckBox addChocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        price = 5;
        if (addWhippedCream.isChecked()) {
            whippedcream = true;
            price += 1;
        }
        if (addChocolate.isChecked()) {
            chocolate = true;
            price += 2;
        }
        price = quantity * price;
        displayQuantity();
        displayPrice();
    }

    private String createOrderSummary() {
        EditText name = findViewById(R.id.edit_text_name);
        String orderSummary = getString(R.string.order_summary_name, name.getText().toString()) + "\n";
        if (whippedcream){
            orderSummary += getString(R.string.addWhippedCream, getString(R.string.yes)) + "\n";
        }else {
            orderSummary += getString(R.string.addWhippedCream, getString(R.string.no)) + "\n";
        }

        if (chocolate){
            orderSummary += getString(R.string.addChocolate, getString(R.string.yes)) + "\n";
        }else {
            orderSummary += getString(R.string.addChocolate, getString(R.string.no)) + "\n";
        }
        orderSummary += getString(R.string.coffeeQuantity, quantity) + "\n";
        orderSummary += getString(R.string.totalPrice, price) + "\n";
        orderSummary += getString(R.string.thank_you);
        return orderSummary;
    }



    private void displayPrice() {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(getString(R.string.totalPrice,  price));
    }

    private void displayQuantity() {
        String quantityString =  Integer.toString(quantity);
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(quantityString);
    }

}