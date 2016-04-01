var id = document.getElementsByName('ownerId')[0].value
$(document).ready(function(){
	var a = $('.newResponseList').html().trim().split("#%%#");
	var newResponseHtml = "";
	var titles = ["", "", "", "", ""];
	for(var i=0; i<5; i++){
		if(a[i] === "NULL")
			newResponseHtml += "<div class='newResponse'></div>";
		else {
			var b = a[i].split("#%#");
			newResponseHtml += "<div class='newResponse' style='cursor: pointer' onclick=document.getElementById(\'viewMy" + i + "\').submit()>"
			var temp = b[0];
			titles[i] = b[0];
			if(temp.length > 27)
				temp = temp.substring(0, 24) + "...";
			newResponseHtml += "<h3>" + temp + "</h3>";
			temp = b[1];
			if(temp.length > 107)
				temp = temp.substring(0, 110) + '...';
			newResponseHtml += "<p>" + temp + "</p><form id='viewMy" + i + "' method='post' action='toMySurvey'><input type='hidden' name='title' /></form></div>";
		}
	}
	$('.newResponseList').html(newResponseHtml);
	for(var i=0; i<5; i++){
		if(titles[i] !== ""){
			$('#viewMy' + i).children()[0].value = titles[i];
		}
	}
	
	var a = $('.SurveyFeed').html().trim().split("#%%#");
	var surveyFeedHtml = "";
	var titles = ["", "", ""];
	for(var i=0; i<3; i++){
		if(a[i] === "NULL")
			surveyFeedHtml += "<div class='feed'></div>";
		else{
			var b = a[i].split("#%#"); 
			titles[i] = b[0];
			surveyFeedHtml += "<div class='feed' style='cursor: pointer' onclick=document.getElementById(\'do" + i + "\').submit()><h2>" + b[0] + "</h2><p class='otherFeedDesc'>" + b[1] + "</p><form id='do" + i + "' method='post' action='toSurvey'><input type='hidden' name='surveyTitle' /><input type='hidden' name='id' value=" + id + " /></form></div>";
		}
	}
	$('.SurveyFeed').html(surveyFeedHtml);
	for(var i=0; i<3; i++){
		if(titles[i] !== ""){
			$('#do' + i).children()[0].value = titles[i];
		}
	}
});