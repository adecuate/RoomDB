package com.example.appforhour.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appforhour.App;
import com.example.appforhour.R;
import com.example.appforhour.db.AppDatabase;
import com.example.appforhour.entity.Word;

public class AddWordActivity extends AppCompatActivity {

    private EditText wordEditText;
    private EditText translateEditText;
    private Button saveButton;

    private AppDatabase appDatabase = App.getAppDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        wordEditText = findViewById(R.id.word_editText);
        translateEditText = findViewById(R.id.input_transl_editText);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wordString = wordEditText.getText().toString();
                String translationString = translateEditText.getText().toString();

                Word word = new Word(wordString,translationString);
                saveToDB(word);
               /* String msg = wordString+";"+translationString;
                Toast.makeText(AddWordActivity.this, msg, Toast.LENGTH_SHORT).show();
                */
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void saveToDB(final Word word){
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                // add word to db
                appDatabase.getWordDAO().insert(word);
                return true;
            }

            //back prev screen
            @Override
            protected void onPostExecute(Boolean isSuccess) {
             setResult(RESULT_OK);
             finish();
            }
        }.execute();



    }


}
