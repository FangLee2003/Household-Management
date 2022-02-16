package model;

public class hometown {
    private String city;
    private long population;

    public hometown() {

    }

    public hometown(String city, long population) {
        this.city = city;
        this.population = population;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "hometown{" +
                "city='" + city + '\'' +
                ", population=" + population +
                '}';
    }
}
