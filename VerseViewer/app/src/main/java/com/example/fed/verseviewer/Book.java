package com.example.fed.verseviewer;

import java.util.Scanner;
import android.support.v7.app.AppCompatActivity;

public class Book {
    public String lines[] = new String [7000];

    Book(AppCompatActivity activity, int resourceID){
        Scanner scan = new Scanner(activity.getResources().openRawResource(resourceID));
        int i = 0;
        while(scan.hasNext()){
            String line = scan.nextLine();
            lines[i] = line;
            i++;
        }
    }
}
