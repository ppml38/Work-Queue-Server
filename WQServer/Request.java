import java.io.*;
import java.net.*;
import java.util.*;

class Request
{
String command;
String action;
String[] var;
String[] val;
int noofpairs=0;

public Request(String recd)
{
String[] result = recd.split("\\s");
command=result[0];

if(!result[1].equals("/"))
{
//String[] sec=result[1].split("\\?");
//action=sec[0];
action=result[1].substring(0,result[1].indexOf("?"));

StringTokenizer tk=new StringTokenizer(result[1].substring(result[1].indexOf("?")+1),"@$");
var=new String[tk.countTokens()];
val=new String[tk.countTokens()];
while(tk.hasMoreTokens())
{
String t=tk.nextToken();
if(t.indexOf("~")!=-1)
{
String[] result1=t.split("~");
var[noofpairs]=result1[0];
val[noofpairs]=result1[1];
noofpairs+=1;

}}
noofpairs+=1;
}
else
{
action="/";
}


}

}