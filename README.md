1)Install Docker on EC2 instance
-> sudo yum install docker

2)Start Docker
-> sudo systemctl start docker
-> sudo usermod -aG docker ec2-user

3)Install Jenkins on EC2 instance
-> docker run -p 8080:8080 -p 50000:50000 -d -v jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v $(which docker):/usr/bin/docker jenkins/jenkins:lts

OR if having a problem with the linux versions

-> docker run -p 8080:8080 -p 50000:50000 -d -v /var/run/docker.sock:/var/run/docker.sock -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts
-> docker exec -it -u0 <container id> bash
-> curl https://get.docker.com > dockerinstall && chmod 777 dockerinstall && ./dockerinstall
-> chmod +x docjer.sock
-> chgrp jenkins docker.sock
-> chmod jenkins docker.sock

4)Install aws cli on jenkins image
-> curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
-> unzip awscliv2.zip
-> ./aws/install
-> aws configure
-> enter credentials from aws user

5)Give jenkins user permission to run Docker in container VIDEO 6 12:00 Min
->sudo docker exec -u 0 -it da5faa4cc9bb bash
->chmod 666 /var/run/docker.sock

...
..
.
