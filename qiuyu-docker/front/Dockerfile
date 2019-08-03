# build stage
FROM node:10.10.0-alpine AS buildstage

RUN npm config set registry https://registry.npm.taobao.org

WORKDIR /app
COPY ./vue-permission-control .

RUN npm install
RUN npm run build

# production stage
FROM nginx:1.15.3-alpine AS prodstage

MAINTAINER chencqy "chen.qy1996@hotmail.com"

COPY ./qiuyu-docker/front/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=buildstage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
