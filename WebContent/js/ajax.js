//For paging
function paging(formobj, n) {	
	if (n > maxPage) {
		alert('Please Enter the valid Page number.');
	} else {
		showPaging(formobj, n);
	}
	/*formobj.pageNo.value = n;
	    formobj.submit();*/
	return false;
}

function showPaging(formobj, pageNo, offset) {
	if (offset == undefined)
		offset = "";
	if (pageNo != "") {
		var url = "searchTaskProcess.do?paging=true&pageNo=" + pageNo;
		if (offset != "")
			url += "&offset=" + offset;
		loadProgressBar("#maintbl");
		$("#maintbl").load(url, function(response, status, xhr) {
			if (status == "error") {
				var msg = "Sorry but there was an error: ";
				$("#maintbl").html(msg + xhr.status + " " + xhr.statusText);
			} else if (status == "success") {
				$("#loader").remove();
			}
		});
	}
}

function loadProgressBar(div) {
	$('<div></div>').appendTo(div).attr("id", "loader").addClass(
			"ui-widget-overlay").css( {
		position : 'absolute',
		top : 0,
		left : 0,
		height : $(div).height(),
		width : $(div).width(),
		zIndex : 1001,
		background : 'url(images/progress.gif) no-repeat center'
	});
}