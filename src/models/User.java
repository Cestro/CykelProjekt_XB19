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

//    private String newName;
//    private int newAge;
//    private String newFirm;
//    private String newEmail;
//    private String newCyclistType;
//    private int newPassword;

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

    public void setnewName(String name) {
        this.name = name;
    }

    public void setnewAge(int age) {
        this.age = age;
    }

    public void setnewFirm(String firm) {
        this.firm = firm;
    }

    public void setnewEmail(String email) {
        this.email = email;
    }

    public void setnewCyclistType(String cyclistType) {
        this.cyclistType = cyclistType;
    }

    public void setnewPassword(String password) {
        this.password = password;
    }
}
