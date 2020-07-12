package com.example.appforhour.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.appforhour.App;
import com.example.appforhour.R;
import com.example.appforhour.adapter.WordAdapter;
import com.example.appforhour.db.AppDatabase;
import com.example.appforhour.entity.Word;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_WORD_RC = 1001;

    // init variable
    private FloatingActionButton addButton;
    private RecyclerView recyclerView;
    private WordAdapter wordAdapter;

    // использыв DB
    private AppDatabase appDatabase = App.getAppDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find components
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // указать что хотим перейти на новый екран 1) с какого 2) куда
                Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
                // 1) intent 2) константа с каким кодом мы запускали активити
                startActivityForResult(intent, ADD_WORD_RC);
            }
        });
        recyclerView = findViewById(R.id.word_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        //созд адаптер
        wordAdapter = new WordAdapter();
        //связыв с recyclerView
        recyclerView.setAdapter(wordAdapter);
        loadWords();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // requestCode при старте совпадает с тем который к нам пришёл
        if(requestCode==ADD_WORD_RC && requestCode==RESULT_OK){
            loadWords();
        }
    }

    private void loadWords(){
            // запрос к DB
            // нельзя делать запросы к БД в главном потоке для этого юзаем AsyncTask
            // запускаем новый поток

            //1) Void 2)Void 3) Какой тип арг будет передан в главн поток
            new AsyncTask<Void, Void, List<Word>>() {
                @Override
                protected List<Word> doInBackground(Void... voids) {
                    return appDatabase.getWordDAO().getWords();
                }

                // работа с комп польз интерф
                @Override
                protected void onPostExecute(List<Word> words) {
                    wordAdapter.setWords(words);
                }
            }.execute();



        }
}
