package com.example.artistrygallery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<String> {

    private EditText mInput;
    private TextView mTitleText;
    private TextView mArtistText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mInput = findViewById(R.id.input);
        mTitleText = findViewById(R.id.titleText);
        mArtistText = findViewById(R.id.artistText);

        if(getSupportLoaderManager().getLoader(0) != null){
            Log.i("Natasha", "initLoader() from onCreate in Searchl.");
            getSupportLoaderManager().initLoader(0,null, this);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.art) {
                    startActivity(new Intent(Search.this, Art.class));
                    return true;
                } else if (id == R.id.welcome) {
                    startActivity(new Intent(Search.this, Welcome.class));
                    return true;
                } else if (id == R.id.search) {
                    startActivity(new Intent(Search.this, Search.class));
                    return true;
                }

                return false;
            }

        });
    }

    public void searchArt(View view) {
        // Get the search string from the input field.
        String queryString = mInput.getText().toString();

        // Hide the keyboard when the button is pushed.
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        // If the network is available, connected, and the search field
        // is not empty, start a BookLoader AsyncTask.
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {

            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            Log.i("Natasha","restartLoader() from Search");
            getSupportLoaderManager().restartLoader(0, queryBundle, this);

            mArtistText.setText("");
            mTitleText.setText(R.string.loading);
        }
        // Otherwise update the TextView to tell the user there is no
        // connection, or no search term.
        else {
            if (queryString.length() == 0) {
                mArtistText.setText("");
                mTitleText.setText(R.string.no_search_term);
            } else {
                mArtistText.setText("");
                mTitleText.setText(R.string.no_network);
            }
        }
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";

        if (args != null) {
            queryString = args.getString("queryString");
        }
        Log.i("Natasha","new BookLoader() from onCreateLoader in Search");
        return new ArtLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("data");
            int i=0;
            String title="";
            String artist="";

            while(i<itemsArray.length()){
                JSONObject info = itemsArray.getJSONObject(i);

                try{
                    title = info.getString("title");
                    artist = info.getString("artistName");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Move to the next item.
                i++;
            }

            // If both are found, display the result.
            if (title != null && artist != null) {
                mTitleText.setText(title);
                mArtistText.setText(artist);
            } else {
                mTitleText.setText(R.string.no_results);
                mArtistText.setText("");
            }

        } catch (Exception e) {
            mTitleText.setText(R.string.no_results);
            mArtistText.setText("");
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.i("Natasha","onLoaderReset() from Search");
        // Do nothing.  Required by interface.
    }
}
