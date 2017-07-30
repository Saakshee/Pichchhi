package com.example.sakshi.shree;

/**
 * Created by sakshi on 7/24/2017.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sakshi on 7/24/2017.
 */

public class NewsJsonParser {

    public static List<News> parsefeed(String content) {
        List<News> newsList = new ArrayList<>();
        News news = new News();
        try {
            JSONObject res = new JSONObject(content);
            if (res.getString("result").equals("Ok")) {
                JSONArray ar = res.getJSONArray("news");
                for (int i = 0; i < ar.length(); i++) {
                    JSONObject obj = ar.getJSONObject(i);
                    news.setHeading(obj.getString("heading"));
                    news.setDescription(obj.getString("description"));
                    news.setUrl(obj.getString("url"));
                    news.setBanner(obj.getString("banner"));
                    newsList.add(news);
                }
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return newsList;
    }


}
