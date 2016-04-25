package timevalvelogin;

import java.util.Scanner;

public class XingLongLogin {
	public static void main(String[] args) throws Exception {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("请输入你的MAC地址,格式为： 44-37-E6-00-AF-44");
		String macAddr = input.next();
		
		System.out.println("请输入到期时间，例： 2015年12月31日到期则为，2015-12-31");
		String date = input.next();
		
		System.out.println("请输入服务器人数：");
		String peopleNum = input.next();
		
		DesUtils du = new DesUtils("ivychang");
		
		
		String m1 = du.encrypt(macAddr + date +peopleNum);
		String m2 = du.encrypt("F0-DE-F2-47-F7-24" + date + peopleNum);
		
		//String m1 = du.decrypt("b3e37a5b9405cf337d05f903995e1b8198561735c2c82e1a742c975ec291b5f8");
		System.out.println(m1);
		System.out.println(m2);
		
		
	}

}
