const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
  , devServer: {
     proxy: 'http://localhost:8091/'
  }
})


/*** 
devServer: {
  proxy: {
      '/': {
          "target": 'http://localhost:8091/',
          "pathRewrite": { '^/': '' },
          "changeOrigin": true,
          "secure": false
      }
    }
  }
*/