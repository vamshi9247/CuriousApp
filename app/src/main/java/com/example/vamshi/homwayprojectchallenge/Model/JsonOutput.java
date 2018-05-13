package com.example.vamshi.homwayprojectchallenge.Model;

import java.util.ArrayList;
import java.util.List;

public class JsonOutput {

private Meta meta;
private Response response;


    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}

 class Meta
{
    private int code;

    private String requestId;

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setRequestId(String requestId){
        this.requestId = requestId;
    }
    public String getRequestId(){
        return this.requestId;
    }
}

class Response
{
    private List<Venues> venues;

    public void setVenues(List<Venues> venues){
        this.venues = venues;
    }
    public List<Venues> getVenues(){
        return this.venues;
    }
}

 class LabeledLatLngs
{
    private String label;

    private double lat;

    private double lng;

    public void setLabel(String label){
        this.label = label;
    }
    public String getLabel(){
        return this.label;
    }
    public void setLat(double lat){
        this.lat = lat;
    }
    public double getLat(){
        return this.lat;
    }
    public void setLng(double lng){
        this.lng = lng;
    }
    public double getLng(){
        return this.lng;
    }
}


 class Location
{
    private String address;

    private String crossStreet;

    private double lat;

    private double lng;

    private List<LabeledLatLngs> labeledLatLngs;

    private int distance;

    private String postalCode;

    private String cc;

    private String city;

    private String state;

    private String country;

    private ArrayList<String> formattedAddress;

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void setCrossStreet(String crossStreet){
        this.crossStreet = crossStreet;
    }
    public String getCrossStreet(){
        return this.crossStreet;
    }
    public void setLat(double lat){
        this.lat = lat;
    }
    public double getLat(){
        return this.lat;
    }
    public void setLng(double lng){
        this.lng = lng;
    }
    public double getLng(){
        return this.lng;
    }
    public void setLabeledLatLngs(List<LabeledLatLngs> labeledLatLngs){
        this.labeledLatLngs = labeledLatLngs;
    }
    public List<LabeledLatLngs> getLabeledLatLngs(){
        return this.labeledLatLngs;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
    public int getDistance(){
        return this.distance;
    }
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    public String getPostalCode(){
        return this.postalCode;
    }
    public void setCc(String cc){
        this.cc = cc;
    }
    public String getCc(){
        return this.cc;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return this.country;
    }
    public void setFormattedAddress(ArrayList<String> formattedAddress){
        this.formattedAddress = formattedAddress;
    }
    public ArrayList<String> getFormattedAddress(){
        return this.formattedAddress;
    }
}


 class Icon
{
    private String prefix;

    private String suffix;

    public void setPrefix(String prefix){
        this.prefix = prefix;
    }
    public String getPrefix(){
        return this.prefix;
    }
    public void setSuffix(String suffix){
        this.suffix = suffix;
    }
    public String getSuffix(){
        return this.suffix;
    }
}


 class Categories
{
    private String id;

    private String name;

    private String pluralName;

    private String shortName;

    private Icon icon;

    private boolean primary;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPluralName(String pluralName){
        this.pluralName = pluralName;
    }
    public String getPluralName(){
        return this.pluralName;
    }
    public void setShortName(String shortName){
        this.shortName = shortName;
    }
    public String getShortName(){
        return this.shortName;
    }
    public void setIcon(Icon icon){
        this.icon = icon;
    }
    public Icon getIcon(){
        return this.icon;
    }
    public void setPrimary(boolean primary){
        this.primary = primary;
    }
    public boolean getPrimary(){
        return this.primary;
    }
}

 class VenuePage
{
    private String id;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
}


 class Venues
{
    private String id;

    private String name;

    private Location location;

    private List<Categories> categories;

    private VenuePage venuePage;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setLocation(Location location){
        this.location = location;
    }
    public Location getLocation(){
        return this.location;
    }
    public void setCategories(List<Categories> categories){
        this.categories = categories;
    }
    public List<Categories> getCategories(){
        return this.categories;
    }
    public void setVenuePage(VenuePage venuePage){
        this.venuePage = venuePage;
    }
    public VenuePage getVenuePage(){
        return this.venuePage;
    }
}




 class Root
{
    private Meta meta;

    private Response response;

    public void setMeta(Meta meta){
        this.meta = meta;
    }
    public Meta getMeta(){
        return this.meta;
    }
    public void setResponse(Response response){
        this.response = response;
    }
    public Response getResponse(){
        return this.response;
    }
}
