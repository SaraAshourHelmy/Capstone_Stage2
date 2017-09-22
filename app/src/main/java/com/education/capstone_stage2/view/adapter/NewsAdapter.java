package com.education.capstone_stage2.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.model.News;
import com.education.capstone_stage2.view.activity.HomeActivity;

import java.util.ArrayList;

/**
 * Created by Sara on 6/3/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder> {

    ArrayList<News> lstNews;
    RecyclerItemClick recyclerItemClick;

    public NewsAdapter(ArrayList<News> lstNews, RecyclerItemClick recyclerItemClick) {
        this.lstNews = lstNews;
        this.recyclerItemClick = recyclerItemClick;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.news_item_list, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return lstNews.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgNews;
        TextView tvNewsTitle;
        TextView tvNewsDescription;
        View itemView;

        public Holder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imgNews = (ImageView) itemView.findViewById(R.id.imgV_news);
            tvNewsTitle = (TextView) itemView.findViewById(R.id.tv_newsTitle);
            tvNewsDescription = (TextView) itemView.findViewById(R.id.tv_newsDescription);
            itemView.setOnClickListener(this);
        }

        public void bindData() {
            int position = getAdapterPosition();
            /*
            Picasso.with(imgNews.getContext()).
                    load(lstNews.get(position).getImgURL())
                    .placeholder(R.drawable.ic_image)
                    .error(R.drawable.loading_error)
                    .into(imgNews);*/
            if (HomeActivity.isEnglish) {
                tvNewsTitle.setText(lstNews.get(position).getEnTitle());
                tvNewsDescription.setText(lstNews.get(position).getEnShortDescription());
            } else {
                tvNewsTitle.setText(lstNews.get(position).getArTitle());
                tvNewsDescription.setText(lstNews.get(position).getArShortDescription());

            }
        }

        @Override
        public void onClick(View v) {

            recyclerItemClick.onItemClick(lstNews.get(getAdapterPosition()));
        }
    }

    public interface RecyclerItemClick {
        void onItemClick(News news);

    }
}
