$(document).ready(function(){
	const login_msg=$.cookie("login_msg");
	if(login_msg){
		$('#loginDiv').html(login_msg+"<button id='logoutBtn'>로그아웃</button>");
	}
	
	$("#logoutBtn").click(function(){
		$.post('main',{sign:"logout"},
		function(){
			$.removeCookie("login_msg");
			location.reload();//새로고침 효과
		});
	});
	
	
	$("#loginBtn").click(function(){
		const id=$('#login_id').val();
		const pw=$('#login_pw').val();
		alert(id+":"+pw);
		
		$.post('main', {id, pw, sign:"login"} , 
			function(data){
				data=JSON.parse(data);
				if(data.msg){//로그인 성공
				    $.cookie("login_msg",data.msg);//응답메세지를 쿠키로 설정한 것
					$('#loginDiv').html(data.msg);
				}else{
					alert(data.error);
				}
				
			} 
		);//end post
	});//end click
});//end ready