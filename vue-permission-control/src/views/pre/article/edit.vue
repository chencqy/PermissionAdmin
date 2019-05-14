<template>
  <article-detail :is-edit="true" />
</template>

<script>
import ArticleDetail from './ArticleDetail'

export default {
  name: 'EditForm',
  components: { ArticleDetail }
}
</script>

