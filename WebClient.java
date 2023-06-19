
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.lang.Object;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpRequest.BodyPublishers;

public class WebClient{

	private static final HttpClient httpClient = HttpClient.newBuilder()
	.version(HttpClient.Version.HTTP_1_1)
	.connectTimeout(Duration.ofSeconds(10))
	.build();

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//////////////////////////////////////  CLASE 33  //////////////////////////////////////
		// Crear el objeto Demo
		/*
		Demo demo = new Demo(10, "Hola mundo");
		byte[] serializado = SerializationUtils.serialize(demo);
		System.out.println("Objeto serializado:");
		System.out.println(serializado);
		System.out.println("////////////////////////////////");*/
		////////////////////////////////////////////////////////////////////////////////////////


		//////////////////////////////////////  CLASE 32  //////////////////////////////////////
		/*
		// Crear el cuerpo de la solicitud con la cadena y el número de tokens
		String requestBody = "IPN,1757600";

		*/
		////////////////////////////////////////////////////////////////////////////////////////
		
		PoligonoIrreg pol1 = new PoligonoIrreg();
		for(int i =0;i<3;i++)
		{
		int x =  (int)(Math.random()*10-10);
		int y =  (int)(Math.random()*10-10);
		pol1.añadeVertice(x,y);
		}
		byte[] serializado = SerializationUtils.serialize(pol1);
		
		do
		{
		serializado = Sender(serializado);
		Thread.sleep(1000);	
		}
		while(true);

		

	}
	
	private static byte[] Sender(byte[] serializado)
	{
		try{
		String ip = "localhost";	
		// Crear la solicitud POST
		HttpRequest request = HttpRequest.newBuilder()
		.POST(HttpRequest.BodyPublishers.ofByteArray(serializado))
		//.uri(URI.create("http://"+ip+":80/searchtoken"))
		.uri(URI.create("http://"+ip+":8080/task"))
		//.setHeader("Content-Type", "text/plain")
		.setHeader("Content-Type", "application/octet-stream")
		.setHeader("X-Debug", "true")
		.build();

		// Enviar la solicitud al servidor
		HttpResponse<byte[]> response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());

		// print response headers
		HttpHeaders headers = response.headers();
		headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

		// print status code
		System.out.println(response.statusCode());

		// print response body
		byte[] requestBytes = response.body();
		byte[] responseBytes = deserializa(requestBytes);
		return responseBytes;
		}catch(Exception ex)
		{
		System.out.println(ex.getStackTrace());
		}
		return serializado;
	}
	
	private static byte[] deserializa(byte[] serializado) {
		PoligonoIrreg objeto = null;
		objeto = (PoligonoIrreg)SerializationUtils.deserialize(serializado);
		System.out.println(objeto);
		int x =  (int)(Math.random()*10-10);
		int y =  (int)(Math.random()*10-10);
		objeto.añadeVertice(x,y);
		byte[] res_serializado = SerializationUtils.serialize(objeto);	
		return res_serializado;
	}
}

