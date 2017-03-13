# HTTP Server

``` bash
┌─────────┐                                          ┌─────────┐
│         │          ┌────────────────────┐          │         │
│         │──────────┤   GET / HTTP/1.1   ├─────────▶│         │
│         │          └────────────────────┘          │  Http   │
│ Client  │                                          │ Server  │
│         │          ┌────────────────────┐          │         │
│         │◀─────────┤  HTTP/1.1 200 OK   ├──────────│         │
│         │          └────────────────────┘          │         │
└─────────┘                                          └─────────┘
```

``` bash

Byequest          Request                                                    Web
Client            Server             provider            parser              Framework

│                   │                   │                   │                   │    
├────────Http ─────▶│                   │                   │                   │    
│      request      ├───Read socket ───▶│                   │                   │    
│                   │                   │                   │                   │    
│                   │◀──Http request ───┤                   │                   │    
│                   │                   │                   │                   │    
│                   │                   │                   │                   │    
│                   ├──────────Parse http request ─────────▶│                   │    
│                   │                   │                   │                   │    
│                   │◀─────────Parsed http request──────────│                   │    
│                   │                   │                   │                   │    
│                   │                   │                   │                   │    
│                   │───────────────────┼───Get response────┼──────────────────▶│    
│                   │                   │                   │                   │    
│                   │◀──────────────────┼─────Response──────┼───────────────────┤    
│◀────────Http ─────┤                   │                   │                   │    
│       response    │                   │                   │                   │    
```
## Run
``` bash
  gradle execute
```


You can connect to the server with netcat this way:

``` bash
  nc localhost 5000
```

## Test
``` bash
  gradle test
```
