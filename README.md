# ad-free
An ad free spotify music experience for Android

<img src=".github/cover.png" width="900">

## Features
- [x] Turn off sound when advertisement is playing
- [ ] Play local music instead when advertisement is playing

## Implementation notes
Advertisement detectors are modularized into implementations of [AdDetectable](./app/src/main/java/ch/abertschi/adump/detector/AdDetectable.kt). An instance of `AdDetectable` can determine if a track being played is a Spotify advertisement or not.

## Release notes
### [v0.0.1, 2017-04-17](https://github.com/abertschi/ad-free/releases/tag/v0.0.1)
Initial release
- Turns off sound when advertisement is playing
- Adds notification action to filter out false positive matches