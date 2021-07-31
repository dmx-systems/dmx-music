export default ({axios: http}) => ({

  storeModule: {
    name: 'music',
    module: require('./music').default
  },

  dropHandler: {
    isDroppable (topic1, topic2) {
      return topic1.typeUri === 'dmx.files.file' && topic2.typeUri === 'dmx.music.playlist'
    },
    handleDrop (topic1, topic2) {
      console.log('music handleDrop', topic1, topic2)
      http.put(`/music/playlist/${topic2.id}/file/${topic1.id}`)
    }
  }
})
