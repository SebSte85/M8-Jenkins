def buildJar(){
    echo 'Building the application jar...'
    sh "mvn package"
}

def buildImage() {
    echo 'Building the docker image...'
        // withCredentials([aws(credentialsId: 'ec2-jenkins', accessKeyVariable:'AWS_ACCESS_KEY_ID', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY', region: 'eu-north-1')]) {
        // sh "aws --version"
        // sh "docker build -t $USER/$JOB_NAME:$BUILD_NUMBER ."
        // sh "docker login -u $USER -p $PASS 681800194367.dkr.ecr.eu-north-1.amazonaws.com/new-app:$BUILD_NUMBER"
        // sh "docker push $USER/$JOB_NAME:$BUILD_NUMBER"
        // }
        sh "aws --version"
        sh "aws ecr get-login-password --region eu-north-1 | sudo docker login --username AWS --password-stdin 681800194367.dkr.ecr.eu-north-1.amazonaws.com"
        sh "docker build -t $JOB_NAME:$BUILD_NUMBER ."
        sh "docker tag $JOB_NAME:$BUILD_NUMBER 681800194367.dkr.ecr.eu-north-1.amazonaws.com/$JOB_NAME:$BUILD_NUMBER"
        sh "docker push 681800194367.dkr.ecr.eu-north-1.amazonaws.com/$JOB_NAME:$BUILD_NUMBER"
}   

def deployApp(){
    echo 'Deploying the application...'
}

return this