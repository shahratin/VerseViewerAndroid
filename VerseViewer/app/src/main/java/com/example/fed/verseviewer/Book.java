package com.example.fed.verseviewer;

import java.util.Scanner;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Book {
    public String lines[] = new String [7000];

    Book(AppCompatActivity activity, int resourceID){
        Scanner scan = new Scanner(activity.getResources().openRawResource(resourceID));
        int i = 0;
        while(scan.hasNext()){
            try {
                String line = scan.nextLine();
                lines[i] = line;
            }catch(Exception e) {
                Log.d("MyException", String.valueOf(e));

            }
            i++;
        }
        scan.close();
    }
}
