package edu.kh.project.email.model.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.email.model.mapper.EmailMapper;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Transactional // 예외 발생하면 롤백 (기본값 commit)
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
	
	// EmailConfig 설정이 적용됙 객체 (메일 보내기 기능)
	private final JavaMailSender mailSender;
	
	private final EmailMapper mapper;
	
	
	
	

	// 이메일 보내기
	@Override
	public String sendEmail(String htmlName, String email) {
		
		// 6자리 난수(인증 코드) 생성
		String authKey = createAuthKey();
		
		try {
			
			// 제목
			String subject = null;
			
			switch(htmlName) {
			case "signup" :
				subject = "[boardProject] 회원 가입 인증번호 입니다."; break;
			}
			
			// 인증 메일 보내기
			
			// MimeMessage : Java 에서 메일을 보내는 객체
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			// MimeMessageHelper : 
			// Spring 에서 제공하는 메일 발송 도우미(간단 + 타임리프)
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			
			// 1번 매개변수 : MimeMessage
			// 1번 매개변수 : 파일 전송 사용 ? true / false
			// 1번 매개변수 : 문자 인코딩 지정
			
			helper.setTo(email); // 받는 사람 이메일 지정
			helper.setSubject(subject); // 이메일 제목 지정
			
			helper.setText(authKey); // 변경 -> html 보낼것 (변경 예정)
			
			// CID(Content-ID) 를 이용해 메일에 이미지 첨부
			// logo 추가예정
			
			// 메일 보내기
			mailSender.send(mimeMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	/** 인증번호 생성 (영어 대문자 + 소문자 + 숫자 6자리)
	* @return authKey
	*/
    public String createAuthKey() {
    	
		String key = "";
		
		for(int i=0 ; i< 6 ; i++) {
		    int sel1 = (int)(Math.random() * 3); // 0:숫자 / 1,2:영어
		  
			    if(sel1 == 0) {
			    	
			       int num = (int)(Math.random() * 10); // 0~9
		       key += num;
		      
		    } else {
		    	
		       char ch = (char)(Math.random() * 26 + 65); // A~Z
		      
		       int sel2 = (int)(Math.random() * 2); // 0:소문자 / 1:대문자
		      
		       if(sel2 == 0) {
		           ch = (char)(ch + ('a' - 'A')); // 대문자로 변경
		       }
		       
		       key += ch;
		    }
		      
		}
		
		return key;
    }

}


/* Googole SMTP 를 이용한 이메일 전송하기
 * 
 * - SMTP(Simplet Mail Trasfer Protocol, 간단한 메일 전송 규약)
 * --> 이메일 메시지를 보내고 받을 때 사용하는 약속(규약, 방법)
 * 
 * - Google SMTP
 * 
 * Java Mail Sender -> Googol STMP -> 대상에게 이메일 전송
 * 1) config.properties 내용 추가(계정, 앱비밀번호)
 * 
 * 
 * */
















