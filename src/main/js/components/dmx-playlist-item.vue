<template>
  <div class="dmx-playlist-item">
    {{artist}} - {{trackTitle}}
  </div>
</template>

<script>
export default {

  mixins: [
    require('./mixins/object').default,       // object to render
    require('./mixins/level').default,
    require('./mixins/path').default,
    require('./mixins/comp-def').default,
    require('./mixins/context').default
  ],

  computed: {

    track () {
      return this.object.children['dmx.music.track']
    },

    artist () {
      return this.track.children['dmx.music.artist'].value
    },

    trackTitle () {
      return this.track.children['dmx.music.track_title'].value
    },

    itemDescription () {
      return this.object.children['dmx.music.item_description']
    }
  }
}
</script>
