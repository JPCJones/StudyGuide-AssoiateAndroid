package com.example.studyguide_assoiateandroid.UserInterface.PagingEx;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyguide_assoiateandroid.R;

import java.util.List;

//******************************************************************************
//not a normal adapter
public class PagingRecyclerViewAdapter extends PagedListAdapter<PagingWordObject, PagingRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "PagingRecyclerViewAdapt";

    private List<PagingWordObject> words;
    private Context context;

    protected PagingRecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    //    public void setWords(List<PagingWordObject> words) {
//        Log.d(TAG, "setWords: "+ words);
//        this.words = words;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PagingWordObject word = getItem(position);
        PagingWordDatabase db = PagingWordDatabase.getInstance(context);
        if(position <= db.pagingWordDao().getWordCount()){
            if (word != null) {
                holder.textView.setText(word.getWord());
            } else {
                // Null defines a placeholder item - PagedListAdapter automatically
                // invalidates this row when the actual object is loaded from the
                // database.
                notifyDataSetChanged();
            }
        }

    }

    private static DiffUtil.ItemCallback<PagingWordObject> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<PagingWordObject>() {

                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(@NonNull PagingWordObject oldItem, @NonNull PagingWordObject newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull PagingWordObject oldItem, @NonNull PagingWordObject newItem) {
                    return oldItem.equals(newItem);
                }
            };



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
