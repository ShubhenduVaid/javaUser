# Steps to set up Cassandra on Docker

## Create a Docker network to allow communication between containers
```bash
docker network create cassandra-network
```

## Create a seed node
```bash
docker run -d --name cassandra-seed \
--network cassandra-network \
-e CASSANDRA_CLUSTER_NAME="MyCluster" \
-e CASSANDRA_SEEDS="cassandra-seed" \
-e CASSANDRA_LISTEN_ADDRESS="cassandra-seed" \
-e CASSANDRA_BROADCAST_ADDRESS="cassandra-seed" \
-p 9042:9042 \
cassandra:latest
```

## Create an additional node
```bash
docker run -d --name cassandra-node1 \
--network cassandra-network \
-e CASSANDRA_CLUSTER_NAME="MyCluster" \
-e CASSANDRA_SEEDS="cassandra-seed" \
-e CASSANDRA_LISTEN_ADDRESS="cassandra-node1" \
-e CASSANDRA_BROADCAST_ADDRESS="cassandra-node1" \
cassandra:latest
```
## Go inside container via command-line
```bash
cqlsh
```

## Create KeySpace
```bash
CREATE KEYSPACE billion
WITH replication = {
'class': 'SimpleStrategy',
'replication_factor': 3
};
```

## Create Table in Keyspace
```bash
CREATE TABLE billion.users (
id UUID,
name TEXT,
email TEXT,
dob DATE,
address TEXT,
phone TEXT,
country TEXT,
date_created TIMESTAMP,
PRIMARY KEY ((country), id)
);
```
Note : Here country is Partition Key and id is Clustering Key




