package dao.inter;

import entity.Country;

import java.util.List;

public interface CountryDaoInter {

    List<Country> getAllCountries();

    Country getById(int id);

    boolean updateCountry(Country c);

    boolean deleteCountry(int id);
}
