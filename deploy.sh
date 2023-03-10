#!/bin/bash
EXIT_STATUS=0
./gradlew :app:shadowJar || EXIT_STATUS=$?
if [ $EXIT_STATUS -ne 0 ]; then
  exit $EXIT_STATUS
fi
./gradlew test || EXIT_STATUS=$?
if [ $EXIT_STATUS -ne 0 ]; then
  exit $EXIT_STATUS
fi
cd infra
cdk synth --profile=softamobots --quiet true
cdk deploy --profile=softamobots --require-approval never
cd ..
exit $EXIT_STATUS
