package com.example.trunic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Practice extends AppCompatActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice);

        dbHelper = new DBHelper(Practice.this);

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
        TextView exampleText = (TextView) findViewById(R.id.example);

        //set text to random character
        setRandRune(dbHelper, phonemeText, symbolText, exampleText);

        //func to check if input is correct
        //if correct set text to rand character and reset rune
        //else reset rune

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

        Button check = (Button) findViewById(R.id.checkButton);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Edges = "edge";

                if (eA.isChecked()) Edges = Edges + 'A';
                if (eB.isChecked()) Edges = Edges + 'B';
                if (eC.isChecked()) Edges = Edges + 'C';
                if (eD.isChecked()) Edges = Edges + 'D';
                if (eE.isChecked()) Edges = Edges + 'E';
                if (eF.isChecked()) Edges = Edges + 'F';
                if (eG.isChecked()) Edges = Edges + 'G';
                if (eH.isChecked()) Edges = Edges + 'H';
                if (eI.isChecked()) Edges = Edges + 'I';
                if (eJ.isChecked()) Edges = Edges + 'J';
                if (eK.isChecked()) Edges = Edges + 'K';

                String[] vowelInfo = dbHelper.runeInfo(Edges, 1);
                String[] consonantInfo = dbHelper.runeInfo(Edges, 0);

                if (vowelInfo != null && vowelInfo[0].equals(phonemeText.getText().toString())){
                    setRandRune(dbHelper, phonemeText, symbolText, exampleText);
                } else if (consonantInfo != null && consonantInfo[0].equals(phonemeText.getText().toString())){
                    setRandRune(dbHelper, phonemeText, symbolText, exampleText);
                }

                eA.setChecked(false);
                eB.setChecked(false);
                eC.setChecked(false);
                e3.setChecked(false);
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

        //func to popup img of solution
        Button idkButton = (Button) findViewById(R.id.questionButton);
        idkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        Button practiceBack = (Button) findViewById(R.id.practiceBackButton);
        practiceBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Practice.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void setRandRune(DBHelper dbHelper, TextView phoneme, TextView symbol, TextView example){
        String[] randRuneInfo = dbHelper.findRandRune();
        phoneme.setText(randRuneInfo[0]);
        symbol.setText(randRuneInfo[1]);
        example.setText(randRuneInfo[2]);
    }

    public void checkMiddleEdge(ToggleButton eF, ToggleButton eG, ToggleButton eH,ToggleButton eM) {
        if (eF.isChecked() || eG.isChecked() || eH.isChecked()){
            eM.setChecked(true);
        } else {
            eM.setChecked(false);
        }
    }

}
