## Micronaut 3.6.2 Documentation

- [User Guide](https://docs.micronaut.io/3.6.2/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.6.2/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.6.2/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Handler

[AWS Lambda Handler](https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html)

Handler: com.softamo.telegram.l337.FunctionRequestHandler


## Requisites

- [AWS Account](https://aws.amazon.com/free/)
- [CDK CLI](https://docs.aws.amazon.com/cdk/v2/guide/cli.html)
- [AWS CLI](https://aws.amazon.com/cli/)

## How to deploy

### Generate the deployable artifact

```
./gradlew :app:shadowJar
./gradlew test
```

### Deploy

The `infra/cdk.json` file tells the CDK Toolkit how to execute your app.

`cd infra`
`cdk synth` - emits the synthesized CloudFormation template
`cdk deploy` - deploy this stack to your default AWS account/region
`cd ..`

Other useful commands:

`cdk diff` - compare deployed stack with current state
`cdk docs`- open CDK documentation

### Cleanup

```
cd infra
cdk destroy
cd ..
```


- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
## Feature aws-cdk documentation

- [https://docs.aws.amazon.com/cdk/v2/guide/home.html](https://docs.aws.amazon.com/cdk/v2/guide/home.html)


## Feature aws-lambda-function-url documentation

- [https://docs.aws.amazon.com/lambda/latest/dg/lambda-urls.html](https://docs.aws.amazon.com/lambda/latest/dg/lambda-urls.html)


## Feature aws-lambda documentation

- [Micronaut AWS Lambda Function documentation](https://micronaut-projects.github.io/micronaut-aws/latest/guide/index.html#lambda)


