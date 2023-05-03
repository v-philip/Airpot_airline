package DTO;

import java.sql.Time;
import java.util.Objects;

public class Departure implements Comparable<Departure> {

    private int gate_id ;
    private String flight_id;
    private int airport_id;
    private java.sql.Time arrival_time;


    public Departure(int gate_id,  String flight_id, int airport_id,java.sql.Time arrival_time )
    {
        this.gate_id = gate_id;
        this.flight_id = flight_id;
        this.airport_id = airport_id;
        this.arrival_time = arrival_time;
    }



    public Departure()
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

    public int getGate_id() {
        return gate_id;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public Time getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Time arrival_time) {
        this.arrival_time = arrival_time;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    public void setGate_id(int gate_id) {
        this.gate_id = gate_id;
    }

    @Override
    public String toString() {
        return "Departure{" +
                "gate_id=" + gate_id +
                ", flight_id='" + flight_id + '\'' +
                ", airport_id=" + airport_id +
                ", arrival_time=" + arrival_time +
                '}';
    }


    @Override
    public  int compareTo(Departure departure1) {
        java.time.LocalTime time1 =   this.getArrival_time().toLocalTime();
        java.time.LocalTime time2 =   departure1.getArrival_time().toLocalTime();
        return   time1.compareTo(time2);

    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Departure a = (Departure) o;
//        return arrival == a.airport_country;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(airport_country);
//    }
}
