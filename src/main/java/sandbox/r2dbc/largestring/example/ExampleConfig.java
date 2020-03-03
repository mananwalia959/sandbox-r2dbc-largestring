package sandbox.r2dbc.largestring.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.r2dbc.mssql.MssqlConnectionConfiguration;
import io.r2dbc.mssql.MssqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
@Configuration
public class ExampleConfig {
	
	@Bean(name = "local")
	public ConnectionFactory getFactoryForLocal() {
		MssqlConnectionConfiguration configuration = MssqlConnectionConfiguration.builder()
			    .host("localhost")
			    .username("sa")
			    .password("yourPasswordHere")
			    .database("YourDatabaseHere")
			    .build();

			MssqlConnectionFactory factory = new MssqlConnectionFactory(configuration);
			return factory;		
	}
	
	@Bean(name = "azure")
	public ConnectionFactory getFactoryForAzure() {
		MssqlConnectionConfiguration configuration = MssqlConnectionConfiguration.builder()
			    .host("something***.database.windows.net") // enter your azure hosted database here
			    .username("yourUser")
			    .password("yourPassword")
			    .database("yourDatabase")
			    .build();

			MssqlConnectionFactory factory = new MssqlConnectionFactory(configuration);
			return factory;		
	}
	
	
	


	
	

}
