<template>
  <div class="dmx-playlist">
    <dmx-value-renderer :object="name" :level="1" :path="[]" :context="context">
    </dmx-value-renderer>
    <dmx-value-renderer :object="description" :level="1" :path="[]" :context="context">
    </dmx-value-renderer>
    <div class="field-label">{{items.length}} items</div>
    <ol>
      <li v-for="item in items">
        <dmx-playlist-item :item="item"></dmx-playlist-item>
      </li>
    </ol>
  </div>
</template>

<script>
export default {

  mixins: [
    require('./mixins/object').default,       // playlist to render
    require('./mixins/context').default
  ],

  computed: {

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
  }
}
</script>

<style>
.dmx-playlist > .dmx-value-renderer + .dmx-value-renderer,
.dmx-playlist > .field-label {
  margin-top: var(--field-spacing);
}

.dmx-playlist > ol {
  padding-left: 30px;
}
</style>
