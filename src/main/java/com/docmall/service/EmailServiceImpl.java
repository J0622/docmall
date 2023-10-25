package com.docmall.service;


import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.docmall.dto.EmailDTO;

import lombok.RequiredArgsConstructor;

// 특징: 매퍼 인터페이스를 참조하지 않음. (필요시 참조가능 하나 현재는 사용이유없음)
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
	
//	의존성 주입 (email-config.xml의 bean으로 주입)
	private final JavaMailSender mailSender;

	@Override
	public void sendMail(EmailDTO dto, String message) {
//		MimeMessage: 메일 구성정보를 담당하는 객체(ex:발신자,수신자,발신자메일주소,수신자메일주소)
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
//			수신자
			mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiverMail()));
//			발신자
			mimeMessage.addFrom(new InternetAddress[] {new InternetAddress(dto.getSenderMail(), dto.getSenderName())});
//			메일제목
			mimeMessage.setSubject(dto.getSubject(), "UTF-8");
//			본문내용
			mimeMessage.setText(message,"UTF-8");
			
//			여기 send 메소드 사용을위해 위 작업을 진행한것임.
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
