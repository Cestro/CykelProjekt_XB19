package models;

public class User {

    private String name;
    private int age;
    private String firm;
    private String email;
    private String cyclistType;
    private String password;
    private int type;
    private boolean addToTeam = false;

    private String newName;
    private int newAge;
    private String newFirm;
    private String newEmail;
    private String newCyclistType;
    private int newPassword;

    public User(String name, int age, String firm, String email, String cyclistType, String password, int type) {
        this.name = name;
        this.age = age;
        this.firm = firm;
        this.email = email;
        this.cyclistType = cyclistType;
        this.password = password;
        this.type = type;
    }

    public void setAddToTeam(boolean addToTeam) {
        this.addToTeam = addToTeam;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getFirm() {
        return firm;
    }

    public String getEmail() {
        return email;
    }

    public String getCyclistType() {
        return cyclistType;
    }

    public String getPassword() {
        return password;
    }

    public int getType() {
        return type;
    }

    public boolean getaddToTeam() {
        return addToTeam;
    }

    public void setnewName(String newName) {
        this.newName = newName;
    }

    public void setnewAge(int newAge) {
        this.newAge = newAge;
    }

    public void setnewFirm(String newFirm) {
        this.newFirm = newFirm;
    }

    public void setnewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public void setnewCyclistType(String newCyclistType) {
        this.newCyclistType = newCyclistType;
    }

    public void setnewPassword(int newPassword) {
        this.newPassword = newPassword;
    }
}
