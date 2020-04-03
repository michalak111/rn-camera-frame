
# react-native-mycameralib

## Getting started

`$ npm install react-native-mycameralib --save`

### Mostly automatic installation

`$ react-native link react-native-mycameralib`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-mycameralib` and add `RNMycameralib.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNMycameralib.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNMycameralibPackage;` to the imports at the top of the file
  - Add `new RNMycameralibPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-mycameralib'
  	project(':react-native-mycameralib').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-mycameralib/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-mycameralib')
  	```


## Usage
```javascript
import RNMycameralib from 'react-native-mycameralib';

// TODO: What to do with the module?
RNMycameralib;
```
  