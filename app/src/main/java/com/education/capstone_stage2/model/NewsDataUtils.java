package com.education.capstone_stage2.model;

import java.util.ArrayList;

/**
 * Created by Sara on 9/15/2017.
 */

public class NewsDataUtils {

    public static ArrayList<News> getNews() {
        ArrayList<News> listNews = new ArrayList<>();
        News news = new News();
        news.setEnTitle("Test Title1");
        news.setArTitle("الخبر الاول");
        news.setEnShortDescription("Short Description of news 1");
        news.setArShortDescription("وصف للخبر الاول ");
        news.setEnLongDescription("Long Description of news 1");
        news.setArLongDescription("وصف مطول للخبر الاول ");
        news.setEnDate(" 12 feb 2017");
        news.setArDate(" 12 فبراير عام 2017");
        news.setImgURL("");
        news.setLatitude(30.0270233);
        news.setLongitude(31.4205552);
        news.setType(NewsType.Recent);
        listNews.add(news);

        //---------------------------------------
        news = new News();
        news.setEnTitle("Test Title2");
        news.setArTitle("الخبر الثاني");
        news.setEnShortDescription("Short Description of news 2");
        news.setArShortDescription("وصف للخبر الثاني ");
        news.setEnLongDescription("Long Description of news 2");
        news.setArLongDescription("وصف مطول للخبر الثاني ");
        news.setEnDate(" 13 feb 2017");
        news.setArDate(" 13 فبراير عام 2017");
        news.setImgURL("");
        news.setLatitude(30.0270233);
        news.setLongitude(31.4205552);
        news.setType(NewsType.Recent);
        listNews.add(news);

        //----------------------------------------

        news = new News();
        news.setEnTitle("Test Title3");
        news.setArTitle("الخبر الثالث");
        news.setEnShortDescription("Short Description of news 3");
        news.setArShortDescription("وصف للخبر الثالث ");
        news.setEnLongDescription("Long Description of news 3");
        news.setArLongDescription("وصف مطول للخبر الثالث ");
        news.setEnDate(" 14 feb 2017");
        news.setArDate(" 14 فبراير عام 2017");
        news.setImgURL("");
        news.setLatitude(30.0270233);
        news.setLongitude(31.4205552);
        news.setType(NewsType.Top_rated);
        listNews.add(news);

        return listNews;
    }
}
