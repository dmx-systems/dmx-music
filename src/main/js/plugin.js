export default ({dmx, axios: http}) => ({

  storeModule: {
    name: 'music',
    module: require('./music').default
  },

  objectRenderers: {
    'dmx.music.playlist': {
      comp: require('./components/dmx-playlist').default,
      model: {
        customize (formModel, object) {
          const items = object.children['dmx.music.playlist_item']
          const descType = dmx.typeCache.getTopicType('dmx.music.item_description')
          let i = 0
          formModel.children['dmx.music.playlist_item'].forEach(item => {
            const desc = items[i++].children['dmx.music.item_description']
            item.children['dmx.music.item_description'] = new dmx.Topic(descType._newInstance(desc))
          })
        }
      }
    }
  },

  dropHandler: {

    isDroppable (topic1, topic2) {
      return topic1.typeUri === 'dmx.files.file' && topic2.typeUri === 'dmx.music.playlist'
    },

    handleDrop (topic1, topic2) {
      // console.log('music handleDrop', topic1, topic2)
      http.put(`/music/playlist/${topic2.id}/file/${topic1.id}`)
    }
  }
})
