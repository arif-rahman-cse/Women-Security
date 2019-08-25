package talha.com.bd.patha_shathi.models;

public class AvailablePothSathi {
    private String name;
    private String distance;
    private int image;



    public AvailablePothSathi(String name, String distance, int image) {
        this.name = name;
        this.distance = distance;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public class MyHolder {
    }
}
