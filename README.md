# Pure SDK - React Native Module

## Getting started
> npm install --save react-native-pure

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
    
_IMPORTANT!_**
If targeting play-services 15+, you have to also import the following dependencies:
```groovy
  implementation (“com.google.android.gms:play-services-ads:15.0.1”)
  implementation (“com.google.android.gms:play-services-awareness:15.0.1")
```

### iOS
1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`.
2. Go to `node_modules` ➜ `react-native-pure` ➜ `ios` and add `RNPure.xcodeproj`.
3. In XCode, in the project navigator, select your project.
4. Add `libRNPure.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`.
5. Under `Build Settings` ➜ `Search Paths` ➜ `Header Search Paths`, add `$(SRCROOT)/../node_modules/react-native-pure/ios` as non-recursive.
6. In the `ios` folder, create a file named `Podfile` with the following content, or merge into your existing `Podfile`:
  	```ruby
  	target 'yourAppName' do
      platform :ios, '9.0' #minimum target for pure
      pod 'PureSDK', :podspec => 'http://puresdk.azurewebsites.net/cocoapods/versions/1.0.64?key=<PWD>'
      pod 'RNPure', :path => '../node_modules/react-native-pure/ios'
    end
  	```
  	* *PWD* will be provided by fluxLoop.

    Include `pod 'PureSDKBluetooth', :podspec => 'https://puresdk.azurewebsites.net/cocoapods/bluetooth/versions/1.0.64?key=<PASSWORD>` to collect Eddystone UID and Eddystone URL.

5. Run `pod install` in the `ios` folder from a terminal window.
5.1 *From now on, use the xcworkspace generated by cocoapods to work on the native iOS files*.
6. Open the `xcworkspace` file that was generated by CocoaPods, located in the `ios` folder.
7. In `AppDelegate.m`, add the following with the other imports: `#import <PureSDK/Pure.h>`
8. In `AppDelegate.m` find the method with the launchOptions, and add the following: 
```objective-c
[Pure initializeWithLaunchOptions:launchOptions];
```
9. Click [here](https://github.com/fluxloop/pure-sdk/blob/master/iOS/README.md) for additional settings required (Permissions).
10. [`startTracking`](#startTracking) must be called to finish initializing the iOS SDK. This can either be done in AppDelegate.m with `[Pure startTracking]` after the init call, or be done with the aformentioned javascript function.
## Usage
```javascript

import { Pure, PureResult } from 'react-native-pure';
Pure.startTrackingWithResponse((res) => {
  if (res === PureResult.PURE_ENABLED) {
    // do something
  }
});

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
*Android only - Promise returns undefined for iOS*

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

Starts tracking of the users movement.
The SDK stores the previous state, so you don't have to call Pure.startTracking() every time the app launches.
```javascript
Pure.startTracking();
```

### startTrackingWithResponse()
<a name="startTrackingWithResponse"></a>
*Only Android returns a response, iOS returns undefined*

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
*Only Android returns a response, iOS returns undefined*

Stops tracking of the users movement.
```javascript
Pure.stopTracking().then((res) => {
  // do stuff based on response
});
```

### isTracking()
<a name="isTracking"></a>

Returns true if user is being tracked, false if not.
```javascript
Pure.isTracking().then((isTracked) => {
  // do stuff based on response
});
```

### getClientId()
<a name="getClientId"></a>

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
ß
### associateMetadataWithForce(string, json, boolean)
<a name="associateMetadataWithForce"></a>

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

### createEventWithForce(string, json, boolean)
<a name="createEventWithForce"></a>

Used to add an event by force.
The name describes what kind of event this is.
If sending order information, it could look something like this:
```javascript
const orderData = {userId: 1234567, orderId: 10001, 'timestamp': '2018-02-01T11:49:31+00:00'};

Pure.createEventWithForce('Order', orderData, true).then((res) => {
  // do stuff based on if response was success
});
```

  
