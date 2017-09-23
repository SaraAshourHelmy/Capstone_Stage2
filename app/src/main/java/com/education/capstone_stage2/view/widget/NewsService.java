package com.education.capstone_stage2.view.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Sara on 7/18/2017.
 */

public class NewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new NewsWidgetFactory(this.getApplicationContext());
    }

}
