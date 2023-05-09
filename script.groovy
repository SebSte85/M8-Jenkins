def buildJar(){
    echo 'Building the application jar...'
    sh "mvn package"
}

def buildImage() {
    echo 'Building the docker image...'
        sh "aws --version"
        sh "aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/v8z9z5a4"
        sh "docker build -t $JOB_NAME:$BUILD_NUMBER ."
        sh "docker tag $JOB_NAME:$BUILD_NUMBER public.ecr.aws/v8z9z5a4/$JOB_NAME:$BUILD_NUMBER"
        sh "docker push public.ecr.aws/v8z9z5a4/new-app:$BUILD_NUMBER"
}   

def deployApp(){
    echo 'Deploying the application...'
}

return this