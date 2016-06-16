package lunarNetwork;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.StringTokenizer;

public class Client {
	/** 
	 * Punkt połączenia z serwerem gry
	 * */
	Socket socket = null;
	/**
	 * Sieciowy strumień wyjściowy
	 */
	PrintWriter output = null;
	/**
	 * Sieciowy strumień wejsciowy
	 */
	BufferedReader input = null;
	
	/*public Client()
	{
		
	}*/
	/**
	 * Metoda inicjalizująca połączenie z serwerem
	 * @throws UnknownHostException wyjątek gdy nie znany jest host
	 * @throws IOException wyjątek gdy są błędy z strumieniem wejściowym/wyjściowym 
	 */
	public void connect() throws UnknownHostException, IOException
	{
		socket = new Socket("localhost", 5555);
		output = new PrintWriter(socket.getOutputStream(), true);
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	/**
	 * Metoda rozłączająca połączenie z serwerem i zamykająca strumienie wejściowe i wyjściowe
	 * @throws IOException wyjątek gdy obsługa strumieni jest niemożliwa
	 */
	public void disconnect() throws IOException
	{
		output.close();
		input.close();
		socket.close();
	}
	/**
	 * Metoda wysyłająca zapytanie do serwera
	 * @param command treść zapytania
	 * @return "Client: treść zapytania" - do wyświetlania w konsoli
	 */
	public String send(String command)
	{
		output.println(command);
		return "Client: " + command;
	}
	/**
	 * Metoda zwracająca odpowiedź z serwera
	 * @return odpowiedź z serwera
	 * @throws IOException wyjątek gdy nie możliwa jest obsługa strumienia wejściowego
	 */
	public String getAnswer() throws IOException
	{
		return input.readLine();
	}
	/**
	 * Metoda pobierająca dane poziomu
	 * @param level numer poziomu
	 */
	public void getLevel(int level)
	{
		try {
				connect();
				System.out.println(send("loadlevel " + level));
				StringTokenizer answerToken = new StringTokenizer(getAnswer()); 
				
				String imgPath = null;
				int bonusNo;
				double [] x , y;
				double landingsx, landingsy, gravity, maxVx, maxVy;
				byte [] image_bytes;
				if(answerToken.nextToken().equals("sendlevel"))
				{
					imgPath = answerToken.nextToken();
					gravity = Double.parseDouble(answerToken.nextToken());
					bonusNo = Integer.parseInt(answerToken.nextToken());
					maxVx = Double.parseDouble(answerToken.nextToken());
					maxVy = Double.parseDouble(answerToken.nextToken());
					landingsx = Double.parseDouble(answerToken.nextToken());
					landingsy = Double.parseDouble(answerToken.nextToken());
				}
				else throw new Exception("Bledna odpowiedz serwera");
				 String answer = getAnswer();
				if(getAnswer().equals("map coordinates"))
				{
					String [] xStr  = getAnswer().split(",");
					String [] yStr = getAnswer().split(",");
				}
				else throw new Exception("Bledna odpowiedz serwera");
				
				if(getAnswer().equals("image bytes start"))
				{
					String received;
					String imgBase64 = "";
					while(!(received = getAnswer()).equals("image bytes end"))
					{
						imgBase64 += received;
						
					}
					image_bytes = Base64.getDecoder().decode(imgBase64);
				}
				
				disconnect();
			
		} catch (UnknownHostException e) {
			System.out.println("Nieznany host!");
		} catch (IOException e) {
			System.out.println("Blad serwera " + socket.getLocalPort() + " lub blad pobierania danych");
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		
		
	}

}
