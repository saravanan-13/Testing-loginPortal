package com.sapient.loginportal.dao;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sapient.loginportal.model.User;
@Repository
public interface UserDAO extends CrudRepository<User,Long> {
	  @Query("SELECT sq1.question,sq2.question FROM SecurityQuestions sq1,SecurityQuestions sq2 where (sq1.questionid,sq2.questionid) in  (select sa.securityQueid1,sa.securityQueid2 from SecurityAns sa where sa.secId in( select p.securityAns from Register p WHERE p.emailid=:email))")
    String findQuestionsById(@Param("email")String email) ;
	  @Query("select sa.securityAnsid1,sa.securityAnsid2 from SecurityAns sa where sa.secId in( select p.securityAns from Register p WHERE p.emailid=:email)")

    String findAnswersById(@Param("email")String  email);
    @Query("SELECT count(*) FROM Register p WHERE p.emailid=:email")
    public int findByEmail(@Param("email") String email);
    @Modifying(clearAutomatically=true)
    @Query("Update PasswordHistory p set p.password3=p.password2,p.password2=p.password1,p.salt3=p.salt2,p.salt2=p.salt1 where p.passId =(select r.passwordHistory from Register r where r.emailid=:email)")
   
    public void changeColumns(@Param("email") String email);
    @Modifying(clearAutomatically=true)
    
 @Query("UPDATE PasswordHistory p set p.password1=:password,p.salt1=:salt  WHERE p.passId=(select r.passwordHistory from Register r where r.emailid=:email)")
 int setPassword(@Param("password") String password,@Param("salt") String salt,@Param("email") String email);

}
