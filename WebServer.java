/*
 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 */

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.util.Random;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.Executors;

public class WebServer {
    private static final String TASK_ENDPOINT = "/task";
    private static final String STATUS_ENDPOINT = "/status";
    private static final String SEARCH_ENDPOINT = "/searchtoken";


    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        int serverPort = 8080;
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        WebServer webServer = new WebServer(serverPort);
        webServer.startServer();

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }

    public WebServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        HttpContext taskContext = server.createContext(TASK_ENDPOINT);
        HttpContext searchContext = server.createContext(SEARCH_ENDPOINT);


        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
        searchContext.setHandler(this::handleSearchRequest);

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }
    
//-------------------------------- SEARCH REQUEST---------------------------------------------------------------------------------

	private void handleSearchRequest(HttpExchange exchange) throws IOException {
		if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
		    exchange.close();
		    return;
		}
	        
	        boolean isDebugMode = false;
	        Headers headers = exchange.getRequestHeaders();
		if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
		    isDebugMode = true;
		}
		
		long startTime = System.nanoTime();
		byte[] requestBytes = exchange.getRequestBody().readAllBytes();
		byte[] responseBytes = searchTokenResponse(requestBytes);
		long finishTime = System.nanoTime();
		long Tiempo_Nano = finishTime - startTime;
	
        if (isDebugMode) {
        
		long tiempoEnSegundos = Tiempo_Nano / 1_000_000_000;
		long tiempoEnMilisegundos = (Tiempo_Nano % 1_000_000_000) / 1_000_000;
		String debugMessage = String.format("La operación tomó %d nanosegundos = %d segundos con %d milisegundos.",
		Tiempo_Nano, tiempoEnSegundos, tiempoEnMilisegundos);
		exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
		
        }

        sendResponse(responseBytes, exchange);
	
	}
	
	private byte[] searchTokenResponse(byte[] requestBytes) {

		String bodyString = new String(requestBytes);
        	String[] partesString = bodyString.split(",");
		
		return String.format(searchToken(partesString[0],Integer.parseInt(partesString[1]))).getBytes();

	}

	private String searchToken(String token,int n) {
	
		int Cuantas = BuscaToken(token,n);
		return "El token fue encontrado "+Cuantas+" veces.";
	  
	}
	
	public static int BuscaToken(String token,int n)
	{
		char[] ArregloToken = token.toCharArray();
		char[] cadenota = new char[n*4];
		Random random = new Random();
		/*
		cadenota[0] = (char)('A');	CADENA DE PRUEBA
		cadenota[1] = (char)('A');
		cadenota[2] = (char)('A');
		cadenota[3] = ' ';
		*/
		for (int i = 1; i < n; i++) {
			cadenota[i*4] = (char)(random.nextInt(26) + 'A');
			cadenota[i*4+1] = (char)(random.nextInt(26) + 'A');
			cadenota[i*4+2] = (char)(random.nextInt(26) + 'A');
			cadenota[i*4+3] = ' ';
			System.out.print(cadenota);
		}
		// Busca la subcadena en la cadena gigante y contabiliza el número de apariciones
		int count = 0;
		for (int i = 0; i < n*4-2; i++) {
			if (cadenota[i] == ArregloToken[0] && cadenota[i+1] == ArregloToken[1] && cadenota[i+2] == ArregloToken[2]) {
			count++;
			//System.out.println("La subcadena aparece en la posición " + i + " de la cadenota.");
		}
		}
		return count;
		//System.out.println("El número de apariciones de la subcadena es " + count + ".");
	}
	
//-------------------------------- TASK REQUEST---------------------------------------------------------------------------------


    private void handleTaskRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        Headers headers = exchange.getRequestHeaders();
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            String dummyResponse = "123\n";
            sendResponse(dummyResponse.getBytes(), exchange);
            return;
        }

        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }

        long startTime = System.nanoTime();

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = calculateResponse(requestBytes);

        long finishTime = System.nanoTime();
        
        long Tiempo_Nano = finishTime - startTime;
      

        
        if (isDebugMode) {
		long tiempoEnSegundos = Tiempo_Nano / 1_000_000_000;
		long tiempoEnMilisegundos = (Tiempo_Nano % 1_000_000_000) / 1_000_000;
		String debugMessage = String.format("La operación tomó %d nanosegundos = %d segundos con %d milisegundos.",
		Tiempo_Nano, tiempoEnSegundos, tiempoEnMilisegundos);
		exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }

        sendResponse(responseBytes, exchange);
    }
	

    private byte[] calculateResponse(byte[] requestBytes) {
        String bodyString = new String(requestBytes);
        String[] stringNumbers = bodyString.split(",");

        BigInteger result = BigInteger.ONE;

        for (String number : stringNumbers) {
            BigInteger bigInteger = new BigInteger(number);
            result = result.multiply(bigInteger);
        }

        return String.format("El resultado de la multiplicación es %s\n", result).getBytes();
    }

    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        String responseMessage = "El servidor está vivo\n";
        sendResponse(responseMessage.getBytes(), exchange);
    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }
    
    



	

    
}
