package dao.inter;

import entity.Country;
import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface CountryDaoInter {

    List<Country> getAllCountries();

    Country getById(int id);

    boolean updateCountry(Country c);

    boolean deleteCountry(int id);
}
