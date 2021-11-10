# JustBar
A HMOS library to add a Bar.

# Source
Inspired by [Hammad Akram/JustBar](https://github.com/Hamadakram/JustBar) - version 1.0

## Features
This library provides an animation similar to Just bar feature.
<img src="https://github.com/applibgroup/Instalike/blob/master/screenshots/instalikeview.gif" width="256">

## Dependency
1. For using justbar module in sample app, include the source code and add the below dependencies in entry/build.gradle to generate hap/support.har.
```groovy
	dependencies {
        implementation fileTree(dir: 'libs', include: ['*.jar', '*.har'])
        testImplementation 'junit:junit:4.13'
        ohosTestImplementation 'com.huawei.ohos.testkit:runner:1.0.0.200'
        implementation project(':justbar')
    }
```
2. For using justbar in separate application using har file, add the har file in the entry/libs folder and add the dependencies in entry/build.gradle file.
```groovy
	dependencies {
		implementation fileTree(dir: 'libs', include: ['*.har'])
		testImplementation 'junit:junit:4.13'
	}
```

## Usage
#### Include following code in your layout:
```xml
    <com.irozon.justbar.JustBar
            ohos:id="$+id:bottomBar"
            ohos:width="match_parent"
            ohos:height="match_content"
            ohos:bottom_margin="16vp">
            <com.irozon.justbar.BarItem
                ohos:id="$+id:barItem"
                ohos:width="0vp"
                ohos:height="0vp"
                app:icon="$graphic:ic_search"
                app:radius="35vp" />
            .
            .
            .

    </com.irozon.justbar.JustBar>
```
Attribute | Desription
--- | ---
`selectedColor` | Selected state color for the ` BarItem `
`unSelectedColor` | Unselected state color for the `BarItem`
`selectedIconColor` | Selected state color for the icon
`unSelectedIconColor` | Unselected state color for the icon
`selected` | Initial selected or unselected state for BarItem`
`icon` | Icon for `BarItem`
`radius` | Radius for the `BarItem`


#### Action for `BarItem`:
```java
    justBar.setOnBarItemClickListener(new OnBarItemClickListener() {
               @Override
               public void onBarItemClick(BarItem barItem, int position) {
                    // Your code here
               }
           });
```

## Future Work
Since there is no alternate api for setColorFilter in HMOS platform, custom attributes - app:selectedColor, 
app:unSelectedColor, app:selectedIconColor, app:unSelectedIconColor  is currently not supported. 
As a result, user needs to call the createColorMatrix function and pass a colorCode as an argument which will internally call setColorMatrix(createColorMatrix(selectedColor)) to change the color of the drawable. Once HMOS platform supports setColorFilter, then this custom attribute can be included.
                                                                                                   
## Licence
```
Copyright 2018 Irozon, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```                                                                                   

