import java.io.*;
import java.net.*;
import java.util.*;

class ConnectionHandler implements Runnable
{

Socket socket=null;

PrintWriter      outToClient;
BufferedReader   inFromClient;

String clientSentence;

public ConnectionHandler(Socket sct)
{
socket=sct;
}

public void run()
{
try{
inFromClient=new BufferedReader(new InputStreamReader(socket.getInputStream()));
outToClient=new PrintWriter(socket.getOutputStream(), true);
clientSentence=inFromClient.readLine();
new Logger(clientSentence,socket);
if(clientSentence.indexOf("?")!=-1)
{
	outToClient.println(new RequestProcessor(new Request(clientSentence)).getResponse());
	outToClient.println("\r\n");
}
else
send(clientSentence.split("\\s")[1]);
socket.close();
}catch(Exception e){e.printStackTrace();new Logger(e);}

}

public void send(String name)
{
	File fl;
	//Scanner opnScanner;
	BufferedReader opnScanner;

	if(name.equals("/")){
	fl=new File("Pages/index.html");
	}
	else{
	fl=new File("Pages/"+name.substring(1));
	}

	if(fl.exists())
	{
	try
	{

		opnScanner = new BufferedReader(new FileReader(fl));
		String s;
		while((s = opnScanner.readLine()) != null)
		{
                  outToClient.println(s);
		}
		opnScanner.close();
	        outToClient.println("\r\n");

	}
	catch(Exception e)
	{
		e.printStackTrace();
		new Logger(e);
	}
	}
	else
	{
		outToClient.println("<html><body>Error : \" Invalid URL \"<br>PLease Contact Prakash Maria Liju P (886599)</body></html>");
		outToClient.println("\r\n");
	}
	
}

}