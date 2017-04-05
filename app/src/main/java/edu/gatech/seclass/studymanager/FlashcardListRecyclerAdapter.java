package edu.gatech.seclass.studymanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jaekyuoh on 2017. 4. 5..
 */

public class FlashcardListRecyclerAdapter extends RecyclerView.Adapter<FlashcardListRecyclerAdapter.FlashcardListViewHolder>{
    private Context context;
    private ArrayList<String> flashcardNameList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String str);
    }
    private int lastPosition = -1;

    public FlashcardListRecyclerAdapter(Context context, ArrayList<String> flashcardNameList, OnItemClickListener listener) {
        this.context = context;
        this.flashcardNameList = flashcardNameList;
        this.listener = listener;
    }

    @Override
    public FlashcardListRecyclerAdapter.FlashcardListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 새로운 뷰를 만든다
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_flashcard,parent,false);
        FlashcardListRecyclerAdapter.FlashcardListViewHolder holder = new FlashcardListRecyclerAdapter.FlashcardListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(FlashcardListRecyclerAdapter.FlashcardListViewHolder holder, int position) {
        holder.flashcardListName.setText(flashcardNameList.get(position));
        holder.bind(flashcardNameList.get(position), listener);



    }

    @Override
    public int getItemCount() {
        return flashcardNameList.size();
    }

//    class FlashcardListViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.flashcard_list_name_textview) TextView flashcardListName;
//
//        FlashcardListViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }

    static class FlashcardListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.flashcard_list_name_textview) TextView flashcardListName;
        public FlashcardListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void bind(final String str, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(str);
                }
            });

        }

    }

}
