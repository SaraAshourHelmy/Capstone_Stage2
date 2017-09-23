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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.db.NewsContract;
import com.education.capstone_stage2.model.News;
import com.education.capstone_stage2.model.NewsDataUtils;
import com.education.capstone_stage2.model.NewsType;
import com.education.capstone_stage2.utils.MyApplication;
import com.education.capstone_stage2.utils.NavigationManager;
import com.education.capstone_stage2.utils.WidgetUtility;
import com.education.capstone_stage2.view.activity.DetailsActivity;
import com.education.capstone_stage2.view.activity.HomeActivity;
import com.education.capstone_stage2.view.adapter.NewsAdapter;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements NewsAdapter.RecyclerItemClick
        , LoaderManager.LoaderCallbacks<Cursor> {


    private RecyclerView recyclerViewNews;
    private ArrayList<News> lstNews;
    private String selectedType = NewsType.Recent.name();
    public static final int LOADER_ID = 300;
    public static final String NEWS_ITEM = "news";
    LoaderManager manager;
    Loader<Cursor> loader;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setLoader();
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

        //setLoader();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_recent:
                selectedType = NewsType.Recent.name();
                setLoader();
                break;
            case R.id.menu_top_rated:
                selectedType = NewsType.Top_rated.name();
                setLoader();
                break;
            case R.id.menu_setting:
                NavigationManager.navigateToSetting(getActivity());
                break;
            default:
                break;
        }
        return true;
    }

    private void setNewsList() {

        if (selectedType.equals(NewsType.Recent.name())) {
            MyApplication.setRecentNews(lstNews);
            WidgetUtility.updateWidget(getContext());
        }
        NewsAdapter adapter = null;
        if (!HomeActivity.isTablet)
            adapter = new NewsAdapter(lstNews, this);
        else
            adapter = new NewsAdapter(lstNews, (HomeActivity) getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerViewNews.setLayoutManager(layoutManager);
        recyclerViewNews.setAdapter(adapter);
        if (HomeActivity.isTablet) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {

                    ((HomeActivity) getActivity()).onItemClick(lstNews.get(0));
                }
            });
        }

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

        lstNews = NewsDataUtils.getNewsLst(data);
        setNewsList();
        manager.destroyLoader(LOADER_ID);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


    }


}
