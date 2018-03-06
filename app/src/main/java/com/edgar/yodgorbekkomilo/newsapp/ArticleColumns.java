package com.edgar.yodgorbekkomilo.newsapp;

import android.net.Uri;
import android.provider.BaseColumns;

import com.edgar.yodgorbekkomilo.newsapp.provider.ArticleProvider;

/**
 * Created by yodgorbekkomilov on 3/6/18.
 */

public class ArticleColumns  implements BaseColumns{

    public static final String TABLE_NAME = "article";
    public static final Uri CONTENT_URI = Uri.parse(ArticleProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TITLE = "title";

    public static final String TITLE_DESCRIPTION = "overview";

    public static final String AUTHOR = "vote_average";

    public static final String VOTE_COUNT = "vote_count";



    public static final String IS_FAVOURITE = "is_favourite";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." + _ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[]{
            _ID,
            TITLE,
            TITLE_DESCRIPTION,
            AUTHOR,
            IS_FAVOURITE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(TITLE_DESCRIPTION) || c.contains("." + TITLE_DESCRIPTION)) return true;
            if (c.equals(AUTHOR) || c.contains("." + AUTHOR)) return true;

            if (c.equals(IS_FAVOURITE) || c.contains("." + IS_FAVOURITE)) return true;
        }
        return false;
    }

}



