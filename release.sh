#!/bin/bash
EXIT_STATUS=0
./gradlew test || EXIT_STATUS=$?
if [ $EXIT_STATUS -ne 0 ]; then
 exit $EXIT_STATUS
fi
./gradlew :app:shadowJar || EXIT_STATUS=$?
if [ $EXIT_STATUS -ne 0 ]; then
 exit $EXIT_STATUS
fi
cd infra
cdk synth --quiet true || EXIT_STATUS=$?
if [ $EXIT_STATUS -ne 0 ]; then
  exit $EXIT_STATUS
fi
cdk deploy --require-approval never || EXIT_STATUS=$?
if [ $EXIT_STATUS -ne 0 ]; then
  exit $EXIT_STATUS
fi
cd ..
exit $EXIT_STATUS
