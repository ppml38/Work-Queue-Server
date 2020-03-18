import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.reflect.*;

class RequestProcessor
{
Request request;
String response=new String("error");

public RequestProcessor(Request rq)
{
request=rq;
if(rq.action.equals("/GETID"))
{
getid();
}
else
{
if(rq.action.equals("/ADD"))
{
add();
buildindex();
response="success";
}
else
{
	if(rq.action.equals("/UPDATE"))
	{update();response="success";}
	else
	{
		if(rq.action.equals("/DELETE"))
		{delete();response="success";}
		else response="invalid";
	}
}
}

}

public String getResponse()
{
return response;
}

public void add()
{
/*id
assignto
task

color
comment*/

try
{
FileWriter wrtlog = new FileWriter("Tasks/"+request.val[0]+"_R_"+request.val[1]);
wrtlog.write(request.val[2]);
wrtlog.write(System.getProperty("line.separator"));
wrtlog.write("Comments / Remarks");
wrtlog.close();
}
catch(Exception e)
{
e.printStackTrace();new Logger(e);
}


}
public void getid()
{
int a=0;
try{
//Scanner sc=new Scanner(new File("name.txt"));
BufferedReader br=new BufferedReader(new FileReader(new File("name.txt")));
String n=br.readLine();
//String n=sc.nextLine();
br.close();
a=Integer.parseInt(n.substring(1));
a+=1;
FileWriter fw=new FileWriter(new File("name.txt"));
fw.write("T"+a);
fw.close();

}catch(Exception e){ e.printStackTrace();new Logger(e);}

response="T"+a;
}

public void update()
{
String id=request.val[0],color=request.val[1],task=request.val[2],comment=request.val[3];
try{
File[] filesList = new File("Tasks/").listFiles();
        for(int t=0;t<Array.getLength(filesList);t++){
		File f = filesList[t];
            if(f.isFile()){
			    if(f.getName().indexOf(id)==0)
			    {
				FileWriter wr=new FileWriter(f);
				wr.write(task);
				wr.write(System.getProperty("line.separator"));
				wr.write(comment);
				wr.close();
				f.renameTo(new File("Tasks/"+id+"_"+color+"_"+f.getName().split("_")[2]));
			   }
			}
			}

}
catch(Exception e){e.printStackTrace();new Logger(e);}
buildindex();
}


public void delete()
{
String id=request.val[0];
try{
	File[] filesList = new File("Tasks/").listFiles();
        for(int t=0;t<Array.getLength(filesList);t++){
		File f = filesList[t];
            if(f.isFile())
		if(f.getName().indexOf(id)==0)
			f.renameTo(new File("Tasks/"+"D"+f.getName().substring(1)));
		}
			
}
catch(Exception e)
{e.printStackTrace();new Logger(e);}

buildindex();
}


public void buildindex()
{
try{
//Scanner sscn=new Scanner(new File("Pages/start.html"));
//Scanner escn=new Scanner(new File("Pages/end.html"));
BufferedReader sscn=new BufferedReader(new FileReader(new File("Pages/start.html")));
BufferedReader escn=new BufferedReader(new FileReader(new File("Pages/end.html")));

FileWriter indwr=new FileWriter(new File("Pages/index.html"));
String id,assignto,task,color="red",comment;

String s;
while((s=sscn.readLine())!=null)
{
indwr.write(s);
}sscn.close();

File[] filesList = new File("Tasks/").listFiles();
File[] newList= new File[100];
int count=0;
        for(int t=0;t<Array.getLength(filesList);t++){
		File n = filesList[t];
    if(n.getName().indexOf("_")!=-1&n.getName().substring(0,1).equals("T"))
	{
	newList[count]=n;
	count++;

	}
}
for (int i = 0; i < count; i++) 
        {
            for (int j = i + 1; j < count; j++) 
            {
                if (i < j) 
                {
                    File temp = newList[i];
                    newList[i] = newList[j];
                    newList[j] = temp;
                }
            }
        }

        for(int h=0;h<count;h++){
		File f=newList[h];
            if(f.isFile()){
			    if(f.getName().indexOf("_")!=-1)
			    {
				id=f.getName().split("_")[0];
				if(f.getName().split("_")[1].equals("R"))color="red";
				if(f.getName().split("_")[1].equals("G"))color="green";
				if(f.getName().split("_")[1].equals("B"))color="orange";
				assignto=f.getName().split("_")[2];
				BufferedReader r=new BufferedReader(new FileReader(f));
				task=java.net.URLDecoder.decode(r.readLine());
				comment=java.net.URLDecoder.decode(r.readLine());
				r.close();
				indwr.write("<div class='redder' style=\"background-color: "+color+"\" id='"+id+"' onmouseover=\"showctl('"+id+"')\" onmouseleave=\"hidectl('"+id+"')\"><table width='100%'><tr><td class='wt' id='tsk"+id+"' colspan='2'>"+task+"</td><td align='center' rowspan='2' width='10%' class='ctl' id='ctl"+id+"'><button onclick=\"pending('"+id+"')\">Pending</button><br><button onclick=\"done('"+id+"')\">Closed</button><button onclick=\"rmv('"+id+"')\">Remove</button></td></tr><tr><td contentEditable='true' class='lower' id='cmnt"+id+"'>"+comment+"</td><td align=right>Assigned to :"+assignto+"</td></tr></table></div>");
			    }

			   }
			}

while((s=escn.readLine())!=null)
{
indwr.write(s);
}escn.close();
indwr.close();
}catch(Exception e){e.printStackTrace();new Logger(e);}
}

} 