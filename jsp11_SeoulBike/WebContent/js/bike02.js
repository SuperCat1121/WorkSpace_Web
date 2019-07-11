
$(function() {
	parseJson();
});
// JSON.parse() : JSON 형식의 문자열 -> JSON Object
// JSON.stringify() : JSON Object -> JSON 형식의 문자열
function parseJson() {
	$.getJSON("json/bike.json", function(data) {
		$.ajax({
			url : "bike.do?command=second_db",
			method : "post",
			data : {"obj":JSON.stringify(data)},
			success : function(msg) {
				// database에 저장을 성공하면 table을 만들자
				alert(msg);
				//if(msg == data.DATA.length) {
					$("table").attr("border","1");
					$("thead").append(
						"<tr>" +
							"<td>" + data.DESCRIPTION.RENT_ID + "</td>" +
							"<td>" + data.DESCRIPTION.ADDR_GU + "</td>" +
							"<td>" + data.DESCRIPTION.CONTENT_ID + "</td>" +
							"<td>" + data.DESCRIPTION.CONTENT_NM + "</td>" +
							"<td>" + data.DESCRIPTION.NEW_ADDR + "</td>" +
							"<td>" + data.DESCRIPTION.CRADLE_COUNT + "</td>" +
							"<td>" + data.DESCRIPTION.LONGITUDE + "</td>" +
							"<td>" + data.DESCRIPTION.LATITUDE + "</td>" +
						"</tr>"
					);
					for(var i=0;i<msg;i++) {
						$("tbody").append(
							"<tr>" +
								"<td>" + data.DATA[i].rent_id + "</td>" +
								"<td>" + data.DATA[i].addr_gu + "</td>" +
								"<td>" + data.DATA[i].content_id + "</td>" +
								"<td>" + data.DATA[i].content_nm + "</td>" +
								"<td>" + data.DATA[i].new_addr + "</td>" +
								"<td>" + data.DATA[i].cradle_count + "</td>" +
								"<td>" + data.DATA[i].longitude + "</td>" +
								"<td>" + data.DATA[i].latitude + "</td>" +
							"</tr>"
						);
					}
				//} $.getJSON / $.each / JSON.parse / JSON.stringify / callback
			},
			error : function() {
				alert("실패");
			}
		});
	});
}