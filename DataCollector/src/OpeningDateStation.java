import java.util.Objects;

public class OpeningDateStation {
    private String stationName;
    private String openingDate;

    public OpeningDateStation(String stationName, String openingDate) {
        this.stationName = stationName;
        this.openingDate = openingDate;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    @Override
    public String toString() {
        return "OpeningDateStation{" +
                "stationName='" + stationName + '\'' +
                ", openingDate='" + openingDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpeningDateStation that = (OpeningDateStation) o;
        return Objects.equals(stationName, that.stationName) && Objects.equals(openingDate, that.openingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, openingDate);
    }
}
