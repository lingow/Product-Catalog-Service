package org.iteso.msc.asn2015.productcatalog;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class HerokuDataSourceFactory {
	
	public DataSource createDataSource() throws URISyntaxException{
		BasicDataSource src = new BasicDataSource();
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		src.setUsername(username);
		src.setPassword(password);
		src.setUrl(dbUrl);
		return src;
	}

}
