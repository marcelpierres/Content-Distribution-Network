package CPS706;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.text.*;
import java.net.*;
public class HIS extends Thread {
	// need to implement TCP protocol

	//class variables
	private int PortNumber =40450;
	private ServerSocket serversocket;
	private boolean work=false;
	//main
	public static void main (String [] args) throws IOException, InterruptedException{
	// create variables	
		HIS server = new HIS();
		
	// run the server		
		server.server();
		Thread.sleep(120000);
	
		//stop server
		server.stopserver();
		
	}

	//starting the server
	public void server() throws IOException{
		 serversocket = new ServerSocket(PortNumber);
		this.start();
		//State what port the server started on
		System.out.println("Web server www.hiscinema.com started on port: "+ PortNumber);
		
		
	}
	
	//stopping the server
	public void stopserver(){
		//set server working as false to stop
		work = false;
		interrupt();
	}
	
	@Override
	public void run(){
		//set server working as true to run
		work = true;
		// display that server is waiting for a client to connect
		while (work==true){
		try {
		System.out.println("Waiting for Client to Connect...");
		
		// recieve connection
		Socket socket = serversocket.accept();	
		System.out.println("Just connected to " + socket.getRemoteSocketAddress());
		System.out.println("socket ");
		// Send the request to a thread to proccess
		multiRequest multirequest = new multiRequest(socket);
		multirequest.start();
		}
		// catch Input/Output exception
		catch(IOException e){
			e.printStackTrace();
		}
				
		}
		
	}
	
}

// thread that handles multiple request
class multiRequest extends Thread{
	
	// set the value of the socket
	private Socket socket;
    multiRequest( Socket socket )
    {
        this.socket = socket;
    }
	
    @Override
	public void run(){
    	try {
    		// get input and output to client
    	//PrintWriter out = new PrintWriter (socket.getOutputStream());
        //BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream()));
    		//other way of readying and writing
    		InputStreamReader IR = new InputStreamReader(socket.getInputStream());
    		BufferedReader BR = new BufferedReader(IR);
    		//==============
    		FileInputStream fl = null;
    	    BufferedInputStream bl = null;
    	    OutputStream ol = null;
    	    
    		//sending files
    		String file = "C:\\Users\\s8muhamm\\Desktop\\Testfile.txt"; //file location
    		// C:\\Users\\marce\\workspace\\CPS706Ver3\\src\\CPS706\\GoodMorning.mp4
    		//C:\\Users\\marce\\workspace\\CPS706Ver3\\src\\CPS706\\Testfile.txt
    		// create file
    		File sendfile = new File(file);
    		
    		byte [] fileseg  = new byte [(int)sendfile.length()]; // convert file into an array of bytes
    		
    		fl = new FileInputStream(sendfile);
    		
            bl = new BufferedInputStream(fl);
            
            bl.read(fileseg,0,fileseg.length);
            
            ol = socket.getOutputStream();
            
    		
         // sending files      			
			ol.write(fileseg,0,fileseg.length);
			ol.flush();
			
			
    		String msg  ;
    		
    		// read a recieved message into variable
    			// if message is not empty
    			if ((msg= BR.readLine()) != null){
    				System.out.println(msg); // output message on server terminal
    				//create a stream to output to client
    			PrintStream PS = new PrintStream(socket.getOutputStream());	
    			// tell client message has been recieved
    				PS.println("Message Recieved");
    				PS.println("www.hiscinema.com");
        			PS.println("Now Downloading...");
        			
        			
    			}
    			
    			
    			
    //=======================================================================		
        //welcome user
    	//out.println("Welcome to Server to www.hiscinema.com "+  socket.getRemoteSocketAddress());
        //out.flush();
        
        
       // String line = in.readLine();
        //System.out.println(line);
        
        //while( line != null && line.length() > 0 )
        //{
          //  out.println( "Message Recieved" );
            //out.flush();
           // line = in.readLine();
        //}
        
        //file send close connection
    			bl.close();
    			ol.close();
    			
    	// Close our connection
        IR.close(); //in
        BR.close(); //out
        socket.close();
        System.out.println( "www.hiscinema.com Closed Connection " );	
    	}
    	// catch Input/output exception
    	catch (IOException e){
    		e.printStackTrace();
    	}
    	
    	

        
	}
	
	
}
