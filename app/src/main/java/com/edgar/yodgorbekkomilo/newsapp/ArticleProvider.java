package com.edgar.yodgorbekkomilo.newsapp;

import java.security.Provider;

/**
 * Created by yodgorbekkomilov on 3/1/18.
 */

public class ArticleProvider extends Provider {
    /**
     * Constructs a provider with the specified name, version number,
     * and information.
     *
     * @param name    the provider name.
     * @param version the provider version number.
     * @param info    a description of the provider and its services.
     */
    protected ArticleProvider(String name, double version, String info) {
        super(name, version, info);
    }
}
