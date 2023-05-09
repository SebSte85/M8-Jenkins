def incrementVersion(){
    echo 'Incrementing the version...'
    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
}

def buildJar(){
    echo 'Building the application jar...'
    sh "mvn clean package"
  
}

def buildImage() {
    echo 'Building the docker image...'
        sh "aws --version"
        sh "aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/v8z9z5a4"
        sh "docker build -t $JOB_NAME:$BUILD_NUMBER ."
        sh "docker tag $JOB_NAME:$BUILD_NUMBER public.ecr.aws/v8z9z5a4/new-app:$BUILD_NUMBER"
        sh "docker push public.ecr.aws/v8z9z5a4/new-app:$BUILD_NUMBER"
}   

def deployApp(){
    echo 'Deploying the application....'
}

def versionUpdate(){
    echo 'Updating the version...'

    withCredentials([usernamePassword(credentialsId: 'github-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'git config --global user.email "sebastian.stemmer@dig-it-up.de"'
        sh 'git config --global user.name "SebSte85"'

        sh "git status"
        sh "git branch"
        sh "git config --list"

        sh "git remote set-url origin git@github.com/SebSte85/M8-Jenkins.git"
        sh "git add ."
        sh "git commit -m 'version update'"
        sh "git push origin HEAD:dockerfile"
}
}

return this