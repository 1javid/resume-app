package dao.impl;

import dao.inter.ConnectionAbstract;
import dao.inter.SkillsDaoInter;
import entity.Skills;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillsDaoImpl extends ConnectionAbstract implements SkillsDaoInter {

    private Skills getSkill(ResultSet resultSet) throws Exception {
        int userId = resultSet.getInt("id");
        String name = resultSet.getString("name");

        return new Skills(userId, name);
    }

    @Override
    public List<Skills> getAllSkills() {
        List<Skills> skillsList = new ArrayList<>();
        try(Connection c = connect()){
            Statement statement = c.createStatement();
            statement.execute("select * from skills");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Skills skills = getSkill(resultSet);
                skillsList.add(skills);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return skillsList;
    }

    @Override
    public Skills getById(int id) {
        Skills skills = null;
        try(Connection connection = connect()) {

            PreparedStatement statement = connection.prepareStatement("select name from skills where id=" + id);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                skills = getSkill(resultSet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return skills;
    }

    @Override
    public boolean insertSkills(Skills s) {

            boolean b = true;
            try (Connection conn = connect()){
                PreparedStatement stmt = conn.prepareStatement("insert skills (name) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, s.getName());
                b = stmt.execute();

                ResultSet generatedKeys = stmt.getGeneratedKeys();

                if (generatedKeys.next()) {
                    s.setId(generatedKeys.getInt(1));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                b = false;
            }
            return b;
    }

    @Override
    public boolean updateSkills(Skills s) {
        try(Connection connection = connect()) {

            PreparedStatement statement = connection.prepareStatement("update skills set name=? where id=?");
            statement.setString(1, s.getName());
            statement.setInt(2, s.getId());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSkills(int id) {
        try(Connection connection = connect()) {

            PreparedStatement statement = connection.prepareStatement("delete from skills where id=?");
            statement.setInt(1, id);

            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
