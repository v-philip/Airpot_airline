
import java.sql.*;
import java.util.List;

import DAO.AirportDaoInterface;
import DAO.MySqlAirportDao;
import DTO.Airport;
import Exception.DaoException;
public class App {
    public static void main(String[] args) {

        App app = new App();
        app.start();

    }

    public void start(){
        AirportDaoInterface IAirportDao = new MySqlAirportDao();

        try{

            System.out.println("\nCall findAllAirport()");
            List<Airport> airports = IAirportDao.FindAllAirports();
            for (Airport airport : airports)
                System.out.println("Airport: " + airports.toString());

            System.out.println("\nCall: findById()");
            int id = 2;
            Airport airport = IAirportDao.findById(id);
            System.out.println("Airport found \n"+ airport);

//            System.out.println("\nCall: deleteById()");
//            int i = IAirportDao.deleteById(id);

            System.out.println("\n call: InsertAirport(a)");
            Airport airport1 = new Airport ("tes","test","Test");
            Airport airport2 = IAirportDao.InsertAirport(airport1);
            System.out.println(airport2);


        }


        catch(DaoException e) {
            e.printStackTrace();
        }
    }
}