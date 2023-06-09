package com.example.trunic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(MainActivity.this);

        ToggleButton e1 = (ToggleButton) findViewById(R.id.edge1);
        ToggleButton e2 = (ToggleButton) findViewById(R.id.edge2);
        ToggleButton e3 = (ToggleButton) findViewById(R.id.edge3);
        ToggleButton e4 = (ToggleButton) findViewById(R.id.edge4);
        ToggleButton e5 = (ToggleButton) findViewById(R.id.edge5);
        ToggleButton e6 = (ToggleButton) findViewById(R.id.edge6);
        ToggleButton e7 = (ToggleButton) findViewById(R.id.edge7);
        ToggleButton e8 = (ToggleButton) findViewById(R.id.edge8);
        ToggleButton e9 = (ToggleButton) findViewById(R.id.edge9);
        ToggleButton e10 = (ToggleButton) findViewById(R.id.edge10);
        ToggleButton e11 = (ToggleButton) findViewById(R.id.edge11);
        ToggleButton e12 = (ToggleButton) findViewById(R.id.edge12);
        ToggleButton eM = (ToggleButton) findViewById(R.id.edgeMid);

        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMiddleEdge(e6, e7, e8, eM);
            }
        });

        e7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMiddleEdge(e6, e7, e8, eM);
            }
        });

        e8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMiddleEdge(e6, e7, e8, eM);
            }
        });

        Button discardButton = (Button) findViewById(R.id.discardButton);
        discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setChecked(false);
                e2.setChecked(false);
                e3.setChecked(false);
                e4.setChecked(false);
                e5.setChecked(false);
                e6.setChecked(false);
                e7.setChecked(false);
                e8.setChecked(false);
                e9.setChecked(false);
                e10.setChecked(false);
                e11.setChecked(false);
                e12.setChecked(false);
                eM.setChecked(false);

            }
        });



    }

    public void checkMiddleEdge(ToggleButton e6, ToggleButton e7, ToggleButton e8,ToggleButton eM) {
        if (e6.isChecked() || e7.isChecked() || e8.isChecked()){
            eM.setChecked(true);
        } else {
            eM.setChecked(false);
        }
    }

}