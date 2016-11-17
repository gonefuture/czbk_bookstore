package test;
import static org.junit.Assert.*;

import java.text.MessageFormat;

import org.junit.Test;


public class test {

	@Test
	public void fun1() {
		String s =MessageFormat.format("{0}或{1}错误", "用户名","密码");
		System.out.print(s);
		
	}

}
