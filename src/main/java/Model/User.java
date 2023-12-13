package Model;

public class User {
    public String email;
    public String name;
    public String username;
    public String password;
    public String dob;
    public String gender;
    public String province;
    public String role;
    public double cpa;
    public String university;

    public User() {
    }
    public User(String username, String password, String name, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }
    public User(String name, double cpa) {
        this.name = name;
        this.cpa = cpa;
    }

    public User(String name, double cpa, String dob, String province) {
        this.name = name;
        this.cpa = cpa;
        this.dob = dob;
        this.province = province;
    }

    public User(String email, String name, String username, String password, String dob, String gender, String province, String role, double cpa, String university) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.province = province;
        this.role = role;
        this.cpa = cpa;
        this.university = university;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getCpa() {
        return cpa;
    }

    public void setCpa(double cpa) {
        this.cpa = cpa;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setName(String name) {
        this.name = name;
    }


}
