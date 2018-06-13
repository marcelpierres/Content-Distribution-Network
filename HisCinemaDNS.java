package CPS706;
import java.io.*;
import java.net.*;

class HisCinemaDNS
{
   public static void main(String args[]) throws Exception
      {
	   	//===============DNS================
	   
	   //=====================================
         DatagramSocket serverSocket = new DatagramSocket(9877); // set port
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); //creates a new recieve packet of certain size
                 
                  serverSocket.receive(receivePacket); // recieves any incoming packet
                  String sentence = new String( receivePacket.getData()); // converts packet to a sting
                  System.out.println("RECEIVED: " + sentence); // reads packet to prompt
                  InetAddress IPAddress = receivePacket.getAddress(); // gets the IP address of the packets origin
                  int port = receivePacket.getPort(); // get the port address of packets origin
                 
                  String response ="Oh I know HERCDN DNS knows, go ask that server";
                  sendData = response.getBytes();// converts response to bytes
                  DatagramPacket newSendPacket =  new DatagramPacket(sendData, sendData.length, IPAddress, port); //creates a new packet to send back to local DNS
                  
                  serverSocket.send(newSendPacket); // sends packet back to IP and port location
               }
      }
}