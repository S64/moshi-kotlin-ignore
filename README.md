# moshi-kotlin-ignore

**TL;DR:** Moshi version of Jackson's `@JsonIgnore`. For when you can't use `@Transient`.

In Moshi, You can use `transient` keyword (in Kotlin: `@Transient` annotation) for ignore serialization.  
But sometimes it's conflict to other features. e.g. `ebean-querybean`.

`moshi-kotlin-ignore` is library for ignore parameter serialization / deserialization. You can use this library to resolve above issues.

## Usages

First, Add following lines to your buildscripts:

```groovy
buildscript {
  ext {
    mki_version = '0.0.3'
  }
}
```

```groovy
repositories {
  maven { url 'http://dl.bintray.com/s64/maven' }
}

dependencies {
  compile "jp.s64.kotiln:moshi-kotlin-ignore:${mki_version}"
}
```

Next, Add factory to your Moshi builder.

```kotlin
// import jp.s64.kotlin.moshi.ignore.IgnoreAdapter

Moshi.Builder()
  //.add(KotlinJsonAdapterFactory())
  .add(IgnoreAdapter.FACTORY)
  .build()
```

For example: You can use in data class's value parameter.

```kotlin
// import jp.s64.kotlin.moshi.ignore.Ignore

data class MyItem(
  val id: Long
  @Ignore
  val secret: String
)
```

`@Ignore` is easy to make conflict? Don't worry, Kotlin has `typealias` feature 😉.

```kotlin
// import jp.s64.kotlin.moshi.ignore.Ignore

typealias MyMagicalAnnotationOfIgnoreJsonPropertyLikeJsonIgnore = Ignore

data class MyAwesomeObjectOfHoldUsersSecretForSignIn(
  val id: Long
  @MyMagicalAnnotationOfIgnoreJsonPropertyLikeJsonIgnore
  val secret: String
)
```

## License

```
Copyright 2017 Shuma Yoshioka

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

<a href="https://donorbox.org/moshi-kotlin-ignore"><img src="https://d1iczxrky3cnb2.cloudfront.net/button-small-blue.png" /></a>

<a href="https://www.patreon.com/S64"><img src="https://c5.patreon.com/external/logo/become_a_patron_button.png"/></a>
