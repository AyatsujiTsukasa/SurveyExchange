function setUp(){
	var qs = $('#surveysContainer').html().split('#%%#');
	var surveysHtml = "";
	var titles = [];
	for(var i=0; i<qs.length-1; i++){
		var q = qs[i].split("#%#");
		titles[i] = q[0];
		surveysHtml += "<div class='survey' style='cursor: pointer' onclick=document.getElementById(\'form" + i + "\').submit()><h2>" + q[0] + "</h2><p>" + q[1] + "</p><form id='form" + i + "' method='post' action='toMySurvey'><input type='hidden' name='title' /></form></div>";
	}
	$('#surveysContainer').html(surveysHtml);
	for(var i=0; i<qs.length-1; i++){
		$('#form' + i).children()[0].value = titles[i];
	}
}
setUp();