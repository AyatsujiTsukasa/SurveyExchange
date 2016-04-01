function setUp(){
	var SurveyHTML = "<ol>";
	var qs = $('.mySurveyContent').html().split("#$%^");
	for(var i=0; i<qs.length; i++){
		var qInfo = qs[i].split("#$#");
		var question = "<li><div class='question'><h3>" + qInfo[0] + "</h3>"
		if(qInfo[1] === 'text'){
			question += "<ul><p><strong>Replies</strong></p>";
			for(var j=2; j<qInfo.length; j++)
				question += "<li>" + qInfo[j] + "</li>";
			question += "</ul>";
		} else {
			question += "<table><tr><td><strong>Choice</strong></td><td><strong>Number of Replies</strong></td></tr>";
			var num = (qInfo.length-2)/2;
			for(var j=0; j<num; j++){
				question += "<tr><td>" + qInfo[j+2] + "</td><td>" + qInfo[j+2+num] + "</td></tr>";
			}
			question += "</table></div>";
		}
		SurveyHTML += question + "</li>";
	}
	$('.mySurveyContent').html(SurveyHTML + "</ol>");
}

setUp();