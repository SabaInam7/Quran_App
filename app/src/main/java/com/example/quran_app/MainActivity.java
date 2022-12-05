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
        SearchBysurahNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //MainActivity.this.surahAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String surah=adapterView.getItemAtPosition(i).toString();

        Toast.makeText(getApplicationContext(), "Clicked"+surah+(i+1), Toast.LENGTH_SHORT).show();
//        SearchBysurahNumber.setText("");
        int surahStart=q.SSP[i];
        try{
            int requiredVerse = Integer.parseInt(String.valueOf(SearchBysurahNumber.getText()));
           verse.setText(q.QuranArabicText[surahStart+requiredVerse]);

        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }



        }
}