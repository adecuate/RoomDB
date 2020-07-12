package com.example.appforhour.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appforhour.R;
import com.example.appforhour.entity.Word;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<Word> words;

    public WordAdapter() {
        words = new ArrayList<>();
    }

    public void setWords(List<Word> words) {
        if (this.words.isEmpty()){
            this.words.clear();
            this.words.addAll(words);
            //перерисовка содержимого
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.word_item_view, parent, false);
        return new WordViewHolder(view);
    }

    // связать обж с представлением
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        // передать дата котор он должен отрисовать
        Word word = words.get(position);
        holder.bind(word);
    }

    // сколько елем нужно отрисовать
    @Override
    public int getItemCount() {
        return words.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder{

        private TextView engVerTextView;
        private TextView rusVerTextView;


        public WordViewHolder(@NonNull View itemView) {
            super(itemView);


            //  связь xml и java
            engVerTextView = itemView.findViewById(R.id.eng_ver_textView);
            rusVerTextView = itemView.findViewById(R.id.rus_ver_textView);

        }

        public void bind(Word word) {
            engVerTextView.setText(word.getEnglishVer());
            rusVerTextView.setText(word.getRusVer());
        }

    }
}
