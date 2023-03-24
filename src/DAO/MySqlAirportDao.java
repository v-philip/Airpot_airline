package DAO;

import DTO.Airport;
import Exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySqlAirportDao extends MySqlDao implements AirportDaoInterface
{

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

            String query = "SELECT * FROM Airport";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {

                int airport_id = resultSet.getInt("airport_id");
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


}
