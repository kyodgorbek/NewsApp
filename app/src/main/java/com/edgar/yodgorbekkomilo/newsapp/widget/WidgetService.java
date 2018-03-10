package com.edgar.yodgorbekkomilo.newsapp.widget;



/**
 * Created by yodgorbekkomilov on 3/10/18.
 */



import android.content.Intent;
import android.widget.RemoteViewsService;

    /**
     * WidgetService is the {@link RemoteViewsService} that will return our RemoteViewsFactory
     */
    public class WidgetService extends RemoteViewsService {
        @Override
        public RemoteViewsFactory onGetViewFactory(Intent intent) {
            return new ArticleWidgetProvider(this, intent);
        }
    }
