# PermissionAdmin
## 简介

后端采用Spring Security OAuth2 管理用户登录以及权限问题，前端通过在[vue-admin-template](https://github.com/PanJiaChen/vue-element-admin)基础上修改而来。

在线预览：[http://47.108.64.204](http://47.108.64.204/)
## 内容
* 登录/github第三方登录、注销
* 权限 --- 页面权限，按钮权限
* 编辑器 --- 富文本
* 文章创建发布管理
* 头像、图片上传至阿里云OSS存储
* Swagger2接口详情页
* Redis存储token
* Docker部署至服务器
## 版本

| 依赖         | 版本  |
| ----------- | ----- |
| Java        | 1.8   |
| Spring Boot | 2.0.4 |
| Vue         | 2.9.6 |

## 开始
#### Vue

```
# 克隆项目
https://github.com/chencqy/PermissionAdmin.git

# 安装依赖
npm install

# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org

# 启动服务
npm run dev
```

#### Java
```java
// 运行环境
jdk1.8 + Maven

// 数据库配置
数据库mysql

// 缓存配置
配置redis，且redis服务必须开启

// 其他
Lombok注解工具
```

## 注：

注意修改相关配置、接口api等。