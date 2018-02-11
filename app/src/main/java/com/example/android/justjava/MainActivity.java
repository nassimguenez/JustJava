
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int price = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void submitOrder() {
        String priceMessage = createOrderSummary();
        displayMessage(priceMessage);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

    }


    public void increment() {
        quantity = quantity + 1;
        calculatePrice();
        displayQuantity();
        displayPrice();
    }


    public void decrement() {
        if (quantity > 0) {
            quantity = quantity - 1;
            calculatePrice();
        }
        displayQuantity();
        displayPrice();
    }

    private void calculatePrice() {
        CheckBox addWhippedCream = (CheckBox) findViewById(R.id.whiped_cream);
        CheckBox addChocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if (addWhippedCream.isChecked()) {
            price += 1;
        }
        if (addChocolate.isChecked()) {
            price += 2;
        }
        price = quantity * 5;
    }

    private String createOrderSummary() {
        EditText name = (EditText) findViewById(R.id.edittext_name);
        String orderSummary = "Name: " + name.getText().toString()
                + "\nAdd Whipped Cream: " + addWhippedCream.isChecked()
                + "\nAdd Chocolate: " + addChocolate.isChecked()
                + "\nQuantity: "
                + quantity + "\nTotal: $"
                + price
                + "\nThank you!";
        return orderSummary;
    }


    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


    private void displayPrice() {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText("PRICE: $" + quantity);
    }

    private void displayQuantity() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + price);
    }

}