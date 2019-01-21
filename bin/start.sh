#!/bin/sh

nohup java -cp $(pwd)/life-flink-1.0-SNAPSHOT.jar com.iogogogo.MainApplication >/dev/null 2>&1 &