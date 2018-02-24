package com.edgar.yodgorbekkomilo.newsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edgar.yodgorbekkomilo.newsapp.NewsList;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.News;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.Source;
import com.edgar.yodgorbekkomilo.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yodgorbekkomilov on 2/18/18.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {


    private  Integer results;

    List<Article> articleList;
   private   Context context;
    private LayoutInflater mInflater;

    // Constructors
    public ArticleAdapter(Context context, ArrayList<Article> articleList){
        super(context,0, articleList);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.articleList = articleList;
    }


    @Override
    public Article getItem(int position) {
        return articleList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Article item = getItem(position);

        Source source = item.getSource();
         vh.textViewStatus.setText(source.getName());
        vh.textViewTotalResults.setText(item.getAuthor());
        if(item.getUrlToImage() == null){
            vh.imageView.setImageResource(R.drawable.news_error);
        }
        else {
            Picasso.with(context).load(String.valueOf(item.getUrlToImage())).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);
        }
        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewStatus;
        public final TextView textViewTotalResults;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewStatus, TextView textViewTotalResults) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewStatus = textViewStatus;
            this.textViewTotalResults = textViewTotalResults;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewStatus = (TextView) rootView.findViewById(R.id.textViewStatus);
            TextView textViewTotalResults = (TextView) rootView.findViewById(R.id.textViewTotalResults);
            return new ViewHolder(rootView, imageView, textViewStatus, textViewTotalResults);
        }
    }
}

