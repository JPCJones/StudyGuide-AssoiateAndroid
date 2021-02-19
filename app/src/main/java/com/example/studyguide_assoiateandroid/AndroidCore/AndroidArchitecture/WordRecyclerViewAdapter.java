package com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyguide_assoiateandroid.R;

import org.w3c.dom.Text;

import java.util.List;

public class WordRecyclerViewAdapter extends RecyclerView.Adapter<WordRecyclerViewAdapter.ViewHolder>{

    private List<WordObject> words;
    private Context context;

    public WordRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setWords(List<WordObject> words){
        this.words = words;
    }

    public WordObject deleteWord(int position){
        return words.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.word_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(words != null){
            holder.textView.setText(words.get(position).getWord());
        }else
            holder.textView.setText("No Data");

    }

    @Override
    public int getItemCount() {
        if(words != null) {
            return words.size();
        }else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
        }
    }
}
