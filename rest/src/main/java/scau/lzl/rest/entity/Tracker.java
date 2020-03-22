package scau.lzl.rest.entity;

public class Tracker {

    private long id;
    private long uid;
    private long timestamp;
    private String referer;
    private int position;

    public Tracker() {
    }

    public Tracker(long id, long uid, long timestamp, String referer, int position) {
        this.id = id;
        this.uid = uid;
        this.timestamp = timestamp;
        this.referer = referer;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
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
                "id=" + id +
                ", uid=" + uid +
                ", timestamp=" + timestamp +
                ", referer='" + referer + '\'' +
                ", position=" + position +
                '}';
    }
}
