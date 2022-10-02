package dao.impl;

import dao.inter.ConnectionAbstract;
import dao.inter.UserSkillsDaoInter;
import entity.Skills;
import entity.User;
import entity.UserSkills;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillsDaoImpl extends ConnectionAbstract implements UserSkillsDaoInter {

    private UserSkills getUserSkills(ResultSet resultSet) throws Exception {
        int userId = resultSet.getInt("id");
        int userSkillsId = resultSet.getInt("user_skills_id");
        int skillsId = resultSet.getInt("skills_id");
        String skillName = resultSet.getString("skills_name");
        int power = resultSet.getInt("power");

        return new UserSkills(userSkillsId, new User(userId), new Skills(skillsId, skillName), power);
    }

    @Override
    public List<UserSkills> getAllSkillsByUserId(int userId) {
        List<UserSkills> userList = new ArrayList<>();
        try(Connection c = connect()){
            PreparedStatement statement = c.prepareStatement("SELECT "
                    + " us.id as user_skills_id, "
                    + " u.*,"
                    + " us.skills_id,s.name AS skills_name ,"
                    + " us.power "
                    + " FROM "
                    + " user_skills us "
                    + " LEFT JOIN user u ON us.user_id=u.id "
                    + " LEFT JOIN skills s ON us.skills_id=s.id "
                    + " WHERE us.user_id = ? ");
            statement.setInt(1, userId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                UserSkills user = getUserSkills(resultSet);
                userList.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return userList;
    }

    @Override
    public boolean insertUserSkills(UserSkills u) {
        try(Connection connection = connect()) {

            PreparedStatement fkStatement = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0;");
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO user_skills (user_id, skills_id, power) VALUES (?, ?, ?);");

            stmt.setInt(1, u.getUser().getId());
            stmt.setInt(2, u.getSkills().getId());
            stmt.setInt(3, u.getPower());

            fkStatement.execute();
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUserSkills(UserSkills u) {
        try(Connection connection = connect()) {

            PreparedStatement statement = connection.prepareStatement("update user_skills set skills_id=?, user_id=?, power=? where id=?");
            statement.setInt(1, u.getSkills().getId());
            statement.setInt(2, u.getUser().getId());
            statement.setInt(3, u.getPower());
            statement.setInt(4, u.getId());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUserSkills(int id) {
        try(Connection connection = connect()) {

            PreparedStatement statement = connection.prepareStatement("delete from user_skills where id=?");
            statement.setInt(1, id);

            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
