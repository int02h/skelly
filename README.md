# Skelly
[![Build Status](https://travis-ci.com/int02h/skelly.svg?branch=master)](https://travis-ci.com/int02h/skelly)
[![Latest release](https://img.shields.io/github/release/int02h/skelly.svg)](https://github.com/int02h/skelly/releases/latest)

Skelly is a tiny library for displaying skeletons while the content itself is loading. All skeletons are completely synchronized across the whole application no matter how many of them visible on a screen at the moment.

![Example GIF](docs/example.gif)

## Customization

- Start color of gradient animation (0xFFCCCCCC by default)
- End color of gradient animation (0xFF444444 by default)
- Corner radius (half height by default)

The parameters from above can be set up both in xml-layout and source code.

## How to use

Just add it to your xml-layout:

```xml
<com.dpforge.skelly.SkeletonView
    android:id="@+id/skeleton"
    android:layout_width="match_parent"
    android:layout_height="48dp" />
```

Keep in mind that `SkeletonView` has no size so `wrap_content` does not work properly.

## Sample

[Sample project](sample/) will show you how to use skelly library in you Android application.

## Automatic substitution with `SkeletonView`

There is an **experimental** feature called `SkeletonLayoutFactory`. It allows you to take any regular layout and replace all views with `SkeletonView`. Fully automated!

You can take any xml-layout and turn it into skeleton-layout:

```kotlin
SkeletonLayoutFactory.Default.inflateFrom(
    context = this,
    layoutId = R.layout.avatar_title_subtitle,
    parent = container,
    attachToRoot = true
)
```

For more information please have a look at [factory sample](sample/src/main/java/com/dpforge/skelly/sample/SkeletonFactoryActivity.kt).

## Install

Add the following dependency to your `build.gradle` script:

```groovy
dependencies {
    implementation 'com.dpforge:skelly:1.0.0'
}
```

## License

Copyright (c) 2021 Daniil Popov

Licensed under the [MIT](LICENSE) License.