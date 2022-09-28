package dao.impl;

import dao.inter.ConnectionAbstract;
import dao.inter.CountryDaoInter;
import dao.inter.NationalityDaoInter;
import entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NationalityDaoImpl extends ConnectionAbstract implements NationalityDaoInter {

    private static Country getNationality(ResultSet resultSet) throws Exception {

        int userId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String nationality = resultSet.getString("nationality");

        return new Country(userId, name, nationality);
    }
    @Override
    public List<Country> getAllNationalities() {

        List<Country> countryList = new ArrayList<>();
        try(Connection c = connect()){
            Statement statement = c.createStatement();
            statement.execute("select * from country");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Country countries = getNationality(resultSet);
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

            PreparedStatement statement = connection.prepareStatement("select nationality from country where id=" + id);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                country = getNationality(resultSet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return country;
    }

    @Override
    public boolean updateNationality(Country c) {
        try(Connection connection = connect()) {

            PreparedStatement statement = connection.prepareStatement("update country set nationality=? where id=?");
            statement.setString(1, c.getNationalityName());
            statement.setInt(2, c.getId());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteNationality(int id) {
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
