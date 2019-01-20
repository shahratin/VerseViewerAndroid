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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verse_view);

        /*
        int index = Index.get(2, 11);

        Book uthmani= new Book(this,R.raw.quran_uthmani);
        Book en= new Book(this,R.raw.en_yusufali);

        Log.d("MyMessage", simple.lines[index]+uthmani.lines[index]+en.lines[index]+bn.lines[index]);
        */
        updateView(3,3);

    }

    public void listen(View view) {
    }

    public void show(View view) {
        TextView suraIndexText = findViewById(R.id.suraIndex);
        TextView ayatIndexText = findViewById(R.id.ayatIndex);
        int suraIndex = Integer.parseInt(String.valueOf(suraIndexText.getText())) - 1;
        int ayatIndex = Integer.parseInt(String.valueOf(ayatIndexText.getText())) - 1;
        updateView(suraIndex, ayatIndex);
    }

    public void updateView(int suraIndex, int ayatIndex){
        int index = Index.get(suraIndex, ayatIndex);
        Book simple= new Book(this,R.raw.quran_simple);
        Book bn= new Book(this,R.raw.bn_bengali);
        //Book en= new Book(this,R.raw.en_yusufali);
        TextView topText = findViewById(R.id.topText);
        TextView bottomText = findViewById(R.id.bottomText);
        topText.setText(simple.lines[index]);
        bottomText.setText(bn.lines[index]);

    }

    public void showPrevious(View view) {
    }

    public void showNext(View view) {
    }

    public void isEnglish(View view) {
    }
}
