<template>
  <div class="dmx-playlist-item">
    <div>{{artist}} - {{trackTitle}}</div>
    <div class="description" v-html="description"></div>
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

    description () {
      const desc = this.item.children['dmx.music.item_description']
      return desc && desc.value
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

<style>
.dmx-playlist-item .description p {
  color: var(--label-color);
  font-size: var(--label-font-size);
  line-height: 1.4;
  margin-top: 5px;
  margin-bottom: 0px;
}
</style>