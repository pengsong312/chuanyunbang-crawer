#!/bin/bash
set -e;
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
cd $DEPLOY_DIR;
tailf logs/stdout.log;