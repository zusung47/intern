package com.tigensoft.intern;


import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
@Log4j
public class MemberTests {

    @Setter(onMethod_ = @Autowired)
    private PasswordEncoder pwencoder;

    @Setter(onMethod_ = @Autowired)
    private DataSource ds;

    //테스트 값 넣어보기
    @Test
    public void testInsertMember() {

        String sql = "insert into member(USER_ID, USER_PW, USERNAME, AUTH) values(?,?,?,?)";

        for(int i = 0; i < 1; i++){
            Connection con = null;
            PreparedStatement pstmt = null;


            try{
                con = ds.getConnection();
                pstmt = con.prepareStatement(sql);

                pstmt.setString(1, "admin"+i);
                pstmt.setString(2, pwencoder.encode("pw"+i));
                pstmt.setString(3, "관리자"+i);
                pstmt.setString(4, "ROLE_ADMIN");

                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(pstmt != null) {try { pstmt.close(); } catch (Exception e) {} }
                if(con != null) {try { con.close(); } catch (Exception e) {} }
            }
        }
    }
}
