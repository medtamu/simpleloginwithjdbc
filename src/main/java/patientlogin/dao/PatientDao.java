package patientlogin.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.sql.DataSource;
import patientlogin.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.hash.Hashing;

public class PatientDao implements ipatientdao {
//private JdbcTemplate jdbcTemplate;
	
  
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//@SuppressWarnings("deprecation")
	public boolean authenticatePatient(Patient patient){
		boolean userExists = false;
		String hashedpass = Hashing.sha256()
      		  .hashString(patient.getPassword(), StandardCharsets.UTF_8)
      		  .toString();
		int rows = jdbcTemplate.queryForObject("select count(*) from Patient " +
				" where username = ? and password = ?", Integer.class,
				patient.getUsername(),hashedpass);
		     
				if(rows==1){
			userExists = true;
		}
		return userExists;
	}

      //  @SuppressWarnings("deprecation")
        public boolean registerPatient(Patient patient) {
         boolean success = false ;
         
         //MessageDigest digest = MessageDigest.getInstance("SHA-256");
         //byte[] hashedpass = digest.digest(
           //patient.getPassword().getBytes(StandardCharsets.UTF_8));
         
         String hashedpass = Hashing.sha256()
        		  .hashString(patient.getPassword(), StandardCharsets.UTF_8)
        		  .toString();

         
         String insertPatient = "insert into Patient values (?,?,?,?,?,?,?,?,?)";
          Object[] values =  new Object[] {patient.getUsername(), hashedpass, patient.getFirstname(), patient.getLastname(), patient.getEmail(), patient.getAddress(), patient.getPhone(), patient.getSsn(), patient.getDob()};
        
         
         
          if(jdbcTemplate.update(insertPatient, values) >= 1) {
              success = true ;

            }
           System.out.println(success);
        //   return success; 
         
         

        	 
        	 
         
         
         
        return success;
       }


}
