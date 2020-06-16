<p align="center">
    <a href="#">
        <img src="https://github.com/congcoi123/tenio/blob/master/assets/tenio-github-logo.png">
    </a>
</p>
<p align="center">
    <a href="LICENSE">
        <img src="https://img.shields.io/badge/license-MIT-blue.svg">
    </a>
    <a href="#">
        <img src="https://img.shields.io/github/last-commit/congcoi123/tenio-libgdx">
    </a>
    <a href="https://github.com/congcoi123/tenio-libgdx/issues">
        <img src="https://img.shields.io/github/issues/congcoi123/tenio-libgdx">
    </a>
    <a href="CONTRIBUTING.md">
        <img src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg">
    </a>
    <a href="https://gitter.im/ten-io/community?source=orgpage">
        <img src="https://badges.gitter.im/Join%20Chat.svg">
    </a>
</p>

# TenIO Libgdx [![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=TenIO%20is%20a%20java%20NIO%20based%20server%20specifically%20designed%20for%20multiplayer%20games.%0D%0A&url=https://github.com/congcoi123/tenio%0D%0A&hashtags=tenio,java,gameserver,multiplayer,nio,netty,jetty,msgpack,cocos2dx,unity,libgdx,phaserjs%0D%0A&via=congcoi123)
This is a client of [TenIO](https://github.com/congcoi123/tenio) project base on [Libgdx](https://github.com/libgdx/libgdx) framework. It also uses some utility classes from [C2Engine](https://github.com/mytv1/C2Engine) to make life easier.

## Features
- It's used to simulator the simple physical movement simulation from the server-side.

## First glimpse
![Simple Movement Simulation](https://github.com/congcoi123/tenio/blob/master/assets/movement-simulation-example-4.gif)

## Wiki
The [wiki](https://github.com/congcoi123/tenio-libgdx/wiki) provides implementation level details and answers to general questions.

## Dependencies
- msgpack
- gdx
- gdx-backend-lwjgl
- gdx-freetype
- gdx-natives
- tween-engine-api

## License
This project is currently available under the [MIT](LICENSE) License.

## Installation
You can get the sources:
```sh
$ git clone https://github.com/congcoi123/tenio-libgdx.git
```

## Contributing
Please check out the [contributing guideline](CONTRIBUTING.md) for more details.

## Other Clients
| [<img src="https://github.com/congcoi123/tenio/blob/master/assets/cocos2dx-logo.png" width="150px;"/><br /><sub><b>TenIO Cocos2dx</b></sub>](https://github.com/congcoi123/tenio-cocos2dx)<br /> | [<img src="https://github.com/congcoi123/tenio/blob/master/assets/unity-logo.png" width="150px;"/><br /><sub><b>TenIO Unity</b></sub>](https://github.com/congcoi123/tenio-unity)<br />          | [<img src="https://github.com/congcoi123/tenio/blob/master/assets/phaserjs-logo.png" width="150px;"/><br /><sub><b>TenIO Phaserjs</b></sub>](https://github.com/congcoi123/tenio-phaserjs)<br /> |
| :-----------------------------------------------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------: |

## Running
Please start the server in example 4 before running this client. You start running the class below for your client-side:

```txt
|-- example
    |-- example4
    |   |-- TestServerMovement.java
```

> Happy coding !
