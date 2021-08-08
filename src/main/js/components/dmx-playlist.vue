<template>
  <div class="dmx-playlist">
    <div class="field">
      <dmx-value-renderer :object="name" :level="1" :path="[]" :context="context"></dmx-value-renderer>
    </div>
    <div class="field">
      <dmx-value-renderer :object="description" :level="1" :path="[]" :context="context"></dmx-value-renderer>
    </div>
    <div class="field" v-if="infoMode">
      <audio :src="currentUrl" controls></audio>
    </div>
    <div class="field">
      <div class="field-label">{{items.length}} items</div>
      <ol>
        <template v-if="infoMode">
          <li v-for="item in items">
            <dmx-playlist-item :item="item"></dmx-playlist-item>
          </li>
        </template>
        <draggable v-else :list="items" :animation="300">
          <!-- 3 lines duplicated in favor of code splitting; TODO: avoid -->
          <li v-for="item in items">
            <dmx-playlist-item :item="item"></dmx-playlist-item>
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
      playPos: 0
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

    items () {
      return this.object.children['dmx.music.playlist_item']
    },

    fileUrls () {
      return this.items.map(item => '/filerepo/' + encodeURIComponent(
        item.children['dmx.music.track'].children['dmx.files.file'].children['dmx.files.path'].value
      ))
    },

    currentUrl () {
      return this.fileUrls[this.playPos]
    }
  },

  components: {
    'dmx-playlist-item': require('./dmx-playlist-item').default,
    draggable: () => ({
      component: import('vuedraggable' /* webpackChunkName: "vuedraggable" */),
      loading: require('./dmx-spinner')
    })
  }
}
</script>

<style>
.dmx-playlist .field + .field {
  margin-top: var(--field-spacing);
}

.dmx-playlist ol {
  margin-top: 0;
  padding-left: 30px;
}

.dmx-playlist ol li {
  line-height: var(--line-height);
}
</style>
