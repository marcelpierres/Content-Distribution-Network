package CPS706;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.text.*;
import java.net.*;

// to make threading in here work we have to run it in parallel.
// in other words we need to implement inter

public class HER extends Thread {
	// need to implement TCP protocol
//hiiiii
	//class variables
	private int PortNumber =40451;
	private ServerSocket serversocket;
	private boolean work=false;
	//main
	public static void main (String [] args) throws IOException, InterruptedException{
	// create variables	
		HER server = new HER();
		
	// run the server		
		server.serverr();
		Thread.sleep(120000);
	
		//stop server
		server.stopserver();
		
	}

	//starting the server
	public void serverr() throws IOException{
		 serversocket = new ServerSocket(PortNumber);
		this.start();
		//State what port the server started on
		System.out.println("Web server www.HERCDN.com started on port: "+ PortNumber);
		
		
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
		// Send the request to a thread to proccess
		multiRun multirequest = new multiRun(socket);
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
class multiRun extends Thread{
	
	// set the value of the socket
	private Socket socket;
    multiRun( Socket socket )
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
        		//========================
        			//read which video we want
        		String msg = BR.readLine();
        			// convert choice to int
        		int vidchoice = Integer.parseInt(msg);
        		 
        		//==============
        		FileInputStream fl = null;
        	    BufferedInputStream bl = null;
        	    OutputStream ol = null;
        
        	    //====================================
        		//sending files
        	    String file="";
        	   
        	    if ( vidchoice == 1)
         	   {
         		file = "H:\\Desktop\\CPS706\\GoodMorning.mp4"; //file location
         	   }
         	   if ( vidchoice == 2)
         	   {
         		file = "H:\\Desktop\\CPS706\\ComeAsYouAre.mp4"; //file location
         	   }
       	   if ( vidchoice == 3)
         	   {
         		file = "H:\\Desktop\\CPS706\\DayNNite.mp4"; //file location
         	   }
        	   if ( vidchoice == 4)
         	   {
         		file = "H:\\Desktop\\CPS706\\InTheEnd.mp4"; //file location
        	   		}
        	   if ( vidchoice == 5)
         	   {
         		file = "H:\\Desktop\\CPS706\\OneMoreTime.mp4"; //file location
        	   		}
        	   
        		//============================================
        	   
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
    			
    			
        //		String msg  ;
        		
        		// read a recieved message into variable
        			// if message is not empty
        	//		if ((msg= BR.readLine()) != null){
        				System.out.println(msg); // output message on server terminal
        				//create a stream to output to client
        			PrintStream PS = new PrintStream(socket.getOutputStream());	
        			// tell client message has been recieved
        			PS.println("Message Recieved");
    				PS.println("YOU FOUND ME!!!");
    				PS.println("Take MY CONTENT!!!!.... YOU WON!!!");
    				
            			
        	//		}
        			
        			
        			
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
            System.out.println( "www.HERCDN.com Closed Connection " );	
        	}
        	// catch Input/output exception
    	catch (IOException e){
    		e.printStackTrace();
    	}
    	
    	

        
    	}
	
	
	

	
}
