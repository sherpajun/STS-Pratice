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
	
	//��ü������ 3�� ���� ������ �̷��� �������� �ۼ��ؾ��Ѵ� (�������� �ǵ��� ��Ȯ�� �ݿ��ؾ��Ѵ�.)
}
