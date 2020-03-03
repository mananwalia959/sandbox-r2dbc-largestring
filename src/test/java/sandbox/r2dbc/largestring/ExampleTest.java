package sandbox.r2dbc.largestring;


import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.r2dbc.spi.Clob;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sandbox.r2dbc.largestring.example.Example;

@SpringBootTest()
@ActiveProfiles("default")
class ExampleTest {
	
	@Autowired Example example;

	@Test
	void testForLocalWithString() {
		
		String str = getLargeString(9000);
		
		StepVerifier.create(example.getResultsetForLocal(str))
			.expectNextCount(1)
			.verifyComplete();
		
	}
	
	@Test
	void testForLocalWithClob() {
		
		String str = getLargeString(9000);
		Clob clob= getClobFromString(str);
		
		StepVerifier.create(example.getResultsetForLocal(clob))
			.expectNextCount(1)
			.verifyComplete();
		
	}
	
	@Test
	void testForAzureWithString() {
		//fails for me at 7872
		String str = getLargeString(9000);
		
		StepVerifier.create(example.getResultsetForAzure(str))
			.expectNextCount(1)
			.verifyComplete();
		
	}
	
	@Test
	void testForAzureWithClob() {
		
		String str = getLargeString(9000);
		Clob clob= getClobFromString(str);
		
		StepVerifier.create(example.getResultsetForAzure(clob))
			.expectNextCount(1)
			.verifyComplete();
	}
	


	private String getLargeString(int n){
		StringBuilder builder = new StringBuilder();
		
		for(int i=0;i<n;i++)
			builder.append("f");
		
		return builder.toString();
		
	}
	private Clob getClobFromString(String str) {
		Publisher<CharSequence> charsequence = Mono.just(str);
		return Clob.from(charsequence);
	}

}
