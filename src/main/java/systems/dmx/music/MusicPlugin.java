package systems.dmx.music;

import static systems.dmx.core.Constants.*;
import static systems.dmx.datetime.Constants.*;
import static systems.dmx.files.Constants.*;
import static systems.dmx.music.Constants.*;

import systems.dmx.core.Topic;
import systems.dmx.core.osgi.PluginActivator;
import systems.dmx.core.service.Inject;
import systems.dmx.core.service.Transactional;
import systems.dmx.core.util.ChildTopicsSequence;
import systems.dmx.files.FilesService;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import java.util.logging.Logger;



@Path("/music")
@Consumes("application/json")
@Produces("application/json")
public class MusicPlugin extends PluginActivator implements MusicService {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    @Inject
    private FilesService fs;

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods

    // *** MusicService ***

    @PUT
    @Path("/playlist/{playlistId}/file/{fileId}")
    @Transactional
    @Override
    public void addFileToPlaylist(@PathParam("fileId") long fileId, @PathParam("playlistId") long playlistId) {
        try {
            Tags tags = TagReader.read(fs.getFile(fileId));
            logger.info("Adding file " + fileId + " to playlist " + playlistId + ", tags: " + tags);
            Topic playlist = dmx.getTopic(playlistId);
            Topic item = dmx.createTopic(mf.newTopicModel(PLAYLIST_ITEM, mf.newChildTopicsModel()
                .set(TRACK, mf.newChildTopicsModel()
                    .set(TRACK_TITLE, tags.title)
                    .set(ARTIST, tags.artist)
                    .set(ALBUM, tags.album)
                    .set(YEAR, tags.year)
                    .setRef(FILE, fileId)
                )
            ));
            playlist.update(mf.newChildTopicsModel().addRef(PLAYLIST_ITEM, item.getId()));
            ChildTopicsSequence s = new ChildTopicsSequence(playlist, PLAYLIST_ITEM, COMPOSITION, dmx);
            s.add(item, null);
        } catch (Exception e) {
            throw new RuntimeException("Adding file " + fileId + " to playlist " + playlistId + " failed", e);
        }
    }
}
