import java.util.Objects;

public class MetroStationDepth {
    private String stationName;
    private String stationDepth;

    public MetroStationDepth(String stationName, String stationDepth) {
        this.stationName = stationName;
        this.stationDepth = stationDepth;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationDepth() {
        return stationDepth;
    }

    public void setStationDepth(String stationDepth) {
        this.stationDepth = stationDepth;
    }

    @Override
    public String toString() {
        return "MetroStationDepth{" +
                "stationName='" + stationName + '\'' +
                ", stationDepth='" + stationDepth + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetroStationDepth that = (MetroStationDepth) o;
        return Objects.equals(stationName, that.stationName) && Objects.equals(stationDepth, that.stationDepth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, stationDepth);
    }

}
