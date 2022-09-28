package entity;

import java.util.Date;
import java.util.List;

public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String profileDesc;
    private String address;
    private Date birthdate;
    private Country birthplace;
    private Country nationality;
    private List<UserSkills> skills;

    public User() {

    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String surname, String email, String phone, String profileDesc, String address, Date birthdate, Country birthplace, Country nationality) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.profileDesc = profileDesc;
        this.address = address;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Country getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(Country birthplace) {
        this.birthplace = birthplace;
    }

    public List<UserSkills> getSkills() {
        return skills;
    }

    public void setSkills(List<UserSkills> skills) {
        this.skills = skills;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", profileDesc='" + profileDesc + '\'' +
                ", address='" + address + '\'' +
                ", birthdate=" + birthdate +
                ", nationality=" + nationality +
                ", birthplace=" + birthplace +
                ", skills=" + skills +
                '}';
    }
}
