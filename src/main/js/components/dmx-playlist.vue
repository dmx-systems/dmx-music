<template>
  <div class="dmx-playlist">
    <div class="field">
      <dmx-value-renderer class="name" :object="name" :no-field-label="infoMode" :level="1" :path="[]"
        :context="context">
      </dmx-value-renderer>
    </div>
    <div class="field" v-if="description">
      <dmx-value-renderer :object="description" :no-field-label="infoMode" :level="1" :path="[]" :context="context">
      </dmx-value-renderer>
    </div>
    <div class="field" v-if="playerVisible">
      <audio :src="currentUrl" controls :autoplay="autoplay" @ended="playNext"></audio>
    </div>
    <div class="field">
      <ol>
        <template v-if="infoMode">
          <li v-for="track in tracks">
            <dmx-playlist-track :track="track" :context="context"></dmx-playlist-track>
          </li>
        </template>
        <draggable v-else :list="tracks" handle=".handle" :animation="300" @start="drag=true" @end="drag=false">
          <!-- 3 lines duplicated in favor of code splitting -->
          <li v-for="track in tracks">
            <div class="track">
              <dmx-playlist-track :track="track" :context="context"></dmx-playlist-track>
              <div :class="['handle', {drag}]"></div>
            </div>
          </li>
        </draggable>
      </ol>
    </div>
  </div>
</template>

<script>
export default {

  mixins: [
    // require('./mixins/object').default,       // the playlist to render
    require('./mixins/info-mode').default,
    require('./mixins/context').default
  ],

  props: {
    // the playlist to render
    object: {
      type: Object,   // dmx.DMXObject ### we can't rely on 'dmx'; injections are not available on 'props' processing
      required: true
    }
  },

  data () {
    return {
      playPos: 0,
      drag: false
    }
  },

  computed: {

    mode () {
      return this.context.mode
    },

    name () {
      return this.object.children['dmx.music.playlist_name']
    },

    description () {
      return this.object.children['dmx.music.playlist_description']
    },

    tracks () {
      return this.object.children['dmx.music.track#dmx.music.playlist_item'] || []
    },

    fileUrls () {
      return this.tracks.map(track => '/filerepo/' + encodeURIComponent(
        track.children['dmx.files.file'].children['dmx.files.path'].value
      ))
    },

    currentUrl () {
      return this.fileUrls[this.playPos]
    },

    playerVisible () {
      return this.infoMode && this.tracks.length
    },

    autoplay () {
      return this.playPos > 0
    }
  },

  methods: {
    playNext () {
      console.log('playNext')
      this.playPos++
    }
  },

  components: {
    'dmx-playlist-track': require('./dmx-playlist-track').default,
    draggable: () => ({
      component: import('vuedraggable' /* webpackChunkName: "vuedraggable" */),
      loading: require('./dmx-spinner')
    })
  }
}
</script>

<style>
.dmx-playlist .name.info {
  font-size: 32px;
  font-weight: bold;
}

.dmx-playlist .field + .field {
  margin-top: var(--field-spacing);
}

.dmx-playlist ol {
  margin-top: 0;
  padding-left: 30px;
}

.dmx-playlist ol li {
  margin-top: 12px;
}

.dmx-playlist ol li .track {
  display: flex;
}

.dmx-playlist ol li .track .dmx-playlist-track {
  flex-grow: 1;
}

.dmx-playlist ol li .track .handle {
  flex-basis: 12px;
  flex-shrink: 0;
  margin-left: 12px;
  background-image: url("../../resources/dots.png");
  border: 2px solid white;
}

.dmx-playlist ol li .track .handle:hover {
  cursor: grab;
}

.dmx-playlist ol li .track .handle.drag {
  cursor: grabbing !important;
}
</style>
