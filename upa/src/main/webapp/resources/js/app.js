function addDateToLastDate(){
	var datepicker1 = document.getElementById("firstdatepicker").value;
	var billingperiodselect = document.getElementById("billingperiodselect").value;
	//https://stackoverflow.com/questions/3818193/how-to-add-number-of-days-to-todays-date
	var date = new Date(datepicker1);

	if(billingperiodselect == 1){
		//once a month
		date = dateformat(date,0,1,-1);
	}else if(billingperiodselect == 2){
		//twice a month
		date = dateformat(date,0,0,14);
	}else if(billingperiodselect == 3){
		//bi-weekly
		date = dateformat(date,0,0,13);
	}else if(billingperiodselect == 4){
		//weekly
		date = dateformat(date,0,0,6);
	}else if(billingperiodselect == 5){
		//daiily
		date = dateformat(date,0,0,1);
	}
	document.getElementById("lastdatepicker").value = date;
}

function dateformat(date,y,m,d){
	//https://jsfiddle.net/taditdash/8FHwL/
	var newdate = date;
    newdate.setYear(newdate.getFullYear()+y);    
    newdate.setMonth(newdate.getMonth()+m);
    newdate.setDate(newdate.getDate()+d);    
   	var yyyy = newdate.getFullYear();
   	var mm = newdate.getMonth()+1; // getMonth() is zero-based
   	var dd  = newdate.getDate();
   	var newval = mm+"/"+dd+"/"+yyyy; // Leading zeros for mm and dd
   return newval;
}

function testing() {
	alert( "test" );
	if ("c" + "a" + "t" === "cat") {
		document.writeln("Same");
	} else {
		document.writeln("Not same");
	}
}

function myFunction( test )
{alert( test );}


function hello() {
	alert("hello javatpoint user");
}

function getCurrentDateString() {
	var d = new Date();
	var curr_min = d.getMinutes();
	var curr_hour = d.getHours();
    var curr_date = d.getDate();
    var curr_month = d.getMonth() + 1; //Months are zero based
    var curr_year = d.getFullYear();
    alert(curr_year + "/"+ curr_month + "/" +curr_date + " "+curr_hour + ":"+curr_min);
}

function getCurrentDateTimeString() {
	var d = new Date();
	var curr_min = d.getMinutes();
	var curr_hour = d.getHours();
    var curr_date = d.getDate();
    var curr_month = d.getMonth() + 1; //Months are zero based
    var curr_year = d.getFullYear();
    var returnVal = curr_year + "/"+ curr_month + "/" +curr_date + " "+curr_hour + ":"+curr_min;
    return returnVal;
}

//Calendar Module
var getDatee = new Date();
var monthe = getDatee.getMonth();
var yeare = getDatee.getFullYear();
var day = getDatee.getDate(); 

function isEmpty(val){
   return (val === undefined || val == null || val.length <= 0) ? true : false;
}

function prev()
{
	monthe = monthe-1;
    if(monthe < 0)
{
    yeare = yeare-1;	
        monthe = 11;
    }
    dispCal(monthe, yeare);
    return false;
}

function next()
{
	monthe = monthe+1;
    if(monthe > 11)
{
    yeare = yeare+1;	
        monthe = 0;
    }
    dispCal(monthe, yeare);
    return false;
}

function daysInMonth(monthe, yeare)
{
    return 32 - new Date(yeare, monthe, 32).getDate();
}

function getElementPosition(arrName,arrItem){
    for(var pos=0; pos<arrName.length; pos++ ){
        if(arrName[pos]==arrItem){
            return pos;
        }
    }
}

function setVal(getDat){
    $('#sel').val(getDat);
    $('#calendar').hide();
}


function dispCal(mon,yea){
var ar = new Array('Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');
    var chkEmpty = isEmpty(mon);
    var n,days,calendar,startDate,newYea,setvale,i;
    if(chkEmpty != true){
        mon = mon+1;
        n = ar[mon-1];
        n += " "+yea;
        newYea = yea;
        days = daysInMonth((mon-1),yea);
        startDate = new Date(ar[mon-1]+" 1"+","+parseInt(yea));
    }else{
        mon = getElementPosition(ar,ar[getDatee.getMonth()]);
        n = ar[getDatee.getMonth()];
        n += " "+yeare;
        newYea = yeare;
        days = daysInMonth(mon,yeare);
        startDate = new Date(ar[mon]+" 1"+","+parseInt(yeare));
    }
    
    var startDay = startDate.getDay();
    var startDay1 = startDay;
    while(startDay> 0){
       calendar += "<td></td>";  
       startDay--;
    }                
    i = 1;
    while (i <= days){
      if(startDay1 > 6){
          startDay1 = 0;  
          calendar += "</tr><tr>";  
      }  
      mon = monthe+1;
      setvale = i+","+n;
if(i == day && newYea==yeare && mon==monthe){
calendar +="<td class='thisday' onclick='setVal(\""+i+"-"+mon+"-"+newYea+"\")'>"+i+"</td>";
      }else{  
        calendar +="<td class='thismon' onclick='setVal(\""+i+"-"+mon+"-"+newYea+"\")'>"+i+"</td>";   
      }
startDay1++;  
      i++;  
    }
calendar +="<td><a style='font-size: 9px; color: #efefef; font-family: arial; text-decoration: none;'h</a></td>";   

    $('#calendar').css('display','block');
    $('#month').html(n);
    var test = "<tr class='weekdays'><td>Sun</td><td>Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td></tr>";  
    test += calendar;
$('#dispDays').html(test);
}