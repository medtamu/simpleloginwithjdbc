package patientlogin.dao;

import java.security.NoSuchAlgorithmException;

import javax.sql.DataSource;
import patientlogin.Patient;


public interface ipatientdao {
	
	    public abstract boolean authenticatePatient(Patient patient);
        public abstract boolean registerPatient(Patient patient);
}
