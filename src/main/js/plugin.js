export default {

  storeModule: {
    name: 'music',
    module: require('./music').default
  },

  dropHandler (viewTopic1, viewTopic2) {
    return viewTopic1.typeUri === 'dmx.files.file' && viewTopic2.typeUri === 'dmx.music.playlist'
  }
}
