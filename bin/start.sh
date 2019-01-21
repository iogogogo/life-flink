#!/bin/sh

jps | grep MainApplication | awk "{print \$1}" | xargs kill -9 >/dev/null 2>&1

nohup java -Dlogback.configurationFile=logback.xml -cp $(pwd)/life-flink-1.0-SNAPSHOT.jar com.iogogogo.MainApplication > $(pwd)/start.log &