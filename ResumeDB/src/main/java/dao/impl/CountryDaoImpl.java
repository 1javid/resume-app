package dao.impl;

import dao.inter.ConnectionAbstract;
import dao.inter.CountryDaoInter;
import entity.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends ConnectionAbstract implements CountryDaoInter {

    private static Country getCountry(ResultSet resultSet) throws Exception {

        int userId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String nationality = resultSet.getString("nationality");
        return new Country(userId, name, nationality);
    }
    @Override
    public List<Country> getAllCountries() {

        List<Country> countryList = new ArrayList<>();
        try(Connection c = connect()){
            Statement statement = c.createStatement();
            statement.execute("select * from country");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Country countries = getCountry(resultSet);
                countryList.add(countries);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return countryList;
    }

    @Override
    public Country getById(int id) {
        Country country = null;
        try(Connection connection = connect()) {

            PreparedStatement statement = connection.prepareStatement("select name from country where id=" + id);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                country = getCountry(resultSet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return country;
    }

    @Override
    public boolean updateCountry(Country c) {
        try(Connection connection = connect()) {

            PreparedStatement statement = connection.prepareStatement("update country set name=? where id=?");
            statement.setString(1, c.getName());
            statement.setInt(2, c.getId());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCountry(int id) {
        try(Connection connection = connect()) {

            PreparedStatement statement = connection.prepareStatement("delete from country where id=?");
            statement.setInt(1, id);

            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }


}
