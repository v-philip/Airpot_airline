package DAO;

import Client.Request;
import DTO.Airport;
import DTO.Arrival;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Exception.DaoException;

import javax.swing.*;

public class MySqlArrivalDao extends MySqlDao implements ArrivalDaoInterface {

    HashSet<Integer> airportIDs = new HashSet<Integer>();

    @Override
    public List<Arrival> FindAllArrival() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Arrival> arrival_schedule = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM arrival_schedule";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int gate_id = resultSet.getInt("gate_id");
//                airportIDs.add(airport_id);
                String flight_id = resultSet.getString("flight_id");
                String airport_id = resultSet.getString("gate_id");
                java.sql.Time arrival_time = resultSet.getTime("arrival_time");
                Arrival a = new Arrival(gate_id, flight_id, gate_id, arrival_time);
                arrival_schedule.add(a);

            }
        } catch (SQLException e) {
            throw new DaoException("findAllSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return arrival_schedule;     //Did not test yet
    }

    @Override
    public String deleteById(String id) throws DaoException {

        Arrival a = findByFightId(id);


        Connection connection = null;
        PreparedStatement ps = null;
        int updated = 0;
        try {
            connection = this.getConnection();
            String query = "DELETE FROM arrival_schedule WHERE flight_id = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, id);

            updated = ps.executeUpdate();

        } catch (SQLException e) {

            throw new DaoException("deleteById() " + e.getMessage());

        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("deleteById() " + e.getMessage());
            }
        }

        if (updated == 1) {
            return a.toString() + "has been deleted";
        } else
            return "there has been an error";
    }

    @Override
    public Arrival findByFightId(String id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Arrival a = null;
        try {
            connection = this.getConnection();

            String query = "SELECT * FROM arrival_schedule WHERE Flight_id = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, id);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();

            while (rs.next()) {

                int gate_id = rs.getInt("gate_id");
//                airportIDs.add(airport_id);
                String flight_id = rs.getString("flight_id");
                String airport_id = rs.getString("gate_id");
                java.sql.Time arrival_time = rs.getTime("arrival_time");
                a = new Arrival(gate_id, flight_id, gate_id, arrival_time);


            }

        } catch (SQLException e) {
            throw new DaoException("findAllSet() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAirportById () " + e.getMessage());
            }
        }
        return a;
    }

    @Override
    public Arrival InsertArrival(Arrival a) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        int change = 0;
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            String query = "insert into arrival_schedule (gate_id, flight_id, airport_id, Arrival_time) values(?,?,?,?);";
            //code form https://docs.oracle.com/
//            ps = connection.prepareStatement(query, Statement.);
            ps.setString(1, a.getFlight_id());
            ps.setInt(2, a.getAirport_id());
            ps.setTime(3, a.getArrival_time());
            change = ps.executeUpdate();

//            try (ResultSet keys = ps.getGeneratedKeys()) {
//                ps(keys.next()).isTrue();
//                ps(keys.getLong(1)).isGreaterThanOrEqualTo(1);
//            }
//            if (change !=0){
//                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
//                    if (generatedKeys.next()) {
//                        a.setAirport_id(generatedKeys.getInt(1));
//                    }
//                }
//            }
//            rs = ps.getGeneratedKeys();
//            a.setAirport_id(rs.getInt(1)) ;

        } catch (SQLException e) {
            throw new DaoException("deleteById() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("Inseret" + e.getMessage());
            }
        }

        return a;
    }

    @Override
//function to filter by country
    public List<Arrival> filterByTime(java.sql.Time time) throws DaoException {

        List<Arrival> a = FindAllArrival();
        List<Arrival> result = new ArrayList<>();
        for (Arrival res : a) {
            if (time.compareTo(res.getArrival_time()) == 0) {
                result.add(res);
            }
        }

        return result;
    }

    /**
     * Gets all the recipes in the database in JSON String format.
     *
     * @return A JSON String containing all the recipes in the database
     * @throws DaoException Extends SQLException
     */
    @Override
    public String FindAllArrivalJson() throws DaoException {
        List<Arrival> arrival_schedule = FindAllArrival();

        if (arrival_schedule == null || arrival_schedule.isEmpty()) return null;

        Gson gsonParser = new Gson();
        return gsonParser.toJson(arrival_schedule);
    }

    @Override
    public String FindArrivalByIdJson(String id) throws DaoException {
        Arrival flight = findByFightId(id);

        if (flight == null) return null;

        Gson gsonParser = new Gson();
        return gsonParser.toJson(flight);
    }

    @Override
    public String DeleteByIdJson(String id) throws DaoException {
        String Message = deleteById(id);

        Gson gsonParse = new Gson();
        return gsonParse.toJson(Message);
    }

    @Override
    public String ArrivalSorted() throws DaoException {

        List<Arrival> a = FindAllArrival();

        a.sort(Arrival::compareTo);
        Gson gsonParser = new Gson();
        return gsonParser.toJson(a);

    }

    @Override
    public String ArrivingFrom(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<String> FlightList = new ArrayList<>();
        String result = "";

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT airports.airport_city, arrival_schedule.*\n" +
                    "FROM airports\n" +
                    "JOIN arrival_schedule\n" +
                    "ON airports.airport_id = arrival_schedule.airport_id WHERE  airports.airport_id = ?;";
            ps = connection.prepareStatement(query);
            ps.setString(1, Integer.toString(id));


            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String city = resultSet.getString("Airport_city");
                int gate_id = resultSet.getInt("gate_id");

                String flight_id = resultSet.getString("flight_id");
                String airport_id2 = resultSet.getString("gate_id");
                java.sql.Time arrival_time = resultSet.getTime("arrival_time");
                result = "City :" + city + " Flight id:" + flight_id + "  Time:" + arrival_time;


            }
        } catch (SQLException e) {
            throw new DaoException("findAllSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }

        Gson gsonParse = new Gson();
        return gsonParse.toJson(result);     //Did not test yet
    }
}



