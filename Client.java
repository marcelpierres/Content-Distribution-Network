package CPS706;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.text.*;
import java.net.*;
import java.nio.*;
public class Client {
	
		// main
	public static void main(String[] args) throws IOException, InterruptedException {
		// declare variables
		String site="", num="", vid="";
		int PortNumber=0;
		int vidNumber;
		Client client = new Client();
		
		// ability to take in input from the user
		BufferedReader br= new BufferedReader (new InputStreamReader(System.in));
		
		// Welcome user
		System.out.println("Welcome to the Dummy Content Distribution Service \n ");
		// delay
		Thread.sleep(2000);
		System.out.println("Now Logging Into your Computer \n ");
		// delay
		Thread.sleep(2000);
		System.out.println("Opening Browser");
	
		//pretend loading screen to open browser
		for (int i=0; i<3; i++){	
			// delay
		Thread.sleep(1000);
		System.out.print(". ");
	}
		
		
		// display header of browser
		System.out.println("\n");
		System.out.println("============================================");
		System.out.println("           BROWSER Version 706");
		System.out.println("============================================ \n");
		System.out.println("Please Insert Your Port Address (Local server Port 40450 or 1)");
		
		//take in portnumber from user
		num = br.readLine();
		
		// convert string to integer
		PortNumber = Integer.parseInt(num);
		
		
		System.out.println("Please Insert The Web Address You Wish to Visit");
			
		//Take in website from user
		site = br.readLine();
		
		//test input
		System.out.println("You entered "+ site);
		
		//select video
		System.out.println("Which video do you want: 1, 2, 3, 4, 5?");
		vid = br.readLine();
		
		vidNumber = Integer.parseInt(vid);
		
		// call the server
		client.server(site,PortNumber);
		
		//call the DNS Server for location
		site =client.dns();
		PortNumber= 40451;
		
		// Call the Webserver to download
		client.server2(site,PortNumber,vidNumber);
		
	/*	
		// create the client (Prof already gave us our PortNumber which is) 40450
		Socket MyClient;
	    MyClient = new Socket(site, PortNumber);
			    
		*/
		
	
		
		
	}

	// communicate with server	
	public  void server (String site, int PortNumber) throws IOException{
		// create the client (Prof already gave us our PortNumber which is) 40450
		Socket MyClient;
	    MyClient = new Socket(site, PortNumber);	
	    
	    //ability to recieve files
	    int filesze = 21474836;
	    String file = "c:/Users/s8muhamm/Desktop/Downloadedfile.txt"; 
	    
	    int bytesRead;
	    int current = 0;
	    FileOutputStream fl = null;
	    BufferedOutputStream bl = null;
	    
	    
	    byte [] mybytearray  = new byte [filesze];
	      InputStream is = MyClient.getInputStream();
	      fl = new FileOutputStream(file);
	      bl = new BufferedOutputStream(fl);
	      
	    // create streams to read and write from server
	    //PrintStream out = new PrintStream( MyClient.getOutputStream() );
	    //PrintWriter out = new PrintWriter (MyClient.getOutputStream());
        //BufferedReader in = new BufferedReader( new InputStreamReader( MyClient.getInputStream() ) );
		
        //Outputs request from client to server in string to output
       // out.println("GET /about.html HTTP/1.1");
        //out.println("Host: " + site);
        //out.println("");
        //out.flush();	
        
       // out.println( "GET " + site + " HTTP/1.1" );
       // out.println("HI");
        
		//String outputStr;
		
		// print output of response
		//while((outputStr = in.readLine()) != null){
           // System.out.println(outputStr);
        //}
//=========================================================================            
            // Tell Server you have succefully connected
	    PrintStream PS = new PrintStream(MyClient.getOutputStream());	//ability to send to server
			PS.println("Successfully Connect to Server");
            // ability to output and read sockets
			InputStreamReader IR = new InputStreamReader(MyClient.getInputStream());
    		BufferedReader BR = new BufferedReader(IR);
    		BufferedReader user= new BufferedReader (new InputStreamReader(System.in));
    		String msg; //string variable that contains read data
    		//==========================================
    		bytesRead = is.read(mybytearray,0,mybytearray.length);
    	      current = bytesRead;	   
    	     //===================================================
    	      
    		
    		
    		//downloading
    		do {
    	         bytesRead = is.read(mybytearray, current, (mybytearray.length-current)); // read the bytes
    	         if(bytesRead >= 0) current += bytesRead; // if curent byte is biggere than 0 then  move on to the next one
    	      } while(bytesRead > -1);
    		
    		bl.write(mybytearray, 0 , current); // write the byte
    	      bl.flush(); //clear the stream
    	      System.out.println("File " + file
    	          + " downloaded (" + current + " bytes read)"); // output the amount of bytes downloaded
    		 
    		//file send close connection
			bl.close();
			//fl.close();
			
			
			//while output from server to client isnt empty
    		while((msg = BR.readLine()) != null){
    			//output the message
    	           System.out.println(msg);
    	                     
    	        }
    		
		// Close our streams
        IR.close();
        BR.close();
        MyClient.close();
		
	}
//=========================================================================================================
	
	// communicate with server	
	public  void server2 (String site, int PortNumber, int vidselect) throws IOException, FileNotFoundException, InterruptedException{
		// create the client (Prof already gave us our PortNumber which is) 40450
		Socket MyClient;
	    MyClient = new Socket(site, PortNumber);	
	    
	    //ability to recieve files
	    int filesze = 21474836;
	    String file = "c:/Users/s8muhamm/Desktop/Downloadedfile.mp4"; 
	    
	    int bytesRead;
	    int current = 0;
	    FileOutputStream fl = null;
	    BufferedOutputStream bl = null;
	    
	    
	    byte [] mybytearray  = new byte [filesze];
	      InputStream is = MyClient.getInputStream();
	      fl = new FileOutputStream(file);
	      bl = new BufferedOutputStream(fl);
	      
	    // create streams to read and write from server
	    //PrintStream out = new PrintStream( MyClient.getOutputStream() );
	    //PrintWriter out = new PrintWriter (MyClient.getOutputStream());
        //BufferedReader in = new BufferedReader( new InputStreamReader( MyClient.getInputStream() ) );
		
        //Outputs request from client to server in string to output
       // out.println("GET /about.html HTTP/1.1");
        //out.println("Host: " + site);
        //out.println("");
        //out.flush();	
        
       // out.println( "GET " + site + " HTTP/1.1" );
       // out.println("HI");
        
		//String outputStr;
		
		// print output of response
		//while((outputStr = in.readLine()) != null){
           // System.out.println(outputStr);
        //}
//=========================================================================            
            // Tell Server you have succefully connected
	    PrintStream PS = new PrintStream(MyClient.getOutputStream());	//ability to send to server
		//	PS.println("Successfully Connect to Server");
            
	    	PS.println(vidselect);
	    
			InputStreamReader IR = new InputStreamReader(MyClient.getInputStream());
    		BufferedReader BR = new BufferedReader(IR);
    		BufferedReader user= new BufferedReader (new InputStreamReader(System.in));
    		String msg;
    		//==========================================
    		bytesRead = is.read(mybytearray,0,mybytearray.length);
    	      current = bytesRead;	   
    	     //===================================================
    	      
    		
    		
    		//downloading
    		do {
    	         bytesRead =
    	            is.read(mybytearray, current, (mybytearray.length-current));
    	         if(bytesRead >= 0) current += bytesRead;
    	      } while(bytesRead > -1);
    		
    		bl.write(mybytearray, 0 , current);
    	      bl.flush();
    	      System.out.println("File " + file
    	          + " downloaded (" + current + " bytes read)");
    		 
    		//file send close connection
			bl.close();
			//fl.close();
			
			
			//while output from server to client isnt empty
    		while((msg = BR.readLine()) != null){
    			//output the message
    	           System.out.println(msg);
    	                     
    	        }
    		
		// Close our streams
        IR.close();
        BR.close();
        MyClient.close();
		
		
	}
	
	

	// communicate with LDNS
	public String dns () throws IOException,  NumberFormatException {
		
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("141.117.232.24");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		String sentence = "Go here to find the the SERVER!!!!!!! HURRY!!!"; // 
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		
		// create a data packet to recieve responce from local DNS server
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		String response = new String(receivePacket.getData()); //send back to main client the file location
		System.out.println("FROM SERVER iClient:" + response);
		//conver to integer
		int finalans = 40451;
		String finalresp = response.substring(1);
		clientSocket.close();

		
		return finalresp;
		
	}
	
	
	
	
	
}
