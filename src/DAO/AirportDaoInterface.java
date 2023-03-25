package DAO;

import DTO.Airport;
import Exception.DaoException;
import java.util.List;

public interface AirportDaoInterface {
    public List<Airport> FindAllAirports() throws DaoException;

    public int deleteById (int id) throws DaoException;

    public Airport findById(int id)throws DaoException;

}
