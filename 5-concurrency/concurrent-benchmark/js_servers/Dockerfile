FROM node:20-alpine

WORKDIR /app
COPY *.mjs package.json ./
RUN npm install

ENTRYPOINT ["node", "AsyncJSServer.mjs"]

