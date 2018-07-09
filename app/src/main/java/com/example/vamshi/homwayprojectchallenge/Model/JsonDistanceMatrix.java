package com.example.vamshi.homwayprojectchallenge.Model;

import java.util.ArrayList;

public class JsonDistanceMatrix {

    private ArrayList<String> destination_addresses;

    public ArrayList<String> getDestinationAddresses() { return this.destination_addresses; }

    public void setDestinationAddresses(ArrayList<String> destination_addresses) { this.destination_addresses = destination_addresses; }

    private ArrayList<String> origin_addresses;

    public ArrayList<String> getOriginAddresses() { return this.origin_addresses; }

    public void setOriginAddresses(ArrayList<String> origin_addresses) { this.origin_addresses = origin_addresses; }

    private ArrayList<Row> rows;

    public ArrayList<Row> getRows() { return this.rows; }

    public void setRows(ArrayList<Row> rows) { this.rows = rows; }

    private String status;

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }

}

 class Row
{
    private ArrayList<Element> elements;

    public ArrayList<Element> getElements() { return this.elements; }

    public void setElements(ArrayList<Element> elements) { this.elements = elements; }
}

class Element
{
    private Distance distance;

    public Distance getDistance() { return this.distance; }

    public void setDistance(Distance distance) { this.distance = distance; }

    private Duration duration;

    public Duration getDuration() { return this.duration; }

    public void setDuration(Duration duration) { this.duration = duration; }

    private String status;

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }
}

 class Duration
{
    private String text;

    public String getText() { return this.text; }

    public void setText(String text) { this.text = text; }

    private int value;

    public int getValue() { return this.value; }

    public void setValue(int value) { this.value = value; }
}

 class Distance
{
    private String text;

    public String getText() { return this.text; }

    public void setText(String text) { this.text = text; }

    private int value;

    public int getValue() { return this.value; }

    public void setValue(int value) { this.value = value; }
}
