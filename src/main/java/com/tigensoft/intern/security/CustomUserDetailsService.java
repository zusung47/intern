package com.tigensoft.intern.security;

import com.tigensoft.intern.dao.MemberDAO;
import com.tigensoft.intern.domain.CustomUser;
import com.tigensoft.intern.dto.MemberDTO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/*
1. 사용자가 입력한 아이디, 패스워드를 비교하기 위한 객체 생성(UserDetails 인터페이스를 구현한 객체 = CustomUser)
2. 로그인 인증을 위해 UserDetailsService 인터페이스를 구현한 Service 클래스 파일 생성 (=CustomUserDetailsService)
3. UserDetailsService 인터페이스에서 실제로 사용자 아이디를 불러오고 인증을 진행하는 메서드인 loadUserByUsername(String loginId) 를 @override
4. loadUserByUsername 메서드에서 loginId 값에 해당하는 로그인 정보를 DB 에서 불러온다.
5. UserDetails 인터페이스를 구현한 객체에다가 DB 에서 불러온 정보를 저장한 후 return 하면 로그인 인증을 진행한다.
*/
@Log4j
public class CustomUserDetailsService implements UserDetailsService {

    @Setter(onMethod_ = @Autowired)
    private MemberDAO memberDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        log.warn("Load User By UserName : " + userName);

        //userName = userId
        MemberDTO memberDTO = memberDAO.getLoginMember(userName);

        log.warn("queried by member mapper : " + memberDTO);

        return memberDTO == null ? null : new CustomUser(memberDTO);
    }
}
