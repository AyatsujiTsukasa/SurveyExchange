function responseNewSurvey(title, desc, surveyString, author, email, pId){
	var newFrame = "<form action='submitSurvey' method='post' id='clientForm'><fieldset>" + toSurveyHTML(title, desc, surveyString) + "<hr><p class='rights'>Posted by " + author + "</p><p class='rights'>Email: " + email + "</p><input type='button' value='< Back' onclick='back()' /><input type='submit' value='submit' /><input type='hidden' name='surveyTitle' value= '" + title + "'/><input type='hidden' name='pId' value= " + pId + " /></fieldset></form>";
	$('#main').html(newFrame);
}

function back(){
	window.location.href = "userHome.jsp";
}

function toSurveyHTML(title, desc, surveyString){
	var SurveyHTML = "<h1 class='sampleTitle'>" + title + "</h1><hr><p>" + desc + "</p><hr><ol>";
	var qs = surveyString.split("#$%^");
	for(var i=0; i<qs.length; i++){
		var qInfo = qs[i].split("#$#");
		var question = "<li><h3>" + qInfo[0] + "</h3><ul>"
		if(qInfo[1] === 'text'){
			question += "<textarea required class='AnswerText' cols='40' rows='5' form='clientForm' name='" + i + "'></textarea></ul></li>";
		} else if(qInfo[1] === 'radio'){
			for(var j=0; j<(qInfo.length-2)/2; j++){
				question += "<p class='sampleChoiceGrp'><input required type='radio' name='" + i + "'value='" + j + "' />" + qInfo[j+2] + "</p>";
			}
			question += "</ul></li>";
		} else {
			for(var j=0; j<(qInfo.length-2)/2; j++){
				question += "<p class='sampleChoiceGrp'><input type='checkbox' name='" + i + "'value='" + j + "' />" + qInfo[j+2] + "</p>";
			}
			question += "</ul></li>";
		}
		SurveyHTML = SurveyHTML + question;
	}
	return SurveyHTML + "<hr></ol>";
}

var a = $('#main').html().split('?=?');
responseNewSurvey(a[0], a[1], a[2], a[3], a[4], a[5]);