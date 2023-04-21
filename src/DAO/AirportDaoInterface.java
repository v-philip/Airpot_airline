package DAO;

import DTO.Airport;
import Exception.DaoException;
import java.util.List;

public interface AirportDaoInterface {
    public List<Airport> FindAllAirports() throws DaoException;

    public int deleteById (int id) throws DaoException;

    public Airport findById(int id)throws DaoException;
    public Airport InsertAirport(Airport a)throws DaoException;
    public List<Airport> filterByCountry(List<Airport>a, String country  )throws DaoException;

    public String FindAllAirportsJson() throws DaoException;
}
