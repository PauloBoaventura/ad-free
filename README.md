# ad-free <img src="design/ad-free_logo_dropshadow-padding.svg" width="40">

[![Kotlin App](https://img.shields.io/badge/Android-Kotlin-green.svg?style=flat)]()  [![codebeat badge](https://codebeat.co/badges/1fc357d9-4c2e-46f6-b847-d295e4de78eb)](https://codebeat.co/projects/github-com-abertschi-ad-free-master)

ad-free is a research project attempting to show flaws in the way how audio advertisement is shown on Android.
 It is a proof-of-concept of a modularized audio ad blocker written in Kotlin with a modern and simplistic user interface.


https://adfree.abertschi.ch

<img src=".github/screens3.sized.png" width="900">

## Features
- No ROOT required
- Turn off sound when advertisement is playing
- Play arbitrary audio instead of advertisements 
- Plugin based design
- Support for Spotify
- Support for Soundcloud (experimental)
- Mute Google Chromecast (experimental)

## Download
[Download the latest release](https://f-droid.org/packages/ch.abertschi.adfree/) from the F-Droid store.  

<a href='https://f-droid.org/packages/ch.abertschi.adfree/'><img src="./landing/get-it-on.png" width="220"/></a>

## Legality
This app is free and opensource and does not seek a comercial interest. It does not
collect user data. It is a proof-of-concept to show
flaws in the way how audio advertisement is often implemented on
Android. Ad-free's algorithms are described below and its source code is freely available.
It does not alter or "hack" protection measures of music players, and only
gathers context information provided by the Android
runtime. Nontheless, ad-free may be against terms of services of music
players. Muting commercials may not be supported and may result in a
temporary ban. Use at your own risk.

## Troubleshooting
See [Troubleshooting](./troubleshooting/readme.org) section for help to get
ad-free up and running.

## Changelog
See [Changelog](./CHANGELOG.md) for a list of implemented features
in new releases.
  
## Implementation notes
### Ad detection
Advertisement detectors are modularized into implementations of [AdDetectable](./app/src/main/java/ch/abertschi/adfree/detector/AdDetectable.kt). An instance of `AdDetectable` can determine if a track being played is advertisement or not.

Ad Free registers an [NotificationListenerService](https://developer.android.com/reference/android/service/notification/NotificationListenerService.html) and is therefore able to parse all incoming notifications on Android. Notifications are parsed by implementations of `AdDetectable`:

- `TitleDetector`:  
Detector which parses notifications for certain keywords. In order to avoid false positives, an notification action is provided to unblock wrongly detected advertisements.

- `NotificationActionDetector`:  
Detector which inspects notifications for properties set in the track navigation bar.

- `NotificationBundleAndroidTextDetector`:  
Detector which checks for properties set in the notification bundle.

- [More](https://github.com/abertschi/ad-free/tree/master/app/src/main/java/ch/abertschi/adfree/detector)

### Ad blocking
[AudioManager](https://developer.android.com/reference/android/media/AudioManager.html),
Android's Audio System provides several streams on which audio can be
played. Music players play audio on the stream
[STREAM_MUSIC](https://developer.android.com/reference/android/media/AudioManager.html#STREAM_MUSIC).
In case of ad detection, Ad Free mutes _STREAM MUSIC_ and calls
a configured [AdPlugin](./app/src/main/java/ch/abertschi/adfree/plugin/AdPlugin.kt). `AdPlugins`
aim to replace advertisement. They play music on an alternative stream and are
therefore not affected by the mute of _STREAM MUSIC_.

## Plugins
### Mute Audio
As the title of this plugin suggests, it only mutes adertisments without playing sound.

### Play local music
Play music tracks stored on your phone while advertisement is playing. At the moment ``.mp3``, ``.wav``, and ``.m4a`` are supported.

### Interdimensional Cable (discontinued)
_[Interdimensional
Cable](./app/src/main/java/ch/abertschi/adfree/plugin/interdimcable/InterdimCablePlugin.kt)
replaces advertisements with interdimensional cable advertisements
featured in [Adult Swim's Rick and Morty TV
series](https://www.youtube.com/watch?v=sBvV1miNoA8&index=12&list=PLNu47mcqeyiATtjW5pIRWlpXBu4pUezdP)._

Due to legal reasons and a rise in popularity, this feature is no
longer supported. [See here](https://github.com/abertschi/ad-free/blob/master/rick_and_morty.md).

## Credits
- The bird and website icons used in this app are made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> and are licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a>.

- This app is written in Kotlin. [Kotlin](https://github.com/JetBrains/kotlin) by Jetbrains is lisenced by the Apache Lisence 2.0

- [RxAndroid](https://github.com/ReactiveX/RxAndroid) by the RxAndroid authors is licensed by the Apache License 2.0
- [Fuel](https://github.com/kittinunf/Fuel) is made by Kittinun Vantasin and is licensed by MIT

- [snakeyaml](https://bitbucket.org/asomov/snakeyaml) is licensed by the Apache License 2.0

- [XStream](http://x-stream.github.io/license.html) by Joe Walnes and the XStream Committers is lisenced by BSD license.

## Licence
This project is lisenced by the Apache Lisence 2.0

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IT IS A PROOF OF CONCEPT AND INTENDED TO BE A RESEARCH PROJECT. IN NO EVENT
SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
DAMAGE.
