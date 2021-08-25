package my.test1;

public class Hello {
	private String msg="hello"+" "+"what r u doing now";
	public void setMsg(String msg) {
		if(msg.equals("hi")) {
			this.msg=msg;
		}else{
			System.out.println("Wrong input type");
		}
	}
	
	public String getMsg() {
		return msg;
	}
	
	//객체지향의 3대 컨셉 무조건 이러한 형식으로 작성해야한다 (개발자의 의도를 명확히 반영해야한다.)
}
