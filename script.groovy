def buildJar(){
    echo 'Building the application jar...'
    sh "mvn package"
}

def buildImage() {
    echo 'Building the docker image...'
        withCredentials([aws(credentialsId: 'ec2-jenkins', accessKeyVariable:'AWS_ACCESS_KEY_ID', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY', region: 'eu-north-1')]) {
        sh "aws --version"
        commonHelper.logInfo("aws cli installed")
        sh "docker build -t $USER/$JOB_NAME:$BUILD_NUMBER ."
        sh "docker login -u $USER -p $PASS 681800194367.dkr.ecr.eu-north-1.amazonaws.com/new-app:$BUILD_NUMBER"
        sh "docker push $USER/$JOB_NAME:$BUILD_NUMBER"
        }
        // sh "aws --version"
        // sh "aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/v8z9z5a4"
        // sh "docker build -t $JOB_NAME:$BUILD_NUMBER ."
        // sh "docker tag $JOB_NAME:$BUILD_NUMBER public.ecr.aws/v8z9z5a4/new-app:$BUILD_NUMBER"
        // sh "docker push public.ecr.aws/v8z9z5a4/new-app:$BUILD_NUMBER"
}   

def deployApp(){
    echo 'Deploying the application...'
}

return this