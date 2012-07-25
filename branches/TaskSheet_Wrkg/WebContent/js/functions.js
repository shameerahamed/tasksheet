function GenerateReport() {
	if(validate()) {
		document.forms[0].action = 'searchTaskProcess.do?reportTask=true';
		document.forms[0].submit();
	}
}

function doSearch() {
	if(validate()) {
		document.forms[0].action = 'searchTaskProcess.do?searchTask=true';
		document.forms[0].submit();
	}
}

function validate() {
	var frm = document.forms[0];
	var msg ="Please Enter the Following :\n -----------------------------";
	var str = "";
	if(frm.fromDate.value == " " || frm.fromDate.value == "")
		str += "\nFrom Date.";		
	if(frm.toDate.value == " " || frm.toDate.value == "")
		str += "\nTo Date.";
	if(str != "") {
		alert(msg+str);	
	} else {
		return true;
	} 
	return false
}