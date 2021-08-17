package models;

public class Music {
    private int id;
    private String name;
    private String singer;
    private String link;

    public Music() {
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Music(int id, String name, String singer, String link) {
        this.id = id;
        this.name = name;
        this.singer = singer;

        this.link = link;
    }

    public Music(int id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
