package com.example.theeagle.news.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theeagle.news.R;
import com.example.theeagle.news.models.NewsFeed;

import java.util.ArrayList;


public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.ViewHolder> {

    private final ArrayList<NewsFeed> newsFeed;
    private final Context context;


    public NewsFeedAdapter(ArrayList<NewsFeed> newsFeed, Context context) {
        this.newsFeed = newsFeed;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.newsFeed = newsFeed.get(position);
        holder.title.setText(holder.newsFeed.getTitle());
        holder.section.setText(holder.newsFeed.getSection());
        holder.date.setText(holder.newsFeed.getTime());
        holder.author.setText(holder.newsFeed.getAuthorName());

    }

    public void updateAdapter(final ArrayList<NewsFeed> newsFeed) {
        if (this.newsFeed != null && !this.newsFeed.isEmpty()) this.newsFeed.clear();
        if (this.newsFeed != null) this.newsFeed.addAll(newsFeed);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return newsFeed.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, section, date, author;
        private NewsFeed newsFeed;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            section = itemView.findViewById(R.id.section);
            date = itemView.findViewById(R.id.date);
            author = itemView.findViewById(R.id.author);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(newsFeed.getUrl())));
        }
    }

}
