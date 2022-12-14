package com.snhu.sslserver;

import java.security.MessageDigest;
import java.time.Duration;
import java.time.Instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}

@RestController
class ServerController{
//FIXME:  Add hash function to return the checksum value for the data string that should contain your name.    
    @RequestMapping("/hash")
    public String myHash(){
    	Instant start = Instant.now();
    	String algorithm = "SHA-256";
    	String myName = "Amanda Purnhagen";
    	byte[] data = myName.getBytes();
    	StringBuilder sb = new StringBuilder();
    	try {
    		MessageDigest theMessage = MessageDigest.getInstance(algorithm);
    		theMessage.update(data);
    		byte[] digested = theMessage.digest();
    		for (byte b : digested) {
    			sb.append(String.format("%02X ", b));
    		}
    	}catch (Exception e){
    	}
    	Instant end = Instant.now();
    	Duration timeElapsed = Duration.between(start, end);
    	System.out.println("Time elapsed: " + timeElapsed.toString());
    	
    	String tobrowser = "data: " + myName + "<br><br>";
    	tobrowser = tobrowser + algorithm + "<br><br>Checksum Value: " + sb.toString();
    	
    	
    	return tobrowser;
    }
}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";