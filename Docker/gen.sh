#!/bin/bash
cp ../target/xw-0.0.1-SNAPSHOT.jar xw-0.0.1-SNAPSHOT.jar
docker build -t springboot .
docker run -d -p 8088:8088 springboot