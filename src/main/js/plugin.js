export default ({dmx, axios: http}) => ({

  storeModule: {
    name: 'music',
    module: require('./music').default
  },

  objectRenderers: {
    'dmx.music.playlist': require('./components/dmx-playlist').default
  },

  dropHandler: {

    isDroppable (topic1, topic2) {
      return topic1.typeUri === 'dmx.files.file' && topic2.typeUri === 'dmx.music.playlist'
    },

    handleDrop (topic1, topic2) {
      // console.log('music handleDrop', topic1, topic2)
      if (topic1.typeUri === 'dmx.files.file' && topic2.typeUri === 'dmx.music.playlist') {
        http.put(`/music/playlist/${topic2.id}/file/${topic1.id}`)
      }
    }
  }
})
