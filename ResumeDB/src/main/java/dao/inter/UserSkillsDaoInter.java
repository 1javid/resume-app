package dao.inter;

import entity.Skills;
import entity.UserSkills;

import java.util.List;

public interface UserSkillsDaoInter {

    List<UserSkills> getAllSkillsByUserId(int userId);

    boolean insertUserSkills(UserSkills u);

    boolean updateUserSkills(UserSkills u);

    boolean deleteUserSkills(int id);
}
