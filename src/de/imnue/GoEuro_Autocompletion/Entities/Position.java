package de.imnue.GoEuro_Autocompletion.Entities;

/**
 * Created with IntelliJ IDEA.
 * User: Brian Nürnberg
 * Date: 08.10.2013
 * Time: 11:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class Position {
    private String _type;
    private long _id;
    private String name;
    private String type;
    private GeoPosition geo_Position;

    public Position(String _type, long _id, String name, String type, GeoPosition geo_Position) {
        this._type = _type;
        this._id = _id;
        this.name = name;
        this.type = type;
        this.geo_Position = geo_Position;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public GeoPosition getGeo_Position() {
        return geo_Position;
    }

    public void setGeo_Position(GeoPosition geo_Position) {
        this.geo_Position = geo_Position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
