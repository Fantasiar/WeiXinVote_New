package com.neuedu.model.mapper;

import java.util.List;

import com.neuedu.model.po.Candidate;
/**
 * 
 * @author feiyy
 *
 */
public interface CandidateMapper {
	
	
	public List<Candidate> getCandidates(int aid, int pagenum, int pageSize);

    public List<Candidate> getCandiateByName(String name);
    
    public void saveCandidate(Candidate c);

	public Candidate getCandidateById(int cid);
    
     
}
