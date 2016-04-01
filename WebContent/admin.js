var qHead1 = "Type: <select onchange='changeQuestion(value, this)'>";
var radioQ = "<option class='Radio' value='0'>Radio</option>";
var checkQ = "<option class='Check' value='1'>Check</option>";
var textQ = "<option class='Text' value='2'>Text</option>";
var qHead2 = "</select><br><input type='button' value='Delete this question' onclick='deleteQuestion(this)'></input>";
var AnsTitle = "<br><input type='text' class='AnsTitle' name='AnsTitle' required='required' />";
var radioGrp = "<p class='choiceGrp'><input type='radio' disabled='disabled'><input type='text' class='Choice' name='radioChoice' required='required' /><input type='button' value='Delete this choice' onclick='deleteChoice(this)'></input></p>";
var radioInit = "<ul class='choiceList'>" + radioGrp + "</ul><div class='addNew addNewC' onclick='addNewRChoice(this)'>Add a new choice</div>";
var checkGrp = "<p class='choiceGrp'><input type='checkbox' disabled='disabled'><input type='text' class='Choice' name='checkChoice' required='required' /><input type='button' value='Delete this choice' onclick='deleteChoice(this)'></input></p>";
var checkInit = "<ul class='choiceList'>" + checkGrp + "</ul><div class='addNew addNewC' onclick='addNewCChoice(this)'>Add a new choice</div>";
var textInit = "<ul class='choiceList'><textarea disabled class='AnswerText' cols='40' rows='5'></textarea></ul>"
var clientCredit = $('#credit').html();
	
function addNewQuestion() {
	var ql = $('#questionList');
	var newHtml = "<hr><li>" + "Title: " + AnsTitle + "<br>Choices: " + radioInit + "<br>" + qHead1 + radioQ + checkQ + textQ + qHead2 + "</li>";
	ql.append(newHtml);
}

function addNewRChoice(ele) {
	var ul = $($(ele).siblings()[3]);
	ul.append(radioGrp);
}

function addNewCChoice(ele) {
	var ul = $($(ele).siblings()[3]);
	ul.append(checkGrp);
}

function deleteQuestion(ele) {
	if(confirm("Are you sure that you want to delete this question?")){
		var a = $('ol')[0].children;
		for(var i=0; i<a.length; i++){
			if(a[i] === ele.parentElement){
				var b = a[i-1];
				var c = a[i];
				b.remove();
				c.remove();
			}
		}
	}
}

function deleteChoice(ele) {
	if(confirm("Are you sure that you want to delete this choice?")){
		ele.parentElement.remove();
	}
}

function changeQuestion(value, ele) {
	if(value === '0'){
		$(ele).html(radioQ + checkQ + textQ);
		var temp = $(ele).siblings();
		$(ele.parentElement).html("Title: " + AnsTitle + "<br>Choices: " + radioInit + "<br>Type: " + ele.outerHTML + "<br>" + temp[temp.length-1].outerHTML);
	} else if(value === '1'){
		$(ele).html(checkQ + radioQ + textQ);
		var temp = $(ele).siblings();
		$(ele.parentElement).html("Title: " + AnsTitle + "<br>Choices: " + checkInit + "<br>Type: " + ele.outerHTML + "<br>" + temp[temp.length-1].outerHTML);
	} else {
		$(ele).html(textQ + radioQ + checkQ);
		var temp = $(ele).siblings();
		$(ele.parentElement).html("Title: " + AnsTitle + "<br>Answer: " + textInit + "<br>Type: " + ele.outerHTML + "<br>" + temp[temp.length-1].outerHTML);
	}
}

var originalHTML;

var SurveyTitle;
var expNumReply;
var desc;
var SurveyString;
var creditEnough = true;
var newCreditValue;
var author;
var email;
var authorID;

function switchPage() {
	originalHTML = $('body').html();
	var valid = true;

	SurveyTitle = $('#SurveyTitle')[0].value;
	expNumReply = $('#expNumReply')[0].value;
	desc = $('#desc')[0].value;
	if(SurveyTitle === "" || expNumReply === "" || desc === "")
		valid = false;
	var SurveyInfo = $('li');
	var InfoString = "";
	if(valid)
	for(var i=0; i<SurveyInfo.length; i++){
		if(i > 0)
			InfoString += "#$%^";
		var child = SurveyInfo[i];
		InfoString += child.children[1].value + "#$#";
		if(child.children[1].value === ""){
			valid = false;
			break;
		}
		var next = child.children[3];
		if(next.children[0].className === 'AnswerText'){
			InfoString += "text";
		} else {
			next = next.children;
			InfoString += next[0].children[0].type + "#$#";
			var count = 0;
			for(var j=0; j<next.length; j++){
				InfoString += next[j].children[1].value + "#$#";
				if(next[j].children[1].value === ""){
					valid = false;
					break;
				}
				count++;
			}
			for(var j=0; j<count; j++){
				InfoString += '0';
				if(j < count-1)
					InfoString += '#$#';
			}
		}
	}
	if(valid && creditEnough){
		newCreditValue = clientCredit - expNumReply;
		author = $('#author').html();
		email = $('#email').html();
		SurveyString = InfoString;
		var newFrame = "<p>New Survey</p><hr><form><fieldset><legend>Are you satisfied with your questionnaire? </legend>" + toSurveyHTML(SurveyTitle, desc, SurveyString) + "<hr><p class='rights'>Posted by " + author + "</p><p class='rights'>Email: " + email + "</p><p class='rights'>(Remaining Credits: " + newCreditValue + ")</p><input type='button' value='< Back' onclick='back()' /></fieldset></form>";
		var hiddenFrame = "<form action='NewSurveyServlet' method='post'><input name='SurveyString' type='hidden' value='" + SurveyString + "' /><input name='title' type='hidden' value='" + SurveyTitle + "' /><input name='expNumReply' type='hidden' value='" + expNumReply + "' /><input name='desc' type='hidden' value='" + desc + "' /><input name='newCreditValue' type='hidden' value='" + newCreditValue + "' /><input name='author' type='hidden' value='" + author + "' /><input type='submit' value='Post this survey' /></form>";
		$('body').html(newFrame + hiddenFrame);
	} else {
		if(!valid)
			alert("You have not filled in all the fields!");
		if(!creditEnough)
			alert("Your expected number of replies exceeded your number of credits!");
	}
}

function back() {
	$('body').html(originalHTML);
	restore();
}

function restore(){
	clientCredit = parseInt(clientCredit) + parseInt(expNumReply);
	document.getElementById('credit').innerHTML = clientCredit;
}

function toSurveyHTML(title, desc, surveyString){
	var SurveyHTML = "<h1 class='sampleTitle'>" + title + "</h1><hr><p>" + desc + "</p><hr><ol>";
	var qs = surveyString.split("#$%^");
	for(var i=0; i<qs.length; i++){
		var qInfo = qs[i].split("#$#");
		var question = "<li><h3>" + qInfo[0] + "</h3><ul>"
		if(qInfo[1] === 'text'){
			question += "<textarea class='AnswerText' cols='40' rows='5'></textarea></ul></li>";
		} else if(qInfo[1] === 'radio'){
			for(var j=0; j<(qInfo.length-2)/2; j++){
				question += "<p class='sampleChoiceGrp'><input type='radio' name='" + i + "'value='" + j + "' />" + qInfo[j+2] + "</p>";
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

function expNumReplyChange(ele){
	creditEnough = true;
	var exp = ele.value;
	if(exp < 0){
		ele.value = 0;
	} else {
		var temp = clientCredit - exp;
		if(temp < -20){
			creditEnough = false;
			$('#credit').html("Credit Not Enough!");
			$('#credit').addClass('creditNotEnough');
		} else if (temp < 0){
			$('#credit').html(temp);
			$('#credit').addClass('creditNotEnough');
		} else {
			$('#credit').html(temp);
			$('#credit').removeClass('creditNotEnough');
		}
	}
}