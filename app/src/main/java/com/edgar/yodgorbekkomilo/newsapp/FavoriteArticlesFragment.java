package com.edgar.yodgorbekkomilo.newsapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yodgorbekkomilov on 3/9/18.
 */

public class FavoriteArticlesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    public static long currentVisiblePosition = 0;
    public static final int LOADER_ID = 0;
    ArrayList<Article> articleList;
    CustomAdapter adapter;
    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_articles_favorite, container, false);
        recycler = view.findViewById(R.id.favorite_View);
        getLoaderManager().initLoader(LOADER_ID, null, this);

        articleList = new ArrayList<>();
        String uri = ArticleColumns.CONTENT_URI.toString();

        Cursor cursor =
                getActivity().getApplicationContext().getContentResolver().query(Uri.parse(uri), null, null,
                        null, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                Log.i("check_data", cursor.toString());

                cursor.moveToFirst();
                do {

                    String title = cursor.getString(cursor.getColumnIndex(ArticleColumns.TITLE));
                    String description = cursor.getString(cursor.getColumnIndex(ArticleColumns.TITLE_DESCRIPTION));
                    String author = cursor.getString(cursor.getColumnIndex(ArticleColumns.AUTHOR));
                    String image = cursor.getString(cursor.getColumnIndex(ArticleColumns.IMAGE));
                    String link = cursor.getString(cursor.getColumnIndex(ArticleColumns.LINK));

                    articleList.add(new Article(title, description, author, image, link));
                    Log.i("check_data", title + " " + description + " " + author);
                } while (cursor.moveToNext());

                adapter = new CustomAdapter(getActivity());
                recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                recycler.setAdapter(adapter);
                ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                        0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Article article = articleList.get(position);
                        articleList.remove(position);
                        adapter.notifyItemRemoved(position);
                        showSnackBar(view, position, article);
                    }
                });

                helper.attachToRecyclerView(recycler);
            }

        } else {
            Log.i("check_data", "cursor is null");
        }

        return view;
    }

    private void showSnackBar(View v, final int position, final Article article) {
        Snackbar.make(v, "Item removed", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        articleList.add(position, article);
                        adapter.notifyItemInserted(position);
                    }
                }).show();
    }

    @Override
    public void onPause() {
        // this variable should be static in class
        currentVisiblePosition = ((LinearLayoutManager) recycler.getLayoutManager()).findFirstCompletelyVisibleItemPosition();

        super.onPause();
    }

    @Override
    public void onResume() {
        // this variable should be static in class
        recycler.getLayoutManager().scrollToPosition((int) currentVisiblePosition);
        currentVisiblePosition = 0;

        super.onResume();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        Context context;

        public CustomAdapter(Context context) {
            this.context = context;
        }

        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.layout_row_view, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {
            if (!articleList.isEmpty()) {
                Article article = articleList.get(position);
                holder.title.setText(article.getTitle());
                holder.authorName.setText(article.getAuthor());

                if (article.getUrlToImage() == null) {
                    holder.image.setImageResource(R.drawable.news_error);
                } else {
                    Picasso.with(context)
                            .load(article.getUrlToImage())
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.ic_launcher)
                            .into(holder.image);
                }

            } else {
                Log.i("check_data", "article list is empty");
            }

        }

        @Override
        public int getItemCount() {
            return articleList.size();
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {

            ImageView image;
            TextView title;
            TextView authorName;

            public CustomViewHolder(View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.textViewStatus);
                authorName = itemView.findViewById(R.id.textViewTotalResults);
                image = itemView.findViewById(R.id.imageView);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("myDataKey", articleList.get(getAdapterPosition()));
                context.startActivity(intent);
            }
        }
    }

}