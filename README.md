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

This aim of the project was to understand how a Http Server work.
The aim of the project is to help an individual understand of how a Http Server
work. It was driven by 23 [acceptance tests](https://github.com/8thlight/cob_spec)
which expected to have features like:

- Post/Put/Get/Option/Head/Delete integration
- Public file listings/links
- Basic Auth
- File provider
- Partial file provider

Here is the basic flow of the Http server:
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

You can run the server this way:

## Run the server
``` bash
  java -jar build/libs/server.jar -p 5000 -d /your/public/directory
```

You can connect to the server with netcat this way:
``` bash
  nc localhost 5000
```

## Test
``` bash
  gradle test
```
