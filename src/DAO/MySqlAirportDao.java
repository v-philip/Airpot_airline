package DAO;

import DTO.Airport;
import Exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MySqlAirportDao extends MySqlDao implements AirportDaoInterface
{

    HashSet<Integer> airportIDs= new HashSet<Integer>();
    @Override
    public List<Airport> FindAllAirports() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Airport> airportList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM Airports";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int airport_id = resultSet.getInt("airport_id");
                airportIDs.add(airport_id);
                String airport_short_form = resultSet.getString("airport_short_form");
                String airport_city = resultSet.getString("airport_city");
                String airport_country = resultSet.getString("airport_country");
                Airport a = new Airport(airport_id, airport_short_form, airport_city, airport_country);
                airportList.add(a);

            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }

        return airportList;     //Did not test yet
    }

    @Override
    public int deleteById(int id) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        int updated = 0;
        try
        {
            connection = this.getConnection();
            String query = "DELETE FROM Airports WHERE airport_id = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1,Integer.toString(id));

            updated = ps.executeUpdate();

        }
        catch (SQLException e)
        {
            throw new DaoException("deleteById() " + e.getMessage());
        }
        finally
        {
            try
            {

                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            }
            catch (SQLException e)
            {
                throw new DaoException("deleteById() " + e.getMessage());
            }
        }

        return updated;
    }

    @Override
    public Airport findById(int id ) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Airport a = null;
        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM Airports WHERE airport_id = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1,Integer.toString(id));

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();

            while (rs.next())
            {

                int airport_id = rs.getInt("airport_id");
                String airport_short_form = rs.getString("airport_short_form");
                String airport_city = rs.getString("airport_city");
                String airport_country = rs.getString("airport_country");
                a = new Airport(airport_id, airport_short_form, airport_city, airport_country);

            }

        }
        catch(SQLException e)
        {
            throw new DaoException("findAllSet() " + e.getMessage());
        }

        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAirportById () " + e.getMessage());
            }
        }
        return a;
    }

    @Override
    public Airport InsertAirport (Airport a) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        int change = 0;
        ResultSet rs = null;
        try
        {
            connection = this.getConnection();
            String query = "insert into airports (airport_id, airport_short_form, airport_city, airport_country) values(NULL,?,?,?);";
            //code form https://docs.oracle.com/
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,a.getAirport_short_form());
            ps.setString(2,a.getAirport_city());
            ps.setString(3,a.getAirport_country());
            change = ps.executeUpdate();

//            try (ResultSet keys = ps.getGeneratedKeys()) {
//                ps(keys.next()).isTrue();
//                ps(keys.getLong(1)).isGreaterThanOrEqualTo(1);
//            }
            if (change !=0){
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    a.setAirport_id(generatedKeys.getInt(1));
                }
            }
            }
//            rs = ps.getGeneratedKeys();
//            a.setAirport_id(rs.getInt(1)) ;

        }
        catch (SQLException e)
        {
            throw new DaoException("deleteById() " + e.getMessage());
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            }
            catch (SQLException e)
            {
                throw new DaoException("Inseret" + e.getMessage());
            }
        }

        return a;
    }

    @Override
//function to filter by country
    public List<Airport> filterByCountry(List<Airport> a, String country){

        List<Airport>result = new ArrayList<>();
        for(Airport res : a)
        {
            if (country.compareTo(res.getAirport_country())==0){
                result.add(res);
            }
        }

        return result;
    }

    @Override
//function to filter by country
    public List<Airport> filterByCountry( String country) throws DaoException {
        List<Airport> a = FindAllAirports();

        List<Airport>result = new ArrayList<>();
        for(Airport res : a)
        {
            if (country.compareTo(res.getAirport_country())==0){
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
    public String FindAllAirportsJson() throws DaoException
    {
        List<Airport> airportList = FindAllAirports();

        if(airportList == null || airportList.isEmpty()) return null;

        Gson gsonParser = new Gson();
        return gsonParser.toJson(airportList);
    }
    @Override
    public String FindAirportIdsJson(int id) throws DaoException
    {
        Airport airport =findById(id);

        if(airport == null ) return null;

        Gson gsonParser = new Gson();
        return gsonParser.toJson(airport);
    }

    @Override
    public String filterByCountryJson(String country)throws DaoException
    {

        List<Airport>  a = filterByCountry(country);
        if(a == null ) return null;

        Gson gsonParser = new Gson();
        return gsonParser.toJson(a);

    }
}
