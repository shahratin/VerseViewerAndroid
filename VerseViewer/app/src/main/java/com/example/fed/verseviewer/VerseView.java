package com.example.fed.verseviewer;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import static android.view.View.TEXT_ALIGNMENT_VIEW_END;
import static android.view.View.TEXT_DIRECTION_ANY_RTL;

public class VerseView extends AppCompatActivity {
    boolean isEnglish = false;
    int suraIndex = 1;
    int ayatIndex = 1;
    Book simple, bn, en;
    TextView topText, bottomText, suraIndexText, ayatIndexText;
    CheckBox isEnglishBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verse_view);
        simple= new Book(this,R.raw.quran_simple);
        bn= new Book(this,R.raw.bn_bengali);
        en= new Book(this,R.raw.en_yusufali);
        topText = findViewById(R.id.topText);
        bottomText = findViewById(R.id.bottomText);
        suraIndexText = findViewById(R.id.suraIndex);
        ayatIndexText = findViewById(R.id.ayatIndex);
        isEnglishBox = findViewById(R.id.isEnglish);
        /*
        int index = Index.get(2, 11);

        Book uthmani= new Book(this,R.raw.quran_uthmani);
        Book en= new Book(this,R.raw.en_yusufali);

        Log.d("MyMessage", simple.lines[index]+uthmani.lines[index]+en.lines[index]+bn.lines[index]);
        */
        updateView();

    }

    public void listen(View view) {
    }

    public void show(View view) {
        if(suraIndexText.getText().toString()!="" && ayatIndexText.getText().toString()!="") {
            suraIndex = Integer.parseInt(String.valueOf(suraIndexText.getText()));
            ayatIndex = Integer.parseInt(String.valueOf(ayatIndexText.getText()));
        }
        updateView();
    }

    public void updateView(){
        if(suraIndex == 0 || ayatIndex ==0){
            suraIndex = 1;
            ayatIndex = 1;
        }
        int index = Index.get(suraIndex, ayatIndex);
        suraIndexText.setText(Integer.toString(suraIndex));
        ayatIndexText.setText(Integer.toString(ayatIndex));
        topText.setText(simple.lines[index]);
        if(isEnglish){
            bottomText.setText(en.lines[index]);
        }else {
            bottomText.setText(bn.lines[index]);
        }

    }

    public void showPrevious(View view) {
        if(suraIndexText.getText().toString()!="" && ayatIndexText.getText().toString()!="") {
            suraIndex = Integer.parseInt(String.valueOf(suraIndexText.getText()));
            ayatIndex = Integer.parseInt(String.valueOf(ayatIndexText.getText()));
        }
        if(ayatIndex == 1){
            if(suraIndex == 1){
             //do nothing
            }else {
                suraIndex--;
                ayatIndex = Index.ayatInSura[suraIndex-1];
            }
        }else{
            ayatIndex--;
        }


        updateView();
    }

    public void showNext(View view) {
        if(suraIndexText.getText().toString()!="" && ayatIndexText.getText().toString()!="") {
            suraIndex = Integer.parseInt(String.valueOf(suraIndexText.getText()));
            ayatIndex = Integer.parseInt(String.valueOf(ayatIndexText.getText()));
        }
        if(ayatIndex >= Index.ayatInSura[suraIndex-1]){
            if(suraIndex==114){
                //do nothing
            }else {
                suraIndex++;
                ayatIndex = 1;
            }
        }else{
           ayatIndex++;
        }
        updateView();
    }

    public void isEnglish(View view) {
        isEnglish = isEnglishBox.isChecked();
        if(suraIndexText.getText().toString()!="" && ayatIndexText.getText().toString()!="") {
            suraIndex = Integer.parseInt(String.valueOf(suraIndexText.getText()));
            ayatIndex = Integer.parseInt(String.valueOf(ayatIndexText.getText()));
        }
        updateView();
    }
}
