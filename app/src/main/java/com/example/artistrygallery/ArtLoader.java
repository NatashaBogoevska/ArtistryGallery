package com.example.artistrygallery;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class ArtLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    ArtLoader(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.i("Natasha","forceLoad() from onStartLoading in ArtLoader");

        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {

        Log.i("Natasha","sleep() from loadInBackground in ArtLoader");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("Natasha","getBookInfo() from loadInBackground in ArtLoader");
        return NetworkUtils.getArtInfo(mQueryString);
    }
}
