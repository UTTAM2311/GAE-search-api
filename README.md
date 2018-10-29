# GAE-search-api


<a href="https://console.cloud.google.com/cloudshell/open?git_repo=https://github.com/UTTAM2311/GAE-search-api&page=editor&open_in_editor=README.md">
<img alt="Open in Cloud Shell" src ="http://gstatic.com/cloudssh/images/open-btn.png"></a>


## Prerequisites
* Gradle 4.0 or Later
* Java 8 
* [google auth sdk](https://cloud.google.com/sdk/docs/downloads-apt-get) 

## Start the application 

    # Configure the project,account and other details . 
    gcloud init 
    # verify the project  
    gradle clean build .
    ## Deploy the application in app Engine .
    gradle appEngineDeploy     