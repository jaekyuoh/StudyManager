package edu.gatech.seclass.studymanager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.gatech.seclass.studymanager.R;

/**
 * Created by jaekyuoh on 2017. 4. 6..
 */

public class WordsListRecyclerAdapter extends RecyclerView.Adapter<WordsListRecyclerAdapter.WordsListViewHolder>{
    private Context context;
    private ArrayList<String> frontList;
    private ArrayList<String> backList;
    private final WordsListRecyclerAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String str);
    }
    private int lastPosition = -1;

    public WordsListRecyclerAdapter(Context context, ArrayList<String> frontList, ArrayList<String> backList, WordsListRecyclerAdapter.OnItemClickListener listener) {
        this.context = context;
        this.frontList = frontList;
        this.backList = backList;
        this.listener = listener;
    }

    @Override
    public WordsListRecyclerAdapter.WordsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 새로운 뷰를 만든다
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_word,parent,false);
        WordsListRecyclerAdapter.WordsListViewHolder holder = new WordsListRecyclerAdapter.WordsListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(WordsListRecyclerAdapter.WordsListViewHolder holder, int position) {
        holder.frontListName.setText(frontList.get(position));
        holder.backListName.setText(backList.get(position));
        holder.bind(frontList.get(position), listener);
        holder.bind(backList.get(position), listener);



    }

    @Override
    public int getItemCount() {
        return frontList.size();
    }

    static class WordsListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.front_name_textview) TextView frontListName;
        @BindView(R.id.back_name_textview) TextView backListName;
        public WordsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void bind(final String str, final WordsListRecyclerAdapter.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(str);
                }
            });

        }

    }
}
