package com.edgar.yodgorbekkomilo.newsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;
import com.edgar.yodgorbekkomilo.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yodgorbekkomilov on 2/18/18.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {

    List<Article> articleList;
   private   Context context;
    private LayoutInflater mInflater;

    // Constructors
    public ArticleAdapter(Context context, List<Article> articles) {
        super(context,0, articles);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        articleList = articles;
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

        vh.textViewSource.setText((CharSequence) item.getSource());
        vh.textViewAuthor.setText((CharSequence) item.getAuthor());
        Picasso.with(context).load(item.getUrlToImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewSource;
        public final TextView textViewAuthor;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewSource, TextView textViewAuthor) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewSource = textViewSource;
            this.textViewAuthor = textViewAuthor;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewSource = (TextView) rootView.findViewById(R.id.textViewSource);
            TextView textViewAuthor = (TextView) rootView.findViewById(R.id.textViewAuthor);
            return new ViewHolder(rootView, imageView, textViewSource, textViewAuthor);
        }
    }
}

