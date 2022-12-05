package com.example.quran_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.function.Predicate;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView LVsurah;
    Quran q=new Quran();
    String[] surahArray;
    EditText SearchBysurahNumber;
    ArrayAdapter<String> surahAdapter;
    TextView verse;
    Button btn;
    int requiredVerse;
    int surahStart;
    int surahNumber;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LVsurah=findViewById(R.id.lvsurah);
        surahArray=q.urduSurahNames;
        surahAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,surahArray);
        LVsurah.setAdapter(surahAdapter);
        LVsurah.setOnItemClickListener(this);
        SearchBysurahNumber=findViewById(R.id.searchbySurah);
        verse=findViewById(R.id.textViewVerse);
        btn=findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    requiredVerse = Integer.parseInt(String.valueOf(SearchBysurahNumber.getText()));
                    if(requiredVerse>(q.surahAyatCount[surahNumber])){
                        verse.setText("Not correct");
                    }
                    else {

                        verse.setText(String.valueOf(q.QuranArabicText[surahStart + requiredVerse-1]));
                        //  verse.setText(String.valueOf(q.surahAyatCount[surahNumber]));
                    }
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String surah=adapterView.getItemAtPosition(i).toString();

        Toast.makeText(getApplicationContext(), "Clicked"+surah+(i+1), Toast.LENGTH_SHORT).show();

         surahNumber=i;
         surahStart=q.SSP[i];

        }

}