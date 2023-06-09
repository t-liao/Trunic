package com.example.trunic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(MainActivity.this);

        ToggleButton eA = (ToggleButton) findViewById(R.id.edge1);
        ToggleButton eB = (ToggleButton) findViewById(R.id.edge2);
        ToggleButton eC = (ToggleButton) findViewById(R.id.edge3);
        ToggleButton e3 = (ToggleButton) findViewById(R.id.edge3b);
        ToggleButton eD = (ToggleButton) findViewById(R.id.edge4);
        ToggleButton eE = (ToggleButton) findViewById(R.id.edge5);
        ToggleButton eF = (ToggleButton) findViewById(R.id.edge6);
        ToggleButton eG = (ToggleButton) findViewById(R.id.edge7);
        ToggleButton eH = (ToggleButton) findViewById(R.id.edge8);
        ToggleButton eI = (ToggleButton) findViewById(R.id.edge9);
        ToggleButton eJ = (ToggleButton) findViewById(R.id.edge10);
        ToggleButton eK = (ToggleButton) findViewById(R.id.edge11);
        ToggleButton eL = (ToggleButton) findViewById(R.id.edge12);
        ToggleButton eM = (ToggleButton) findViewById(R.id.edgeMid);

        TextView phonemeText = (TextView) findViewById(R.id.phonemes);
        TextView symbolText = (TextView) findViewById(R.id.symbol);
        TextView exampleLeftText = (TextView) findViewById(R.id.exampleLeft);
        TextView exampleRightText = (TextView) findViewById(R.id.exampleRight);

        eF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMiddleEdge(eF, eG, eH, eM);
            }
        });

        eG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMiddleEdge(eF, eG, eH, eM);
            }
        });

        eH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMiddleEdge(eF, eG, eH, eM);
            }
        });

        eC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e3.setChecked(eC.isChecked());
            }
        });

        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eC.setChecked(e3.isChecked());
            }
        });

        Button discardButton = (Button) findViewById(R.id.discardButton);
        discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eA.setChecked(false);
                eB.setChecked(false);
                eC.setChecked(false);
                eD.setChecked(false);
                eE.setChecked(false);
                eF.setChecked(false);
                eG.setChecked(false);
                eH.setChecked(false);
                eI.setChecked(false);
                eJ.setChecked(false);
                eK.setChecked(false);
                eL.setChecked(false);
                eM.setChecked(false);

            }
        });

        Button translate = (Button) findViewById(R.id.checkButton);
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText(phonemeText, symbolText, exampleLeftText, exampleRightText);
                String vowelEdges = "edge";
                String consonantEdges = "edge";

                if (eA.isChecked()) vowelEdges = vowelEdges + 'A';
                if (eB.isChecked()) vowelEdges = vowelEdges + 'B';
                if (eC.isChecked()) vowelEdges = vowelEdges + 'C';
                if (eD.isChecked()) vowelEdges = vowelEdges + 'D';
                if (eE.isChecked()) vowelEdges = vowelEdges + 'E';

                String[] vowelInfo = dbHelper.runeInfo(vowelEdges);

                if (eF.isChecked()) consonantEdges = consonantEdges + 'F';
                if (eG.isChecked()) consonantEdges = consonantEdges + 'G';
                if (eH.isChecked()) consonantEdges = consonantEdges + 'H';
                if (eI.isChecked()) consonantEdges = consonantEdges + 'I';
                if (eJ.isChecked()) consonantEdges = consonantEdges + 'J';
                if (eK.isChecked()) consonantEdges = consonantEdges + 'K';

                String[] consonantInfo = dbHelper.runeInfo(consonantEdges);

                if (vowelInfo != null && consonantInfo != null){

                    if (eL.isChecked()){
                        // vowel before consonant
                        phonemeText.setText(vowelInfo[0] + " " + consonantInfo[0]);
                        symbolText.setText(vowelInfo[1] + " " + consonantInfo[1]);
                        exampleLeftText.setText(vowelInfo[2]);
                        exampleRightText.setText(consonantInfo[2]);

                    } else {
                        // consonant before vowel
                        phonemeText.setText(consonantInfo[0] + " " + vowelInfo[0]);
                        symbolText.setText(consonantInfo[1] + " " + vowelInfo[1]);
                        exampleLeftText.setText(consonantInfo[2]);
                        exampleRightText.setText(vowelInfo[2]);
                    }

                } else if (vowelInfo != null){
                    phonemeText.setText(vowelInfo[0]);
                    symbolText.setText(vowelInfo[1]);
                    exampleLeftText.setText(vowelInfo[2]);

                } else if (consonantInfo != null){
                    phonemeText.setText(consonantInfo[0]);
                    symbolText.setText(consonantInfo[1]);
                    exampleLeftText.setText(consonantInfo[2]);

                } else {
                    phonemeText.setText("No results found");
                }

            }
        });

    }


    public void clearText(TextView a, TextView b, TextView c, TextView d) {
        a.setText("");
        b.setText("");
        c.setText("");
        d.setText("");
    }

    public void checkMiddleEdge(ToggleButton eF, ToggleButton eG, ToggleButton eH,ToggleButton eM) {
        if (eF.isChecked() || eG.isChecked() || eH.isChecked()){
            eM.setChecked(true);
        } else {
            eM.setChecked(false);
        }
    }

}