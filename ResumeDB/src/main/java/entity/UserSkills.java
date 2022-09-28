package entity;

public class UserSkills {
    private Integer id;
    private User user;
    private Skills skills;
    private int power;

    public UserSkills() {
    }

    public UserSkills(Integer id, User user, Skills skills, int power) {
        this.id = id;
        this.user = user;
        this.skills = skills;
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "UserSkills{" +
                "id=" + id +
                ", user=" + user +
                ", skills=" + skills +
                ", power=" + power +
                '}';
    }
}
