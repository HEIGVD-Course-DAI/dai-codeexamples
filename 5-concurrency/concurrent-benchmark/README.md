Course 5 - Concurrency
======================

Implementation of a TCP server to test the performance of different ways to implement concurrency:


* Single-task server
* Multi-threaded server without thread-pool
* Multi-threaded server with fixed-size thread-pool (different sizes) 
* Multi-threaded server with cached thread-pool
* Multi-threaded server with virtual threads
* as well as a asynchronous server in JavaScript

How to run
----------

* In the `./java_servers` directory, run `mvn clean package` to build the Java servers.
* In the main directory, run `docker compose build` to build the Docker images (Java and JavaScript).
* In the main directory, run `docker compose up` to start the servers.

This start the following servers
| Server                                 | Port  |
|----------------------------------------|-------|
| Single-threaded server                 | 10001 |
| Multi-threaded server                  | 10002 |
| Fixed thread-pool server (4 threads)   | 10003 |
| Fixed thread-pool server (500 threads) | 10004 |
| Cached thread-pool server              | 10005 |
| Virtual thread-pool server             | 10006 |
| JavaScript asynchronous server         | 10007 |

Finally, ideally on another machine, use [ApacheBench](https://httpd.apache.org/docs/2.4/programs/ab.html) to benchmark the concurrent servers:

```bash
ab -n 10000 -c 1000 http://<server-ip>:<server-port>/<test>
```

Here:
* `<server-ip>` is the IP address of the machine running the concurrent servers (`localhost` if you run the benchmark on the same machine)`
* `<server-port>` is the port exposed by the server implementation you want to test (see the logs or the  `docker-compose.yml` file)
* `<test>` indicate the type of work the server should perform for each requests. The options are:
  * `compute`: perform a CPU-intensive computation
  * `sleep`: sleep for a fixed delay

You can also combine operations.

Examples of commands:

```bash
ab -n 2000 -c 1000 http://localhost:10007/sleep
ab -n 2000 -c 1000 http://localhost:10007/compute/sleep
```

Both commands perform 2000 requests, with up to 1000 parallel connections. The first command only sleeps for a fixed delay, while the second command performs a CPU-intensive computation and the sleeping operation.

Results
-------

`ab` prints the results of the benchmark. The most important metric is the *Requests per second*.