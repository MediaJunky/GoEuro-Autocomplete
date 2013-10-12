package de.imnue.GoEuro_Autocompletion.Backend;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import de.imnue.GoEuro_Autocompletion.AutoCompleteResponse;
import de.imnue.GoEuro_Autocompletion.Entities.Position;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Brian Nürnberg
 * Date: 08.10.2013
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class AutoCompleteSuggestions extends AsyncTask<String, Void, String> {
    public AutoCompleteResponse delegate = null;
    public AutoCompleteTextView textView = null;


    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(30000);
            connection.setConnectTimeout(35000);
            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            if (response == 200) {
                Log.d("DEBUG_BACKEND", "There is a valid response: " + response);
                inputStream = connection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String readLine;
                while ((readLine = bufferedReader.readLine()) != null ) {
                     stringBuilder.append(readLine);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            ArrayList<Position> positions = new JSONParser().parseJSON(s);
            Log.d("DEBUG_BACKEND", "PositionsArray is: " + positions);
            delegate.backendReponseFinish(positions, textView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG_BACKEND", "Response is: " + s);
    }
}
