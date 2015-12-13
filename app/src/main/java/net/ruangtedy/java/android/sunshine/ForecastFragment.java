package net.ruangtedy.java.android.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.ruangtedy.java.android.sunshine.model.Data;
import net.ruangtedy.java.android.sunshine.model.WeatherResult;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
        inflater.inflate(R.menu.forecastfragment, menu);
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
            updateWeather();
            return true;
        }


        return super.onOptionsItemSelected(item);

    }

    private void updateWeather() {
        FetchWeatherTask weatherTask=new FetchWeatherTask();
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        String location=preferences.getString(getString(R.string.pref_location_key),getString(R.string.pref_default_location));
        weatherTask.execute(location);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {




        View rootView=inflater.inflate(R.layout.fragment_main,container,false);
//        String[] data = {
//                "Mon 6/23 - Sunny - 31/17",
//                "Tue 6/24 - Foggy - 21/8",
//                "Wed 6/25 - Cloudy - 22/17",
//                "Thurs 6/26 - Rainy - 18/11",
//                "Fri 6/27 - Foggy - 21/10",
//                "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
//                "Sun 6/29 - Sunny - 20/7"
//        };
//
//
//
//        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));
        mForecastAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (this activity)
                        R.layout.list_item_forecast, // The name of the layout ID.
                        R.id.list_item_forecast_textview, // The ID of the textview to populate.
                        new ArrayList<String>());

        ListView listView=(ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String forecast=mForecastAdapter.getItem(position);
               // Toast.makeText(getActivity(),forecast, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), DetailActivity.class).putExtra(Intent.EXTRA_TEXT,forecast);
                startActivity(intent);
            }
        });


        return rootView;
    }
    public class FetchWeatherTask extends AsyncTask<String, Void, WeatherResult> {
        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();



        private WeatherResult getWeatherDataFromJson(String forecastJsonStr) throws IOException {
            ObjectMapper mapper=new ObjectMapper();
            WeatherResult weather = mapper.readValue(forecastJsonStr,WeatherResult.class);
            Log.v(LOG_TAG,weather.getCity().getName());




            return weather;
        }
        @Override
        protected WeatherResult doInBackground(String... params) {
            //lesson 2
            HttpURLConnection urlConnection=null;
            BufferedReader reader=null;

            String forecastJsonStr=null;
            Log.v(LOG_TAG,"lalala");

            int numDays=7;
            String units="metric";
            try {



                //URL url=new URL("http://api.openweathermap.org/data/2.5/forecast?q=London&appid=2de143494c0b295cca9337e1e96b00e0");
                final String FORECAST_BASE_URL="http://api.openweathermap.org/data/2.5/forecast/daily?";
                final String QUERY_PARAM="q";
                final String APPID_PARAM="APPID";
                final String DAYS_PARAM = "cnt";
                final String PARAM_UNITS="units";

                Uri builtUri= Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .appendQueryParameter(QUERY_PARAM,params[0])
                        .appendQueryParameter(DAYS_PARAM,Integer.toString(numDays))
                        .appendQueryParameter(PARAM_UNITS,units)
                        .appendQueryParameter(APPID_PARAM, "2de143494c0b295cca9337e1e96b00e0")
                        .build();
                Log.v(LOG_TAG,"Built URI"+builtUri);
                URL url=new URL(builtUri.toString());
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

                Log.v(LOG_TAG, "Forecast JSON String" + forecastJsonStr);
                return getWeatherDataFromJson(forecastJsonStr);


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



        }

        @Override
        protected void onPostExecute(WeatherResult weatherResult) {
            if(weatherResult!=null){
                mForecastAdapter.clear();
                for(Data data:weatherResult.getList()){
                    String str=data.getDatestr()+" "+data.getWeather().get(0).getMain();
                    mForecastAdapter.add(str);



                }

            }

        }
    }
}


