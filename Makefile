###########################################################################
###########################################################################
#
# Project-Level Makefile
#
# This Makefile does the following:
# -Switches branch to the relevant branch for the environment.
# -Pull the relevant branch from the remote repo.
# -Builds the project.
# -Builds the docker container.
#
###########################################################################
###########################################################################

image_name = web/mobilecarwash

development:
    docker-compose stop && docker-compose rm -f && ./gradlew clean build -x test createDockerfile && cd ./build && echo "build" && docker build -t $(image_name):Latest . && cd ../

DEV: qa-clean-and-build
QA: QA-REPO qa-clean-and-build

QA-REPO:
	echo 'Running build for QA...' && git checkout master && git pull

qa-clean-and-build:
	docker-compose stop && docker-compose rm -f && gradle clean build -x test createDockerfile && cd ./build && echo "build" && docker build -t $(image_name):Latest . && cd ../ && docker-compose up -d

PUSH: commit

commit:
	git add --all && git commit -m"service update" && git push origin master
