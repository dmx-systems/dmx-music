<template>
  <div class="dmx-playlist-track">
    <div>{{artist}} - {{trackTitle}}</div>
    <dmx-value-renderer class="description" v-if="description" :object="description" noFieldLabel :level="1" :path="[]"
      :context="context">
    </dmx-value-renderer>
  </div>
</template>

<script>
export default {

  mixins: [
    require('./mixins/context').default
  ],

  props: {
    track: {
      type: Object,
      required: true
    }
  },

  computed: {

    artist () {
      return this.track.children['dmx.music.artist'].value
    },

    trackTitle () {
      return this.track.children['dmx.music.track_title'].value
    },

    description () {
      return this.track.assoc.children['dmx.music.item_description']
    }
  }
}
</script>

<style>
.dmx-playlist-track .description {
  margin-top: 5px;
}

.dmx-playlist-track .description p {
  color: var(--label-color);
  font-size: var(--label-font-size);
  line-height: 1.4;
  margin: 0;
}

.dmx-playlist-track .description p + p {
  margin-top: 5px;
}
</style>
