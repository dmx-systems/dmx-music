package systems.dmx.music;

import systems.dmx.core.osgi.PluginActivator;
import systems.dmx.core.service.Transactional;

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

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods

    // *** MusicService ***

    @PUT
    @Path("/playlist/{playlistId}/file/{fileId}")
    @Transactional
    @Override
    public void addFileToPlaylist(@PathParam("fileId") long fileId, @PathParam("playlistId") long playlistId) {
        try {
            logger.info("Adding file " + fileId + " to playlist " + playlistId);
        } catch (Exception e) {
            throw new RuntimeException("Adding file " + fileId + " to playlist " + playlistId + " failed", e);
        }
    }
}
