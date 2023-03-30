package DTO;

import java.util.Objects;

public class Airport implements Comparable<Airport>
{
    private int airport_id ;
    private String airport_short_form;
    private String airport_city;
    private String airport_country;


    public Airport(int airport_id, String airport_short_form, String airport_city, String airport_country )
    {
        this.airport_id = airport_id;
        this.airport_short_form = airport_short_form;
        this.airport_city = airport_city;
        this.airport_country = airport_country;
    }

    public Airport( String airport_short_form, String airport_city, String airport_country)
    {
        this.airport_id = 0;
        this.airport_short_form = airport_short_form;
        this.airport_city = airport_city;
        this.airport_country = airport_country;
    }

    public Airport()
    {
    }

    public int getAirport_id()
    {
        return airport_id;
    }

    public void setAirport_id(int airport_id)
    {
        this.airport_id = airport_id;
    }

    public String getAirport_short_form()
    {
        return airport_short_form;
    }

    public void setAirport_short_form(String firstName)
    {
        this.airport_short_form = firstName;
    }

    public String getAirport_city()
    {
        return airport_city;
    }

    public void setAirport_city(String airport_city)
    {
        this.airport_city = airport_city;
    }

    public String getAirport_country()
    {
        return airport_country;
    }

    public void setAirport_country(String airport_country)
    {
        this.airport_country = airport_country;
    }


    @Override
    public String toString()
    {
        return "Airport{" + "Airport Id=" + airport_id + ", airport_short_form=" + airport_short_form + ", airport_city=" +
                airport_city + ", airport_country=" + airport_country +'}';
    }

    @Override
    public  int compareTo(Airport aAirport) {
        return this.getAirport_country().compareTo(aAirport.getAirport_country());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport a = (Airport) o;
        return airport_country == a.airport_country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(airport_country);
    }


}
