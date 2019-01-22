package com.example.fed.verseviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class VerseView extends AppCompatActivity {
    boolean isEnglish = false;
    int suraIndex = 1;
    int ayatIndex = 1;
    Book simple, bn, en;
    Menu englishMenu;
    TextView topText, bottomText, suraIndexText, ayatIndexText;
    CheckBox isEnglishBox;
    Spinner suraList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verse_view);
        simple= new Book(this,R.raw.quran_simple);
        bn= new Book(this,R.raw.bn_bengali);
        en= new Book(this,R.raw.en_yusufali);
        englishMenu = new Menu(this,R.raw.sura_list_en);
        topText = findViewById(R.id.topText);
        bottomText = findViewById(R.id.bottomText);
        suraIndexText = findViewById(R.id.suraIndex);
        ayatIndexText = findViewById(R.id.ayatIndex);
        isEnglishBox = findViewById(R.id.isEnglish);
        suraList = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, englishMenu.menu);
        suraList.setAdapter(adapter);
        suraList.setOnItemSelectedListener(suraListListener);



        /*suraList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        */
        /*
        int index = Index.get(2, 11);

        Book uthmani= new Book(this,R.raw.quran_uthmani);
        Book en= new Book(this,R.raw.en_yusufali);

        Log.d("MyMessage", simple.lines[index]+uthmani.lines[index]+en.lines[index]+bn.lines[index]);
        */
        updateView();

    }
    private AdapterView.OnItemSelectedListener suraListListener= new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView parent, View v, int position, long id){
            suraIndex = position + 1;
            updateView();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    public void listen(View view) {
    }

    public void show(View view) {
        checkTextField();
        updateSuraName();
        updateView();
    }

    public void updateView(){
        if(suraIndex == 0 || ayatIndex ==0){
            suraIndex = 1;
            ayatIndex = 1;
        }else if(suraIndex > 114){
            suraIndex = 114;
            if(ayatIndex >= Index.ayatInSura[suraIndex-1]){
                ayatIndex = Index.ayatInSura[suraIndex-1];
            }
        }else{
            if(ayatIndex >= Index.ayatInSura[suraIndex-1]) {
                ayatIndex = Index.ayatInSura[suraIndex-1];
            }
        }
        int index = Index.get(suraIndex, ayatIndex);

        suraIndexText.setText(Integer.toString(suraIndex));
        ayatIndexText.setText(Integer.toString(ayatIndex));
        topText.setText(simple.lines.get(index));
        if(isEnglish){
            bottomText.setText(en.lines.get(index));
        }else {
            bottomText.setText(bn.lines.get(index));
        }

    }

    public void showPrevious(View view) {
        checkTextField();
        if(suraIndex <= 0){
            suraIndex = 1;
        }else if(suraIndex <= 114) {


            if (ayatIndex <= 0) {
                ayatIndex = 1;
            } else if (ayatIndex == 1) {
                if (suraIndex == 1) {
                    //do nothing
                } else {
                    suraIndex--;
                    updateSuraName();
                    ayatIndex = Index.ayatInSura[suraIndex - 1];
                }
            } else {
                ayatIndex--;
            }
        }else{
            suraIndex = 114;
        }


        updateView();
    }

    private void checkTextField() {
        if(!suraIndexText.getText().toString().matches("") && !ayatIndexText.getText().toString().matches("")) {
            suraIndex = Integer.parseInt(String.valueOf(suraIndexText.getText()));
            ayatIndex = Integer.parseInt(String.valueOf(ayatIndexText.getText()));
        }
    }

    public void showNext(View view) {
        checkTextField();
        if(suraIndex <= 0){
            suraIndex = 0;
            updateSuraName();
        }else if(suraIndex >= 114){
            suraIndex = 114;
            updateSuraName();
            if(ayatIndex < Index.ayatInSura[suraIndex-1]){
                ayatIndex++;
            }
        }else if(ayatIndex >= Index.ayatInSura[suraIndex-1]){
            if(suraIndex >= 114){
                //do nothing
            }else {
                suraIndex++;
                updateSuraName();
                ayatIndex = 1;
            }
        }else{
           ayatIndex++;
        }
        updateView();
    }

    public void isEnglish(View view) {
        isEnglish = isEnglishBox.isChecked();
        checkTextField();
        updateView();
    }

    private void updateSuraName(){
        if(suraIndex <= 0){
            suraList.setSelection(0);
        }else if(suraIndex < 114) {
            suraList.setSelection(suraIndex - 1);
        }else{
            suraList.setSelection(113);
        }
    }

    public void selectSura(View view) {
        //suraIndex = suraList.getSelectedItemPosition();
       // updateView();
    }
}
