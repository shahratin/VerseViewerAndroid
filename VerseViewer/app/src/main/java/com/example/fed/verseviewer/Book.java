package com.example.fed.verseviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import android.support.v7.app.AppCompatActivity;

public class Book {
    public List<String> lines = new ArrayList<>();
    Book(AppCompatActivity activity, int resourceID){
        Scanner scan = new Scanner(activity.getResources().openRawResource(resourceID));
        while(scan.hasNextLine()){
            lines.add(scan.nextLine());
        }
    }
}
