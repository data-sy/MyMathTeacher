const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // 멀티워드 아니어도 되게
  lintOnSave: false
})
