package com.billion.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Override
	protected String getKeyspaceName() {
		return "user_schema";  // Your keyspace name
	}

	@Override
	protected String getContactPoints() {
		return "localhost";  // Your Cassandra contact point
	}

	@Override
	protected int getPort() {
		return 9042;  // Your Cassandra port
	}

	@Override
	protected String getLocalDataCenter() {
		return "datacenter1";  // Your local data center
	}
}
