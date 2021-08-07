<template>
  <div class="dmx-playlist">
    <div class="field">
      <dmx-value-renderer :object="name" :level="1" :path="[]" :context="context"></dmx-value-renderer>
    </div>
    <div class="field">
      <dmx-value-renderer :object="description" :level="1" :path="[]" :context="context"></dmx-value-renderer>
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
    require('./mixins/object').default,       // the playlist to render
    require('./mixins/info-mode').default,
    require('./mixins/context').default
  ],

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
