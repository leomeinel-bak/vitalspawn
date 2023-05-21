<!-- PROJECT SHIELDS -->

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![Quality][quality-shield]][quality-url]

<!-- PROJECT LOGO -->
<!--suppress ALL -->
<br />
<p align="center">
  <a href="https://github.com/LeoMeinel/vitalspawn">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">VitalSpawn</h3>

  <p align="center">
    Set Spawn on Spigot and Paper
    <br />
    <a href="https://github.com/LeoMeinel/vitalspawn"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/LeoMeinel/vitalspawn">View Demo</a>
    ·
    <a href="https://github.com/LeoMeinel/vitalspawn/issues">Report Bug</a>
    ·
    <a href="https://github.com/LeoMeinel/vitalspawn/issues">Request Feature</a>
  </p>

<!-- ABOUT THE PROJECT -->

## About The Project

### Description

VitalSpawn is a Plugin that lets you set a spawn point.

This plugin is perfect for any server wanting to have a spawn.

### Features

- Set spawn
- Teleport to spawn
- MySQL/MariaDB support
- Teleport to spawn on spawn and respawn

### Built With

- [Gradle 7](https://docs.gradle.org/7.5.1/release-notes.html)
- [OpenJDK 17](https://openjdk.java.net/projects/jdk/17/)

<!-- GETTING STARTED -->

## Getting Started

To get the plugin running on your server follow these simple steps.

### Commands and Permissions

1. Permission: `vitalspawn.setspawn`

- Command: `/setspawn`
- Description: Set spawn

2. Permission: `vitalspawn.spawn`

- Command: `/spawn`
- Description: Teleport to spawn

3. Permission: `vitalspawn.onspawn`

- Description: Teleport to spawn on spawn/join

3. Permission: `vitalspawn.onrespawn`

- Description: Teleport to spawn on respawn

4. Permission: `vitalspawn.delay.bypass`

- Description: Bypass delay

### Configuration - config.yml

```yaml
# Command delay
delay:
  enabled: true
  # time in s
  time: 3

spawn-on-spawn: true
spawn-on-respawn: true

# Choose a storage system (mysql or yaml)
storage-system: yaml

mysql:
  host: "localhost"
  port: 3306
  database: vitalspawn
  username: "vitalspawn"
  password: ""
  prefix: "server_"
```

### Configuration - messages.yml

```yaml
spawn-tp: "&fYou have been teleported to spawn"
spawn-set: "&fYou have set the new spawn location"
no-perms: "&cYou don't have enough permissions!"
player-only: "&cThis command can only be executed by players!"
no-spawn: "&cNo Spawn has been set!"
countdown: "&fTeleporting in &b%countdown% &fseconds"
active-delay: "&cYou are already trying to teleport!"
```

<!-- ROADMAP -->

## Roadmap

See the [open issues](https://github.com/LeoMeinel/vitalspawn/issues) for a list of proposed features (and known
issues).

<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to be, learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->

## License

Distributed under the GNU General Public License v3.0. See `LICENSE` for more information.

<!-- CONTACT -->

## Contact

Leopold Meinel - [leo@meinel.dev](mailto:leo@meinel.dev) - eMail

Project Link - [VitalSpawn](https://github.com/LeoMeinel/vitalspawn) - GitHub

<!-- ACKNOWLEDGEMENTS -->

### Acknowledgements

- [README.md - othneildrew](https://github.com/othneildrew/Best-README-Template)

<!-- MARKDOWN LINKS & IMAGES -->

[contributors-shield]: https://img.shields.io/github/contributors-anon/LeoMeinel/vitalspawn?style=for-the-badge
[contributors-url]: https://github.com/LeoMeinel/vitalspawn/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/LeoMeinel/vitalspawn?label=Forks&style=for-the-badge
[forks-url]: https://github.com/LeoMeinel/vitalspawn/network/members
[stars-shield]: https://img.shields.io/github/stars/LeoMeinel/vitalspawn?style=for-the-badge
[stars-url]: https://github.com/LeoMeinel/vitalspawn/stargazers
[issues-shield]: https://img.shields.io/github/issues/LeoMeinel/vitalspawn?style=for-the-badge
[issues-url]: https://github.com/LeoMeinel/vitalspawn/issues
[license-shield]: https://img.shields.io/github/license/LeoMeinel/vitalspawn?style=for-the-badge
[license-url]: https://github.com/LeoMeinel/vitalspawn/blob/main/LICENSE
[quality-shield]: https://img.shields.io/codefactor/grade/github/LeoMeinel/vitalspawn?style=for-the-badge
[quality-url]: https://www.codefactor.io/repository/github/LeoMeinel/vitalspawn
