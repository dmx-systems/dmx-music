package systems.dmx.music;

import java.io.File;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;



public class TagReader {

    public static Tags read(File file) {
        try {
            AudioFile f = AudioFileIO.read(file);
            Tag tag = f.getTag();
            String title  = tag.getFirst(FieldKey.TITLE);
            String artist = tag.getFirst(FieldKey.ARTIST);
            String album  = tag.getFirst(FieldKey.ALBUM);
            String year   = tag.getFirst(FieldKey.YEAR);
            return new Tags(title, artist, album, year);
        } catch (Exception e) {
            throw new RuntimeException("Reading tags of " + file + " failed", e);
        }
    }
}
