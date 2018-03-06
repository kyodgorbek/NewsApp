package com.edgar.yodgorbekkomilo.newsapp.provider;

/**
 * Created by yodgorbekkomilov on 3/1/18.
 */

public abstract class ArticleProvider extends BaseContentProvider {

    public static final String AUTHORITY = "com.edgar.yodgorbekkomilo.newsapp.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;
    private static final String TAG = ArticleProvider.class.getSimpleName();
    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";
    private static final int URI_TYPE_ARTICLE = 0;
    private static final int URI_TYPE_ARTICLE_ID = 1;


    /**
     * Constructs a provider with the specified name, version number,
     * and information.
     *
     * @param name    the provider name.
     * @param version the provider version number.
     * @param info    a description of the provider and its services.
     */
    protected ArticleProvider(String name, double version, String info) {

    }
}
