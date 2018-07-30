package com.SwipeUp.swipeUpMenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.SwipeUp.swipeUp.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SwipeUpActivity extends AppCompatActivity {
    private Spinner sizeSpinner;
    private Spinner colorSpinner;

    private ArrayList<String> sizeArray = new ArrayList<>(
            Arrays.asList("S", "M", "L", "XL"));
    private ArrayList<String> colorArray = new ArrayList<>(
            Arrays.asList("Rosa", "Blu", "Fosforescente"));

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
        setContentView(R.layout.swipe_up_menu);

        findElements();

        setUpSpinners();
    }

    public void findElements(){
        sizeSpinner = findViewById(R.id.taglia_spinner);
        colorSpinner = findViewById(R.id.color_spinner);
    }

    private void setUpSpinners(){
        ArrayAdapter<String> adpSize=new ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, sizeArray);
        sizeSpinner.setAdapter(adpSize);

        ArrayAdapter<String> adpColor=new ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item, colorArray);
        colorSpinner.setAdapter(adpColor);
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
    }
}