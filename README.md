# Pure SDK - React Native Module

## Getting started
> npm install --save fluxloop/react-native-pure

After installing the module, do the following:

### Android
1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.fluxloop.react.pure.RNPurePackage;` to the imports at the top of the file
  - Add `new RNPurePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```groovy
  	include ':react-native-pure'
  	project(':react-native-pure').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-pure/android')
  	```
3. Insert the following lines inside the allprojects > repositories block in `android/build.gradle`:
  	```groovy
    mavenLocal()
    mavenCentral()
    jcenter()
    maven {
            url "$rootDir/../node_modules/react-native/android"
        }
    maven {url 'https://maven.google.com'}
    maven {
        credentials {
            username "<USERNAME>"
            password "<PASSWORD>"
        }
        url 'https://puresdk.azurewebsites.net/artifacts/'
    }
  	```
  	*The USERNAME and PASSWORD will be provided by fluxLoop.*
  	For more detailed overview on Android dependencies, click [here](https://github.com/fluxloop/pure-sdk/blob/master/Android/README.md).
  	
4. Insert the following lines inside the dependencies block in `android/app/build.gradle``:
    ```groovy
    compile project(':react-native-pure')
    compile fileTree(dir: "libs", include: ["*.jar"])
    ```
### iOS
1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-pure` and add `RNPure.xcodeproj`
In XCode, in the project navigator, select your project. Add `libRNPure.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
3. In the `ios` folder, create a file named `Podfile` with the following content, or merge into your existing `Podfile`:
  	```ruby
  	workspace 'desiredWorkspaceName.xcworkspace'
  	xcodeproj 'yourProjectName.xcodeproj'
  	xcodeproj '../node_modules/react-native-pure/ios/RNPure.xcodeproj'
  	
  	target 'yourAppName' do
      platform :ios, '10.0'
      xcodeproj 'yourAppName.xcodeproj'
      use_frameworks!
      pod 'PureSDK', :podspec => 'http://puresdk.azurewebsites.net/cocoapods/versions/latest?key=<PASSWORD>'
    end
    
    target 'RNPure' do
      platform :ios, '10.0'
      xcodeproj '../node_modules/react-native-pure/ios/RNPure.xcodeproj'
      use_frameworks!
      pod 'PureSDK', :podspec => 'http://puresdk.azurewebsites.net/cocoapods/versions/latest?key=<PASSWORD>'
    end
  	```
  	* *desiredWorkSpace*: Should be replaced with your desired name for a work space. If you already have a workspace, give it the same name.
  	* *yourProjectName*: Should be named exactly the same as your xcodeproj, which can be found in the `ios`folder.
  	*  *<PASSWORD> will be provided by fluxLoop.*

4. Run `pod install` in the `ìos` folder from a terminal window.
4.1 *From now on, use the xcworkspace to work on the native iOS files*.
5. Open the `xcworkspace` file that was generated by CocoaPods, located in the `ios` folder.
6. In `AppDelegate.m`, add the following with the other imports: `#import <PureSDK/Pure.h>`
7. In `AppDelegate.m` find the method with the launchOptions, and add the following: 
```objective-c
[Pure startWithLaunchOptions:launchOptions];
````
8. Click [here](https://github.com/fluxloop/pure-sdk/blob/master/iOS/README.md) for additional settings required

## Usage
```javascript
// TODO: How to import & use module

// Preferred:
import {Pure} from 'react-native-pure';
Pure.methodName();

// If the above does not work:
import {NativeModules} from 'react-native';
NativeModules.Pure.methodName();
```

## Available methods
* [`init`](#init)
* [`startTracking`](#startTracking)
* [`startTrackingWithResponse`](#startTrackingWithResponse)
* [`stopTracking`](#stopTracking)
* [`stopTrackingWithResponse`](#stopTrackingWithResponse)
* [`isTracking`](#isTracking)
* [`getClientId`](#getClientId)
* [`associateMetadata`](#associateMetadata)
* [`associateMetadataWithForce`](#associateMetadataWithForce)
* [`createEvent`](#createEvent)
* [`createEventWithForce`](#createEventWithForce)

<a name="init"></a>
### init()
* Android only - Promise returns undefined for iOS *

The SDK is initialized automatically by default. If you need to override this behaviour, you can do this by adding the following metadata to your AndroidManifest:
```xml
 <meta-data android:name="com.pure.sdk.AutoInit" android:value="false" />
```

To then do your manual init of the SDK:
```javascript
Pure.init().then((res) => {
  // do stuff based on response
});
```

### startTracking()
<a name="startTracking"></a>
* Android only - Does not start tracking on iOS *

Starts tracking of the users movement.
The SDK stores the previous state, so you don't have to call Pure.startTracking() every time the app launches.
```javascript
Pure.startTracking();
```

### startTrackingWithResponse()
<a name="startTrackingWithResponse"></a>
* Android only - Promise returns undefined for iOS*

Starts tracking of the users movement.
The SDK stores the previous state, so you don't have to call Pure.startTrackingWithResponse() every time the app launches.
```javascript
Pure.startTrackingWithResponse().then((res) => {
  // do stuff based on response
});
```

### stopTracking()
<a name="stopTracking"></a>

Stops tracking of the users movement.
```javascript
Pure.stopTracking();
```

### stopTrackingWithResponse()
<a name="stopTrackingWithResponse"></a>
* Android only - Promise returns undefined for iOS, but still stops tracking *
Stops tracking of the users movement.
```javascript
Pure.stopTracking().then((res) => {
  // do stuff based on response
});
```

### isTracking()
<a name="isTracking"></a>
* Android only - Promise returns undefined for iOS *

Returns true if user is being tracked, false if not.
```javascript
Pure.isTracking().then((isTracked) => {
  // do stuff based on response
});
```

### getClientId()
<a name="getClientId"></a>
* Android only - Promise returns undefined for iOS *

Returns the client ID.
```javascript
Pure.getClientId().then((id) => {
  // do stuff based on response
});;
```

### associateMetadata(string, json)
<a name="associateMetadata"></a>

Used to add metadata.
The type has to be unique for each model you want to preserve.
If sending userinfo, it could look something like this:
```javascript
const userInfo = {userId: 1234567, gender: 'male', 'birthYear': 1980};

Pure.associateMetadata('UserInfo', userInfo).then((res) => {
  // do stuff based on if response was success
});
```

### associateMetadataWithForce(string, json, boolean)
<a name="associateMetadataWithForce"></a>
* Only for Android - Does not force on iOS, does a regular associateMetadata *
Used to add metadata by force.
Boolean in argument should be true for force, false for not.
```javascript
const userInfo = {userId: 1234567, gender: 'male', 'birthYear': 1980};

Pure.associateMetadataWithForce('UserInfo', userInfo, true).then((res) => {
  // do stuff based on if response was success
});
```

### createEvent(string, json)
<a name="createEvent"></a>

Used to add an event.
The name describes what kind of event this is.
If sending order information, it could look something like this:
```javascript
const orderData = {userId: 1234567, orderId: 10001, 'timestamp': '2018-02-01T11:49:31+00:00'};

Pure.createEvent('Order', orderData).then((res) => {
  // do stuff based on if response was success
});
```

### createEvent(string, json, boolean)
<a name="createEvent"></a>
* Only for Android - Does not force on iOS, does a regular createEvent *

Used to add an event by force.
The name describes what kind of event this is.
If sending order information, it could look something like this:
```javascript
const orderData = {userId: 1234567, orderId: 10001, 'timestamp': '2018-02-01T11:49:31+00:00'};

Pure.createEventWithForce('Order', orderData, true).then((res) => {
  // do stuff based on if response was success
});
```

  
