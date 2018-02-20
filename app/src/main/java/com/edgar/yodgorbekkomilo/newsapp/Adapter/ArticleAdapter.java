package com.edgar.yodgorbekkomilo.newsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.News;
import com.edgar.yodgorbekkomilo.newsapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by yodgorbekkomilov on 2/18/18.
 */

public class ArticleAdapter extends ArrayAdapter<News> {

    private String status;
    private  Integer results;

    String articleList;
   private   Context context;
    private LayoutInflater mInflater;

    // Constructors
    public ArticleAdapter(Context context, String articles) {
        super(context,0, Integer.parseInt(articles));
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        articleList = articles;
    }


    @Override
    public News getItem(int position) {
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

        News item = getItem(position);

        vh.textViewStatus.setText((CharSequence) item.getStatus());
        vh.textViewTotalResults.setText(item.getTotalResults());
        Picasso.with(context).load(String.valueOf(item.getArticles())).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

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

