package CPS706;


import java.io.*;
import java.net.*;
class HerCDNDNS
{
   public static void main(String args[]) throws Exception
      {
	   //===============DNS================
	   
	   //=====================================
	   
         DatagramSocket serverSocket = new DatagramSocket(9874);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  String response = "2";
                  sendData = response.getBytes();
                  DatagramPacket newSendPacket =     new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(newSendPacket);
               }
      }
}