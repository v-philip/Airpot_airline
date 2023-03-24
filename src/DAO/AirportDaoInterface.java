package DAO;

import DTO.Airport;
import Exception.DaoException;
import java.util.List;

public interface AirportDaoInterface {
    public List<Airport> FindAllAirports() throws DaoException;

//    public Airport () throws DaoException;

}
