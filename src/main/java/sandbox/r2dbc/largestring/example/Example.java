package sandbox.r2dbc.largestring.example;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.r2dbc.spi.Clob;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Result;
import reactor.core.publisher.Mono;

@Component
public class Example {
	
	@Autowired 
	@Qualifier("local")
	private ConnectionFactory factoryLocalDatabase;
	
	@Autowired 
	@Qualifier("azure")
	private ConnectionFactory factoryAzureDatabase;
	
	
	public Mono<Result> getResultsetForLocal(String blob){
		return Mono.from(factoryLocalDatabase.create())
				.flatMap(connection -> {
					Publisher<? extends Result> result= connection
							.createStatement("INSERT INTO TEST_TABLE ( jsonBlob) VALUES(@jsonBlob)")
		            .bind("jsonBlob", blob).execute();
					
					return Mono.from(result);
					
				});
		
	}
	
	public Mono<Result> getResultsetForLocal(Clob blob){
		return Mono.from(factoryLocalDatabase.create())
				.flatMap(connection -> {
					Publisher<? extends Result> result= connection
							.createStatement("INSERT INTO TEST_TABLE ( jsonBlob) VALUES(@jsonBlob)")
		            .bind("jsonBlob", blob).execute();
					
					return Mono.from(result);
					
				});
		
	}


	public Mono<Result> getResultsetForAzure(String blob) {
		return Mono.from(factoryAzureDatabase.create())
				.flatMap(connection -> {
					Publisher<? extends Result> result= connection
							.createStatement("INSERT INTO TEST_TABLE ( jsonBlob) VALUES(@jsonBlob)")
		            .bind("jsonBlob", blob).execute();
					
					return Mono.from(result);
					
				});
	}
	
	public Mono<Result> getResultsetForAzure(Clob blob) {
		return Mono.from(factoryAzureDatabase.create())
				.flatMap(connection -> {
					Publisher<? extends Result> result= connection
							.createStatement("INSERT INTO TEST_TABLE ( jsonBlob) VALUES(@jsonBlob)")
		            .bind("jsonBlob", blob).execute();
					
					return Mono.from(result);
					
				});
	}
}
