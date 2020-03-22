package scau.lzl.rest.entity;

public class Tracker {

    private String name;
    private long timestamp;
    private String referer;
    private int position;

    public Tracker() {
    }

    public Tracker(String name, long timestamp, String referer, int position) {
        this.name = name;
        this.timestamp = timestamp;
        this.referer = referer;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Tracker{" +
                "name='" + name + '\'' +
                ", timestamp=" + timestamp +
                ", referer='" + referer + '\'' +
                ", position=" + position +
                '}';
    }
}
