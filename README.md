# appium-mobile-automation

# Steps to run the test:
~ Download sample.apk file from URL: https://github.com/afollestad/material-dialogs/raw/master/sample/sample.apk and copy to src/test/resources Directory
~ See below to Download Android Studio and Start the Android Emulator
~ See below to Download and Start the Appium Server
~ Run the Test Class from Eclipse IDE

# Install Android Studio
~ Open Browser and navigate to URL - https://developer.android.com/studio
~ Look for download file: android-studio-ide-183.5452501-linux.tar.gz
~ Open a command prompt and extract the downloaded file
mkdir mobile-tools
cd mobile-tools
tar xf ~/Downloads/android-studio-ide-183.5452501-linux.tar.gz 

# Update Environment Variable. File: ~/.bashrc
export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
source ~/.bashrc

# Start the Android Studio
cd android-studio/bin
./studio.sh

# Android Studio - Create Virtual Device
Press: Control + Shift + a
enter 'avd'
choose "AVD Manager"
Click Button: Create Virtual Device
Sample Device - Choose Category as "Phone" , Name as "Pixel2" , Size as "5.0" , Resolution: 1080 X 1920 
Click Button: Next
Choose Release Name: Oreo , API Level: 27 (Click when Download link present near Oreo)
Enter AVD Name as "Pixel2API27" and Click Button "Finish"
In Android Virtual Device Manager, Under column "Actions"  click Icon "Play" 

# Start Emulator
cd $HOME/Android/Sdk/tools
emulator -list-avds
emulator -avd Pixel2API27 -netdelay none -netspeed full


# Download and Install Node JS
https://nodejs.org/en/
https://nodejs.org/dist/v10.15.3/node-v10.15.3-linux-x64.tar.xz
cd ~/mobile-tools
tar xf ~/Downloads/node-v10.15.3-linux-x64.tar.xz
mv node-v10.15.3-linux-x64 nodejs

# Update Environment Variales
vi ~/.bashrc
export PATH=$PATH:$HOME/mobile-tools/nodejs/bin
source ~/.bashrc

# Install and Verify Appium Dependencies
cd ~/mobile-tools/nodejs/bin
npm install -g appium
npm install -g appium-doctor
appium-doctor --android
npm install webdriverio
npm i -g mjpeg-consumer

# Start Appium
cd ~/mobile-tools/nodejs/bin
appium

# Run the Appium Test Case
cd $Home/appium-mobile-automation
mvn test

