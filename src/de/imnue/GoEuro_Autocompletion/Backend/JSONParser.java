package de.imnue.GoEuro_Autocompletion.Backend;

import android.util.JsonReader;
import de.imnue.GoEuro_Autocompletion.Entities.GeoPosition;
import de.imnue.GoEuro_Autocompletion.Entities.Position;

import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Brian Nürnberg
 * Date: 08.10.2013
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class JSONParser {

     public ArrayList<Position> parseJSON(String jsonString) throws IOException {
         JsonReader jsonReader = null;
         Reader reader = new StringReader(jsonString);
         try {
             jsonReader = new JsonReader(reader);
             return readResults(jsonReader);
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }  finally {
             if (jsonReader != null) {
                jsonReader.close();
             }
         }

         return null;
     }

    private ArrayList<Position> readResults(JsonReader reader) throws IOException {
        ArrayList<Position> positions = new ArrayList<Position>();
        reader.beginObject();
        while (reader.hasNext()) {
            String attribute = reader.nextName();
            if (attribute.equals("results")) {
                positions = readResultsArray(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return positions;
    }

    private ArrayList<Position> readResultsArray(JsonReader reader) throws IOException {
        ArrayList<Position> positions = new ArrayList<Position>();
        reader.beginArray();
        while (reader.hasNext()) {
            positions.add(readPosition(reader));
        }
        reader.endArray();
        return positions;
    }

    private Position readPosition(JsonReader reader) throws IOException {
        String _type = null;
        long _id = -1;
        String name = null;
        String type = null;
        GeoPosition geo_Position = null;

         reader.beginObject();
        while (reader.hasNext()) {
            String attribute = reader.nextName();
            if (attribute.equals("_type")) {
                _type = reader.nextString();
            } else if (attribute.equals("_id")) {
               _id = reader.nextLong();
            } else if (attribute.equals("name")) {
                name = reader.nextString();
            } else if (attribute.equals("type")) {
                type = reader.nextString();
            } else if (attribute.equals("geo_position")) {
                geo_Position = readGeoPosition(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Position(_type, _id, name, type, geo_Position);
    }

    private GeoPosition readGeoPosition(JsonReader reader) throws IOException {
        double latitude = 0.0;
        double longitude = 0.0;

        reader.beginObject();
        while (reader.hasNext()) {
            String attribute = reader.nextName();
            if(attribute.equals("latitude")) {
                latitude = reader.nextDouble();
            } else if (attribute.equals("longitude")) {
                longitude = reader.nextDouble();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new GeoPosition(latitude, longitude);
    }
}
