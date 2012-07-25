var msg ="Please Enter the Following :\n -----------------------------";

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


function doUserSearch () {
	var frm = document.forms[0];
	var str = "";
	if(frm.empId.value == "" && frm.empName.value == "")
		str += "Please Enter any one of the search Criteria .";
	if(str != "") {
		alert(str);
	} else {
		document.forms[0].action = 'searchUserProcess.do?searchUser=true';
		document.forms[0].submit();
	}	
}

function validate() {
	var frm = document.forms[0];	
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
	return false;
}


function activateUser(idx) {
	document.forms[0].action = 'searchUserProcess.do?activateUser=true&idx='+idx;
	document.forms[0].submit();
}