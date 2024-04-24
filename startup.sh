#!/bin/bash

nohup java -jar /tsx/tsx-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > /tsx/log.txt 2>&1 &
