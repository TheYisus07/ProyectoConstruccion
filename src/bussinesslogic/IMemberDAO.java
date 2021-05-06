package bussinesslogic;

import domain.Member;
import java.util.ArrayList;

/**
 *
 * @author Javier Blas
 */
public interface IMemberDAO {
    public ArrayList<Member> consultMemberList();
    public Member consultMember(String fullName);
    public Member registerMember (Member member);
    public int removeMember (String fullName, String curp);
}