$(document).ready(function(){

  function getStats() {
		$.ajax({
			type : "GET",
			url : "/stats",
			data : '$format=json',
			dataType : 'json',
			success : function(data) {
				$("#host").append("<div class=\"alert alert-info fade in\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a><strong>Hostname</strong> "+data.hostname+"</div>");
				$("#time").append("<div class=\"alert alert-warning fade in\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a><strong>Time</strong> "+data.date.year+"-"+data.date.month+"-"+data.date.dayOfMonth+" "+data.date.hour+"h "+data.date.minute+"m "+data.date.second+"s"+"</div>");
				$("#statusTable tr").remove();
				$("#statusTable tbody").append("<tr><th>service</th><th>port</th><th>status</th></tr>");
				var serv=1;
				$.each(data.serviceStats, function(d,results) {
				  if (results.status=='UP') {
					  resStatus = "<span class=\"label label-success\">UP</span>"
				  }	else {
					  resStatus = "<span class=\"label label-danger\">DOWN</span>"
				  }
				  $("#statusTable tbody").append(
					"<tr>"  
				    + "<td class=\"text-nowrap\"><a data-toggle=\"modal\" href=\"javascript:;\" onclick=\"newWin('"+results.service.name.trim()+"');\">"  
				    + results.service.name.trim()
				    + "</a>"
				    + "</td>" + "<td class=\"text-nowrap\">"
				    + results.service.port
				    + "</td>" + "<td class=\"text-nowrap\">"
				    + resStatus
					+ "</td>" 
					+ "</tr>");
				  serv++;
				})
				$.each(data.fileSystem.rootDirectories, function(d,results) {
				  $("#fsTable tr").remove();
				  $("#fsTable tbody").append("<tr><th>partition</th><th>total space</th><th>used space</th><th>usabled space</th></tr>");					
				  $("#fsTable tbody").append(
					"<tr>"  
				    + "<td class=\"text-nowrap\">"  
				    + results.name.trim()
				    + "</a>"
				    + "</td>" + "<td class=\"text-nowrap\">"
				    + results.totalSpace
				    + "</td>" + "<td class=\"text-nowrap\">"
				    + results.usedSpace
				    + "</td>" + "<td class=\"text-nowrap\">"
				    + results.usabledSpace				    
					+ "</td>" 
					+ "</tr><tr>"
					+ "<td colspan=4><div class=\"progress\">"
					+ "<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=\""+results.usedSpacePercent+"\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:"+results.usedSpacePercent+"%;\">"
				    + results.usedSpacePercent+"%</div></div></td></tr>"
				    );
				})
			}
		});
  };
	
 // boot
 getStats();
 
}); 

function newWin(service) {
	url='/detail/'+service;
	width=500;
	height=500;
    var leftPosition, topPosition;
    leftPosition = (window.screen.width / 2) - ((width / 2) + 10);
    topPosition = (window.screen.height / 2) - ((height / 2) + 50);
    window.open(url, "w"+service,
    "status=no,height=" + height + ",width=" + width + ",resizable=yes,left="
    + leftPosition + ",top=" + topPosition + ",screenX=" + leftPosition + ",screenY="
    + topPosition + ",toolbar=no,menubar=no,scrollbars=yes,location=no,directories=no");
}
	
