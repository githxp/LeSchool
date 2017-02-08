package com.hxp.leschool.model.opt;

import android.util.Log;

import com.hxp.leschool.model.bean.ScoreModel;
import com.hxp.leschool.viewmodel.ScoreViewModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by hxp on 17-2-8.
 */

public class ScoreModelOpt {

    private ScoreViewModel mScoreViewModel;
    private ScoreModel mScoreModel;
    public ArrayList<ScoreModel> mData = new ArrayList<>();
    private ScoreModelOptCallback mScoreModelOptCallback;

    public ScoreModelOpt(ScoreViewModel scoreViewModel) {
        this.mScoreViewModel = scoreViewModel;
        mScoreModelOptCallback = scoreViewModel;
    }

    public void get(String response) {
        Document doc = Jsoup.parse(response);
        Elements e = doc.select(":contains(学年学期)");
        for (int i=0;i<e.size();i++){
            Log.d("fragment","e:"+e.get(i).text());
        }

    }

    public void refresh() {

    }

    public int getCount() {
        return mData.size();
    }

    public interface ScoreModelOptCallback {
        void refresh();
    }
}
