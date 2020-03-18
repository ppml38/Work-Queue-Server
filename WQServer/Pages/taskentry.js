function newtaskentry(id)
{
var a="<div class='redder' id='"+id+"' onmouseover=\"showctl('"+id+"')\" onmouseleave=\"hidectl('"+id+"')\" ><table width='100%'><tr><td class='wt' id='tsk"+id+"' colspan='2'>"+$("#task").val()+"</td><td align='center' rowspan='2' width='10%' class='ctl' id='ctl"+id+"'><button onclick=\"pending('"+id+"')\">Pending</button><br><button onclick=\"done('"+id+"')\">Closed</button><button onclick=\"rmv('"+id+"')\">Remove</button></td></tr><tr><td contentEditable='true' class='lower' id='cmnt"+id+"'>Comments / Remarks</td><td align=right>Assigned to :"+$("#assignto").val()+"</td></tr></table></div>";

return a;
}


/*
<tr>\
<td align='right'>\
<table class='lower'>\
<tr><td><b>Entered:</b></td><td contentEditable='true'>"+ShowLocalDate()+"</td><td><b>Finished:</b></td><td contentEditable='true' id='end"+(i-1)+"'>Not yet</td></tr>\
</table>\
</td>\
</tr>\
*/