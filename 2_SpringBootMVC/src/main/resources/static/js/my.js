$(document).ready(function() {
	
	
	$("#basketDiv").click(function(){
		const productName=$("#product").val();
		alert(productName);
		$.post('basketinsert',{productName},
		function(data){
			data=JSON.parse(data);
			alert(data.msg);
		});
	});
	
	

	const logined_id = $.cookie("logined_id");
	if (logined_id) {
		$("#loginDiv").html(logined_id + "님 환영합니다.<button id='logoutBtn'>로그아웃</button>");
	}
	$(document).on("click", "#logoutBtn", function(event) { //로그아웃 처리

		$.post("logout",
			{

			},
			function() {

				$.removeCookie("logined_id");
				location.reload();
			}
		);//end post() 
	});//end 로그아웃 처리


	$("#loginBtn").click(function() {
		const id = $("#login_id").val();
		const pw = $("#login_pw").val();
		alert(id + ":" + pw);
		$.post('login', { id, pw }, function(data) {
			data + JSON.parse(data);
			console.log(data);
			if (data.errMsg) {
				alert(data.errMsg);
			} else {
				$("#loginDiv").html(data.name + "님 환영합니다.<button id='logoutBtn'>로그아웃</button>");
				$.cookie("logined_id", "id");
			}
		});
	});

});