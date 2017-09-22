package com.education.capstone_stage2.view.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.db.NewsContract;
import com.education.capstone_stage2.model.News;
import com.education.capstone_stage2.model.NewsType;
import com.education.capstone_stage2.utils.NavigationManager;
import com.education.capstone_stage2.utils.ToolbarUtils;
import com.education.capstone_stage2.view.activity.DetailsActivity;
import com.education.capstone_stage2.view.activity.HomeActivity;
import com.education.capstone_stage2.view.adapter.NewsAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements NewsAdapter.RecyclerItemClick
        , LoaderManager.LoaderCallbacks<Cursor> {


    private RecyclerView recyclerViewNews;
    private ArrayList<News> lstNews;
    private Toolbar toolbar;
    private String selectedType = NewsType.Recent.name();
    public static final int LOADER_ID = 300;
    public static final String NEWS_ITEM = "news";
    LoaderManager manager;
    Loader<Cursor> loader;

    public HomeFragment() {
        // Required empty public constructor
    }

    private void setLoader() {
        manager = getLoaderManager();
        loader = manager.getLoader(LOADER_ID);
        if (loader == null)
            manager.initLoader(LOADER_ID, null, this);
        else
            manager.restartLoader(LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewNews = (RecyclerView) view.findViewById(R.id.recycler_news);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ToolbarUtils.setToolbar((AppCompatActivity) getActivity(), toolbar
                , false);

        setLoader();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_recent:
                Toast.makeText(getContext(), "recent", Toast.LENGTH_LONG).show();
                selectedType = NewsType.Recent.name();
                setLoader();
                break;
            case R.id.menu_top_rated:
                Toast.makeText(getContext(), "rated", Toast.LENGTH_LONG).show();
                selectedType = NewsType.Top_rated.name();
                setLoader();
                break;
            case R.id.menu_setting:
                Toast.makeText(getContext(), "setting", Toast.LENGTH_LONG).show();
                NavigationManager.navigateToSetting(getActivity());
                break;
            default:
                break;
        }
        return true;
    }

    private void setNewsList() {
        NewsAdapter adapter = null;
        if (!HomeActivity.isTablet)
            adapter = new NewsAdapter(lstNews, this);
        else
            adapter = new NewsAdapter(lstNews, (HomeActivity) getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerViewNews.setLayoutManager(layoutManager);
        recyclerViewNews.setAdapter(adapter);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ((HomeActivity) getActivity()).onItemClick(lstNews.get(0));
            }
        });

    }

    @Override
    public void onItemClick(News news) {

        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra(NEWS_ITEM, news);
        startActivity(intent);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(getContext(), NewsContract.NewsEntry.CONTENT_URI, null,
                NewsContract.NewsEntry.COLUMN_NEWS_TYPE + " = ?", new String[]{selectedType}, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        lstNews = new ArrayList<>();
        News news;
        while (data.moveToNext()) {
            news = new News();

            news.setImgURL(data.getString(data.getColumnIndex
                    (NewsContract.NewsEntry.COLUMN_NEWS_IMAGE_URL)));
            news.setEnTitle(data.getString(data.getColumnIndex
                    (NewsContract.NewsEntry.COLUMN_NEWS_EN_TITLE)));
            news.setArTitle(data.getString(data.getColumnIndex
                    (NewsContract.NewsEntry.COLUMN_NEWS_AR_TITLE)));
            news.setEnShortDescription(data.getString(data.getColumnIndex
                    (NewsContract.NewsEntry.COLUMN_NEWS_EN_SHORT_DESCRIPTION)));
            news.setArShortDescription(data.getString(data.getColumnIndex
                    (NewsContract.NewsEntry.COLUMN_NEWS_AR_SHORT_DESCRIPTION)));
            news.setEnLongDescription(data.getString(data.getColumnIndex
                    (NewsContract.NewsEntry.COLUMN_NEWS_EN_LONG_DESCRIPTION)));
            news.setArLongDescription(data.getString(data.getColumnIndex
                    (NewsContract.NewsEntry.COLUMN_NEWS_AR_LONG_DESCRIPTION)));
            news.setEnDate(data.getString(data.getColumnIndex
                    (NewsContract.NewsEntry.COLUMN_NEWS_EN_DATE)));
            news.setArDate(data.getString(data.getColumnIndex
                    (NewsContract.NewsEntry.COLUMN_NEWS_AR_DATE)));
            news.setLatitude(data.getDouble(data.getColumnIndex
                    (NewsContract.NewsEntry.COLUMN_NEWS_LATITUDE)));
            news.setLongitude(data.getDouble(data.getColumnIndex
                    (NewsContract.NewsEntry.COLUMN_NEWS_LONGITUDE)));
            news.setType(data.getString(data.getColumnIndex
                    (NewsContract.NewsEntry.COLUMN_NEWS_TYPE)));

            lstNews.add(news);

        }
        setNewsList();
        manager.destroyLoader(LOADER_ID);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


    }


}
