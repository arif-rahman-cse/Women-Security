package talha.com.bd.patha_shathi.models;

public class Pathasathis {

    private String name;
    private String month;
    private int picture;

    public Pathasathis(String name, String month, int picture) {
        this.name = name;
        this.month = month;
        this.picture = picture;
    }

    public Pathasathis() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

}
