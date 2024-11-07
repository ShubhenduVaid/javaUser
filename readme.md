// Create KeySpace
CREATE KEYSPACE billion
WITH replication = {
'class': 'SimpleStrategy',
'replication_factor': 3
};

//Create Table in Keyspace
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

-> Here country is Partition Key and id is Clustering Key

//Create a Docker network to allow communication between containers:
docker network create cassandra-network

-> Seed Node
docker run -d --name cassandra-seed \
--network cassandra-network \
-e CASSANDRA_CLUSTER_NAME="MyCluster" \
-e CASSANDRA_SEEDS="cassandra-seed" \
-e CASSANDRA_LISTEN_ADDRESS="cassandra-seed" \
-e CASSANDRA_BROADCAST_ADDRESS="cassandra-seed" \
-p 9042:9042 \
cassandra:latest

-> Additional Node
docker run -d --name cassandra-node1 \
--network cassandra-network \
-e CASSANDRA_CLUSTER_NAME="MyCluster" \
-e CASSANDRA_SEEDS="cassandra-seed" \
-e CASSANDRA_LISTEN_ADDRESS="cassandra-node1" \
-e CASSANDRA_BROADCAST_ADDRESS="cassandra-node1" \
cassandra:latest

-> cqlsh





