package com.tigensoft.intern.domain;

import com.tigensoft.intern.dto.MemberDTO;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

//loadUserByUsername()의 리턴타입 UserDetails를 맞추기위해 MemberDTO를 UserDetails타입으로 변환
@Getter
@Log4j
public class CustomUser extends User {

    /*
    자바 직렬화에서 사용되는 고유 식별자 (직렬화는 객체를 바이트 스트림으로 변환하여 저장하거나 네트워크를 통해 전송할 수 있게하는 과정)
    이걸 사용해서 CustomUser클래스의 버전을 명시적으로 지정함으로써 직렬화 및 역직렬화 시 클래스의 호환성을 유지하고 예외 발생 방지
    CustomUser클래스는 User를 상속하고 User는 Serializable 인터페이스를 구현하고 있기 때문에 serialVersionUID 정의하는게 좋음
     */
    private static final long serialVersionUID = 1L;

    private MemberDTO member;

    //CustomUser는 userdetails.User를 상속하기 때문에 부모 클래스의 생성자를 호출해야만 정상적인 객체를 생성할 수 있음
    //GrantedAuthority : 사용자 권한을 나타내는 인터페이스
    //<? extends GrantedAuthority> : GrantedAuthority 인터페이스를 구현한 객체들만 컬렉션에 포함 가능
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
    }

    //3번째에 GrantedAuthority객체 형태로 전달해야함 -> SimpleGrantedAuthority 클래스 사용하면됨
    //User 생성자는 Collection<? extends GrantedAuthority>타입의 인자를 필요로해서 단일 권한을 명시한 경우 Collections.singletonList를 사용해야함
    //Collections.singletonList() 단일요소로 구성된 불변 리스트 반환 스프링시큐리티에서 단일 권한 리스트를 표현할때 유용함
    public CustomUser(MemberDTO member){
        super(member.getUserId(), member.getUserPw(), Collections.singletonList(new SimpleGrantedAuthority(member.getAuth())));
        this.member = member;
        log.warn(this.member);
    }

}
