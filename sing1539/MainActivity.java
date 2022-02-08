package com.cst2335.sing1539;

import static com.cst2335.sing1539.R.id.togglebutton;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    //LinearLayout mainland;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);
    }
    public void display_Toast(View v){

        Toast.makeText(MainActivity.this,getString(R.string.TOAST_MESSAGE)  , Toast.LENGTH_SHORT).show();
    }



    public void display_snack (View v) {

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch toggle = (Switch) findViewById(togglebutton);

        toggle.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            Snackbar snack;
            if (isChecked) {
                snack = Snackbar.make(toggle, "The switch is now followed by On", Snackbar.LENGTH_LONG);

            }
            else {
                snack = Snackbar.make(toggle, "The switch is now followed by off", Snackbar.LENGTH_LONG);

            }
            snack.setAction("Undo", click -> compoundButton.setChecked(false));
            snack.show();

        })
        ;}}