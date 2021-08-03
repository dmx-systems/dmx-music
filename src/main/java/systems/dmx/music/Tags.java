package systems.dmx.music;

public class Tags {

    public String title;
    public String artist;
    public String album;
    public String year;

    public Tags(String title, String artist, String album, String year) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    public String toString() {
        return "TITLE=\"" + title + "\", ARTIST=\"" + artist + "\", ALBUM=\"" + album + "\", YEAR=\"" + year + "\"";
    }
}
