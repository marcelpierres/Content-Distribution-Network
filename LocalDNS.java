package CPS706;

import java.io.*;
import java.net.*;


public class LocalDNS {
	public static void main(String args[]) throws Exception
    {
		//===============DNS================
		
		
	  //=====================================
		
       DatagramSocket serverSocket = new DatagramSocket(9876);
          byte[] receiveData = new byte[1024];
          byte[] sendData = new byte[1024];
          while(true)
             {
        	  //recived  packet from CLient and saved port and IP address
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String( receivePacket.getData());
                System.out.println("RECEIVED: " + sentence);
                InetAddress IPAddress = receivePacket.getAddress(); // saved sender IP
                int port = receivePacket.getPort();// saved sender port
                
               
                
                
                //find out where to go from the recieved pakcet... thank god
                // takes info recieved from client and asks HISCinemaDNS(which is specified in the recieved content sent to the client from HIScenemaWeBServer)
                DatagramSocket newClientSocket = new DatagramSocket(); // creates new UDP socket
                String search = "Hey is this HisCinema? Im looking for HerCDN... Do you know where it is?"; // 
                
                
                InetAddress HISIPAddress = InetAddress.getByName("141.117.232.23"); // IP address for HISDNS
                //converts the info into packets of bytes              
                sendData = search.getBytes();
                DatagramPacket newSendPacket = new DatagramPacket(sendData, sendData.length, HISIPAddress, 9877); //creates new UDP packet
                newClientSocket.send(newSendPacket); // sends new packet
                
                //recieve data from the server about where to go next
                DatagramPacket newReceivePacket = new DatagramPacket(receiveData, receiveData.length); // creates a recieve packet
                newClientSocket.receive(newReceivePacket); //recieves incoming packet
                String newModifiedSentence = new String(newReceivePacket.getData()); // turns byte packet to string
                System.out.println("FROM SERVER:" + newModifiedSentence); // reads out message recieved oh where to go...
                System.out.println("The Port: " + newSendPacket.getPort()); // as well as port number from the one who sent it...
                newClientSocket.close(); // close this socket
                
                
                //create a new datagram socket
                DatagramSocket herClientSocket = new DatagramSocket();
                String search2 = "Hey is this HERCDN DNS? Im tryin to find distribution server herCDN."; // 
        		
                //converts the info into packets of bytes              
                sendData = search2.getBytes();
                
                InetAddress HERIPAddress = InetAddress.getByName("141.117.232.18"); // IP address for HERDNS
                //with past knowledge from other server 
                DatagramPacket herSendPacket = new DatagramPacket(sendData, sendData.length, HERIPAddress, 9874);
                
                herClientSocket.send(herSendPacket); //sends packet to herCDN DNS
                byte[] receiveData1 = new byte[1024]; // testing
                DatagramPacket herReceivePacket = new DatagramPacket(receiveData1, receiveData1.length);
                herClientSocket.receive(herReceivePacket);
                String herModifiedSentence = new String(herReceivePacket.getData()); // use this saved data  and send it to client of known address
                System.out.println("FROM SERVER:" + herModifiedSentence);
                System.out.println("The Port: " + herSendPacket.getPort());
              
                
                //System.out.println(herReceivePacket.getAddress());
                String thetruth = (herReceivePacket.getAddress()).toString();
                
                // response back to client... implement last
                byte[] sendData1 = new byte[1024];
               // sendData1 = herModifiedSentence.getBytes(); // turn last server response to bytes
                sendData1 = thetruth.getBytes(); // turn last server response to bytes
                
                DatagramPacket sendPacket =   new DatagramPacket(sendData1, sendData1.length, IPAddress, port); // send info back to client
                serverSocket.send(sendPacket); //here is the location of the files
                
                
                herClientSocket.close();
                
                
                
               
                
                
               
             }
    }
}
