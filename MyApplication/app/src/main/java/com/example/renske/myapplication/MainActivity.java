package com.example.renske.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private Button generateButton, resetButton;
    private RadioButton hundredRadio, tenRadio;
    private EditText lowerBound, upperBound;
    private int userinputUBound, userinputLBound;
    private int finalUbound, finalLbound;
    private int max, min, finalnumber;
    private TextView result, result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initalize all the buttons etc
        generateButton = (Button) findViewById(R.id.generate_button);
        resetButton = (Button) findViewById(R.id.button2);
        hundredRadio = (RadioButton) findViewById(R.id.radioButton);
        tenRadio = (RadioButton) findViewById(R.id.radioButton2);
        lowerBound = (EditText) findViewById(R.id.editText);
        upperBound = (EditText) findViewById(R.id.editText2);
        result = (TextView) findViewById(R.id.textView4);
        result2 = (TextView) findViewById(R.id.textView2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onGenerateClick(View view) {
        // check whether values have been specified, either by the radio buttons or by a custom upper and lower bound
        if( (lowerBound.getText().toString().isEmpty() || upperBound.getText().toString().isEmpty()) && (!hundredRadio.isChecked() && !tenRadio.isChecked())) {
            Toast.makeText(this, "You did not enter a lower and upper bound", Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            // if the user specified bounds, generate random values between these
            if (!lowerBound.getText().toString().isEmpty() && !upperBound.getText().toString().isEmpty()) {

                userinputLBound = Integer.parseInt(lowerBound.getText().toString());
                userinputUBound = Integer.parseInt(upperBound.getText().toString());

                    if(userinputLBound > userinputUBound){
                        Toast.makeText(this, "Lower bound cannot be higher than upper bound.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                        finalnumber = userinputLBound + (int) (Math.random() * ((userinputUBound - userinputLBound) + 1));
                        // source: http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
                        result.setText(String.valueOf(finalnumber));
            }

            // if 1~100 range is checked
            if (hundredRadio.isChecked()) {
                finalnumber = 1 + (int) (Math.random() * ((100 - 1) + 1));
                result.setText(String.valueOf(finalnumber));
            }

            // if 1~10 range is checked
            if (tenRadio.isChecked()) {
                finalnumber = 1 + (int) (Math.random() * ((10 - 1) + 1));
                result.setText(String.valueOf(finalnumber));
            }
            result2.setText("This is your winning number!");
        }
    }

    public void onResetClick(View view) {
        // empty radio buttons?
        //i tried to set/clear thef ull group but it didnt work
        tenRadio.setChecked(false);
        hundredRadio.setChecked(false);
        lowerBound.setText("");
        upperBound.setText("");
        result2.setText("");
        result.setText("");
    }

    public void onTenClick(View view) {
        finalLbound = 1;
        finalUbound = 10;
    }

    public void onHundredClick(View view) {
        finalLbound = 1;
        finalUbound = 100;
    }

}
