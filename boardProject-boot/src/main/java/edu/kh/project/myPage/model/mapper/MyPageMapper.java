package edu.kh.project.myPage.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;

@Mapper
public interface MyPageMapper {

	/** 회원 정보 수정
	 * @param inputMember
	 * @return result
	 */
	int updateInfo(Member inputMember);

	/** 회원의 비밀번호 조회
	 * @param memberNo
	 * @return 암포회 되어있는 비밀번호
	 */
	String selectPw(int memberNo);

	/** 비밀 번호 변경
	 * @param paramMap
	 * @return result
	 */
	int changePw(Map<String, Object> paramMap);

	int secession(int memberNo);

}
