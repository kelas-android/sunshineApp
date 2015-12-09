package net.ruangtedy.java.android.sunshine;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {
    ArrayAdapter<String> mForecastAdapter;

    public ForecastFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.forecastfragment,menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_refresh){
            FetchWeatherTask weatherTask=new FetchWeatherTask();
            weatherTask.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {




        View rootView=inflater.inflate(R.layout.fragment_main,container,false);
        String[] data = {
                "Mon 6/23 - Sunny - 31/17",
                "Tue 6/24 - Foggy - 21/8",
                "Wed 6/25 - Cloudy - 22/17",
                "Thurs 6/26 - Rainy - 18/11",
                "Fri 6/27 - Foggy - 21/10",
                "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
                "Sun 6/29 - Sunny - 20/7"
        };



        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));
        mForecastAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (this activity)
                        R.layout.list_item_forecast, // The name of the layout ID.
                        R.id.list_item_forecast_textview, // The ID of the textview to populate.
                        weekForecast);

        ListView listView=(ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);

        return rootView;
    }
    public class FetchWeatherTask extends AsyncTask<Void, Void, Void>{
        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

        @Override
        protected Void doInBackground(Void... params) {
            //lesson 2
            HttpURLConnection urlConnection=null;
            BufferedReader reader=null;

            String forecastJsonStr=null;
            Log.v(LOG_TAG,"lalala");

            try {
                Log.v(LOG_TAG,"0");

                URL url=new URL("http://api.openweathermap.org/data/2.5/forecast?q=London&appid=2de143494c0b295cca9337e1e96b00e0");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                Log.v(LOG_TAG, "1");

                InputStream inputStream=urlConnection.getInputStream();
                StringBuffer buffer=new StringBuffer();
                if(inputStream==null){
                    Log.v(LOG_TAG,"nulll");
                    return null;

                }

                reader=new BufferedReader(new InputStreamReader(inputStream));
                Log.v(LOG_TAG,"2");

                String line;
                while((line=reader.readLine())!=null){
                    buffer.append(line+"\n");
                    Log.v(LOG_TAG, "3");

                }

                if(buffer.length()==0){
                    Log.v(LOG_TAG,"4");

                    return null;
                }
                Log.v(LOG_TAG,"5");

                forecastJsonStr=buffer.toString();
                Log.v(LOG_TAG,"Forecast JSON String"+forecastJsonStr);



            } catch (MalformedURLException e) {
                Log.e("LOG_TAG", "Error ", e);
                return null;
            } catch (ProtocolException e) {
                Log.e("LOG_TAG", "Error ", e);
                return null;
            } catch (IOException e) {
                Log.e("LOG_TAG", "Error ", e);

                return null;
            } finally {
                if(urlConnection!=null){
                    urlConnection.disconnect();
                }
                if(reader!=null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e("LOG_TAG", "Error closing stream", e);
                    }
                }
            }



            return null;
        }
    }
}


