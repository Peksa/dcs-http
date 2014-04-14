dcs-http
==========

dcs-http is a [Play! framework 1.x](http://www.playframework.org/) application that exposes a small JSON
HTTP API with data from a [DCS World](https://www.digitalcombatsimulator.com/en/world/) client.


Demo
----
Here's a demo what you can build with this!

![](https://raw.github.com/peksa/dcs-http/master/screenshot.png)


Features
--------
The API currently exposes two endpoints:
    
Fetch all objects (static and dynamic) in the simulator:
    `/api/objects`
    
Fetch all active objects (objects that are moving):
    `/api/activeobjects`


Example response
----------------
```json
[
  {
    "id": 17018625,
    "name": "AWACS #001",
    "lat": 42.411212,
    "lon": 42.577581,
    "heading": 3.275483,
    "alt": 7010.398437,
    "country": "Enemies",
    "type": "E-3A",
    "groupName": "AWACS #001",
    "entity": "O",
    "time": 9776.44
  },
  {
    "id": 17018369,
    "name": "Tanker SHELL-1",
    "lat": 41.896175,
    "lon": 42.170345,
    "heading": 0.13528,
    "alt": 5486.398435,
    "country": "Enemies",
    "type": "KC-135",
    "groupName": "Tanker SHELL-1",
    "entity": "O",
    "time": 9776.44
  }
]
```

Example client
--------------
There currently exists a sample client which is located at `http://localhost:9000/maps`.
This is a small web page that uses JavaScript to fetch the positions of all objects in the game which are then drawn out on a map.

How it works
------------
DCS World allows you to run Lua code in order to export data out of the simulator. Lua is used in order to establish a socket to the Play application, where data is then streamed. The data is cached in memory in order to efficiently serve HTTP requests to clients.
```
23:39:53,945 INFO  ~ Starting C:\Users\Peksa\git\dcs-http
23:39:54,018 INFO  ~ Precompiling ...
23:39:56,681 INFO  ~ Application 'dcs-http' is now started !
23:39:56,704 INFO  ~ Waiting for connection by DCS on: 0.0.0.0:9595
23:39:56,749 INFO  ~ Listening for HTTP on port 9000 ...
23:40:20,769 INFO  ~ Got connection from DCS 127.0.0.1:9923
```

TODO
----
Expose much more information from DCS - the active players on the server, the current map and so on. If you know Lua or DCS World, please get in touch.


Run it yourself
---------------
If you'd like to run the application yourself, you're welcome to do so.

1. Install [DCS World](https://www.digitalcombatsimulator.com/en/world/).
2. Copy `lua/Export.lua` from the root of this repo to `%HOME%/Saved Games/DCS/Scripts`
3. Install JDK 7
4. Install Play framework 1.x. Instructions [here](http://www.playframework.org/documentation/1.2.5/install).
5. Clone this repo, `git clone git://github.com/Peksa/dcs-http.git`
6. Start the application, `play run --%prod`
7. Start DCS World and join or host a single or multiplayer game.
8. Visit [http://localhost:9000/api/objects](http://localhost:9000/api/objects) or try out the sample client at [http://localhost:9000/maps](http://localhost:9000/maps)
