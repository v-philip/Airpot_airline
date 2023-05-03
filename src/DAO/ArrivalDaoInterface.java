package DAO;


import DTO.Airport;
import DTO.Arrival;
import Exception.DaoException;
import java.util.List;

public interface ArrivalDaoInterface {
    public List<Arrival> FindAllArrival() throws DaoException;

    public String deleteById (String id) throws DaoException;

    public Arrival findByFightId(String id)throws DaoException;
    public Arrival InsertArrival(Arrival a)throws DaoException;
    public List<Arrival> filterByTime (java.sql.Time time)throws DaoException;

    public String FindAllArrivalJson() throws DaoException;
    public String FindArrivalByIdJson(String id) throws DaoException;
    public String DeleteByIdJson(String id) throws DaoException;
    public String ArrivalSorted()throws DaoException;
    public String ArrivingFrom(int id)throws DaoException;

}
