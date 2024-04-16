package com.web3.notification;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.web3.notification.service.RouterService;
import com.web3.notification.util.HibernateFactory;
import com.zaxxer.hikari.HikariDataSource;

public class AdminApplication implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
	static {
		HibernateFactory.buildSessionFactory();
	}

	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

		try {
			RouterService routeService = new RouterService();
			return routeService.routeRequest(input);
		} finally {
			flushConnectionPool();
		}

	}

	private void flushConnectionPool() {
		ConnectionProvider connectionProvider = HibernateFactory.getSessionFactory().getSessionFactoryOptions()
				.getServiceRegistry().getService(ConnectionProvider.class);
		HikariDataSource hikariDataSource = connectionProvider.unwrap(HikariDataSource.class);
		hikariDataSource.getHikariPoolMXBean().softEvictConnections();
	}
}
