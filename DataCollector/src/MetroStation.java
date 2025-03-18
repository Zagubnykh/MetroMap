public class MetroStation {
    private String name;
    private String lineNumber;
    private Boolean hasConnection;

    public Boolean getHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(Boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "MetroStations{" +
                "name='" + name + '\'' +
                ", lineNumber=" + lineNumber +
                '}';
    }
}
