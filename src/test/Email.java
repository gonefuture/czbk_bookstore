package test;



import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.junit.Test;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

public class Email {

	@Test
	public void sendEnail() throws IOException{
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().
					getResourceAsStream("email_template.properties"));
			String host = props.getProperty("host");
			String uname = props.getProperty("uname");
			String pwd = props.getProperty("pwd");
			String from = props.getProperty("from");
			String to = "848727875@qq.com";
			String subject = props.getProperty("subject");
			String content = props.getProperty("content");
			System.out .print("已发1");
			Session session = MailUtils.createSession(host, uname, pwd);
			System.out .print("已发2");
			Mail mail = new Mail(from,to,subject,content);
			try{
				System.out .print("发送前");
				MailUtils.send(session, mail);
				System.out .print("已发送");
			} catch(MessagingException e){
				
			}
		}

}
