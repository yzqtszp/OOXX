package timevalvelogin;

import java.util.Scanner;

public class XingLongLogin {
	public static void main(String[] args) throws Exception {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("���������MAC��ַ,��ʽΪ�� 44-37-E6-00-AF-44");
		String macAddr = input.next();
		
		System.out.println("�����뵽��ʱ�䣬���� 2015��12��31�յ�����Ϊ��2015-12-31");
		String date = input.next();
		
		System.out.println("�����������������");
		String peopleNum = input.next();
		
		DesUtils du = new DesUtils("ivychang");
		
		
		String m1 = du.encrypt(macAddr + date +peopleNum);
		String m2 = du.encrypt("F0-DE-F2-47-F7-24" + date + peopleNum);
		
		//String m1 = du.decrypt("b3e37a5b9405cf337d05f903995e1b8198561735c2c82e1a742c975ec291b5f8");
		System.out.println(m1);
		System.out.println(m2);
		
		
	}

}
