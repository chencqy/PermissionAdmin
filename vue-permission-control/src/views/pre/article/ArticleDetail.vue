<template>
  <div class="createPost-container">
    <Tinymce ref="editor" :height="400" />
  </div>
</template>

<script>
import Tinymce from '@/components/TinyMCE'

export default {
  name: 'ArticleDetail',
  components: { Tinymce }
}
</script>

<style scoped>

</style>
