
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.lang.Object;
import java.net.http.HttpRequest.BodyPublishers;

public class WebClient{

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
	/*
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/status"))
                .setHeader("X-Debug", "true") // add request header
                .build();
	*/
	
	// Crear el cuerpo de la solicitud con la cadena y el número de tokens
        String requestBody = "IPN,17576000";

        // Crear la solicitud POST
        HttpRequest request = HttpRequest.newBuilder()
                .POST(BodyPublishers.ofString(requestBody))
                .uri(URI.create("http://localhost:8080/searchtoken"))
                .setHeader("Content-Type", "text/plain")
                .setHeader("X-Debug", "true")
                .build();

        // Enviar la solicitud al servidor
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

    }

}
