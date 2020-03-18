function showloading()
{
$("#hold").css("visibility","visible");
}
function hideloading()
{
$("#hold").css("visibility","hidden");
}

function dontrefresh()
{
return "No No Noooooo !!\n\nIf you Refresh/Close this You will lose Work Queue.\nSo, Dont Refresh/Close this Dude, Please !!";
}

function ShowLocalDate()
{
var dNow = new Date();
var localdate= (dNow.getMonth()+1) + '/' + dNow.getDate() + '/' + dNow.getFullYear() + ' ' + dNow.getHours() + ':' + dNow.getMinutes();
return localdate;
}


function addtaskentry()
{

if($.trim($("#task").val())!="")
{
showloading();
$.get("GETID","a~a",function(data){
					data=$.trim(data);
					var id=data;
					$.get("ADD",
					"id~"+data+"@$assignto~"+$("#assignto").val()+"@$task~"+$("#task").val(),
					function(data)
					{
						data=$.trim(data);
						if(data=="success")
						{
						hideloading();
						$("#sheet").prepend(newtaskentry(id));
						$("#"+id).css("height","0px");
						$("#"+id).animate({height: "63px"});
						//$("#"+id).slideUP();
						$("#task").val("");
						}
						else alert("Error connecting Server");
					});
				   });
}

}



function done(i)
{
showloading();
//$("#end"+i).html(""+ShowLocalDate()+"");
$.get("UPDATE","id~"+i+"@$color~G@$task~"+$("#tsk"+i).html()+"@$comment~"+$("#cmnt"+i).html(),function(data){data=$.trim(data);if(data=="success"){hideloading();$("#"+i).css("backgroundColor","green");}else alert("Error connecting Server");});
}
function pending(i)
{
showloading();
$.get("UPDATE","id~"+i+"$@color~B@$task~"+$("#tsk"+i).html()+"@$comment~"+$("#cmnt"+i).html(),function(data){data=$.trim(data);if(data=="success"){hideloading();$("#"+i).css("backgroundColor","orange");}else alert("Error connecting Server");});
//$("#"+i).css("backgroundColor","pink");
//$("#end"+i).html("Not yet");
}
function rmv(i)
{

if(confirm("Are you sure to remove this ?"))
{
showloading();
$.get("DELETE","id~"+i,function(data){data=$.trim(data);if(data=="success"){hideloading();$("#"+i).animate({height: "0px"}, function(){ $("#"+i).remove(); });}else alert("Error connecting Server");});
}
}


function showctl(i)
{
$("#ctl"+i).css("visibility","visible");
}

function hidectl(i)
{
$("#ctl"+i).css("visibility","hidden");
}

function assignevents()
{

$("#add").click(
function()
{
addtaskentry();
}
);




}

