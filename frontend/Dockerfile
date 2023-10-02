FROM node:20-alpine

WORKDIR /frontend

COPY package.json package-lock.json ./

RUN npm install -g typescript

RUN npm install

COPY .  ./

RUN npm run build
