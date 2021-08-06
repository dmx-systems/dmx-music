<template>
  <div class="dmx-playlist-item">
    {{artist}} - {{trackTitle}}
  </div>
</template>

<script>
export default {

  props: {
    item: {
      type: Object,
      required: true
    }
  },

  computed: {

    track () {
      return this.item.children['dmx.music.track']
    },

    artist () {
      return this.track.children['dmx.music.artist'].value
    },

    trackTitle () {
      return this.track.children['dmx.music.track_title'].value
    },

    itemDescription () {
      return this.item.children['dmx.music.item_description']
    }
  }
}
</script>
