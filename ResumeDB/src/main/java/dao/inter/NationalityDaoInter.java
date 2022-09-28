package dao.inter;

import entity.Country;

import java.util.List;

public interface NationalityDaoInter {

    List<Country> getAllNationalities();

    Country getById(int id);

    boolean updateNationality(Country c);

    boolean deleteNationality(int id);
}
