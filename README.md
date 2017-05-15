[Blockchain Monitoring Tool](http://blockchain-monitoring.org)
================

BMTool is an open source project designed for Hyperledger Fabric. 

It provides convient and demonstrative way to represent information
about blockchain fabric network activities. 
# About
Project consists of Grafana, Influx DB and BMTool as own, which collects and aggregates telemetry from Fabric.
## Requirements
You need Docker and maybe Docker-compose to run BMTool and open 3000 and 8086 ports. That's all.
## Installation
You can download docker image with command: `docker pull blockchainmonitoring/blockchain-monitoring:latest`

With docker-compose create docker-compose.yaml file:
```yaml
version: '2'

services:
  monitoring:
    container_name: blockchain-monitoring
    image: blockchainmonitoring/blockchain-monitoring:latest
    volumes:
      - $FABRIC_NET_CONFIG:/etc/conf/net-config.yaml
    ports:
      - "3000:3000"
      - "8086:8086"
```
and net-config.yaml file:
```yaml
organisations:
- name: 'foo'
  ca:
    name: 'ca-foo'
    address: 'http://172.25.0.177:7054'
  enroll:
    login: 'fadmin'
    pass: 'foo'
    msp: 'foo'
  peers:
    - name: 'peer-foo'
      address: 'grpc://172.25.0.104:7051'

    - name: 'peer-foo-02'
      address: 'grpc://172.25.0.105:7051'

    - name: 'peer-foo-03'
      address: 'grpc://172.25.0.106:7051'

- name: 'bar'
  ca:
    name: 'ca-foo'
    address: 'http://ca-foo:7054'
  enroll:
    login: 'badmin'
    pass: 'bar'
    msp: 'bar'
  peers:
    - name: 'peer-bar'
      address: 'grpc://172.25.0.107:7051'
```

This file describes fabric network configuration and contains two main sections: organization and channels.
Orgranization section provides information about fabric-CA, fabric-peers name and address, MSP-ID. 
Next section channels show us which peers are connected to channel, their addresses, names and msp-id.

Also you need to set environment variable $FABRIC_NET_CONFIG for net-config.yaml file (**it must be absolute path**) and after that just write:
```bash
docker-compose up
```
If monitoring seccessully started you can access to it by visiting http://localhost:3000 admin:admin

## Use monitoring in your code
BMTool provides you simple API, written in Java. 
Visit [link](https://github.com/blockchain-monitoring/blockchain-monitoring-api) for more information.
