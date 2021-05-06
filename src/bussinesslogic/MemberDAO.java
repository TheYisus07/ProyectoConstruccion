package bussinesslogic;

import dataaccess.Conection;
import domain.Member;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Javier Blas
 */
public class MemberDAO implements IMemberDAO{
    
    private final Conection connection = new Conection();
    
    @Override
    public ArrayList<Member> consultMemberList() {
        ArrayList<Member> arrayListMembers = new ArrayList<>();     
        try {
            connection.connect();
            String query = "SELECT fullName, dateOfBirth, curp, phoneNumber, institutionalMail, discipline, studyGrade, studyArea, typeOfTeaching, lgac, ies, prodepParticipation, position, academicGroup_Keycode FROM member";
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                 arrayListMembers.add(new Member(resultSet.getString("FullName"), resultSet.getDate("DateOfBirth"), resultSet.getString("CURP"), resultSet.getString("PhoneNumber"), resultSet.getString("InstitutionalMail"), resultSet.getString("Discipline"), resultSet.getString("StudyGrade"), resultSet.getString("StudyArea"),resultSet.getString("TypeOfTeaching"), resultSet.getString("LGAC"), resultSet.getString("IES"), resultSet.getString("ProdepParticipation"), resultSet.getString("Position"), resultSet.getString("academicGroup_Keycode"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return arrayListMembers;
    }
    

    @Override
    public Member consultMember(String memberFullName) {
        String query = "SELECT FullName, CURP, academicGroup_Keycode FROM member WHERE FullName = ?";
        Member member = null; 
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, memberFullName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                member = new Member();
                member.setFullName(resultSet.getString("FullName"));                    
                member.setCurp(resultSet.getString("CURP"));
                member.setKeycodeAcademicGroup(resultSet.getString("academicGroup_Keycode")); 
            }  
        } catch (SQLException ex) {
            Logger.getLogger(MemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }
        return member;
    }

    @Override
    public Member registerMember(Member member) {
        try {
            connection.connect();
        } catch (SQLException ex) {
            Logger.getLogger(MemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String query = ("INSERT INTO member (fullName, dateOfBirth, curp, phoneNumber, institutionalMail, discipline, studyGrade, studyArea, typeOfTeaching, lgac, ies, prodepParticipation, position, academicGroup_Keycode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, member.getFullName());
            preparedStatement.setDate(2, (Date) member.getDateOfBirth());
            preparedStatement.setString(3, member.getCurp());
            preparedStatement.setString(4, member.getPhoneNumber());
            preparedStatement.setString(5, member.getInstitutionalMail());
            preparedStatement.setString(6, member.getDiscipline());
            preparedStatement.setString(7, member.getStudyGrade());
            preparedStatement.setString(8, member.getStudyArea());
            preparedStatement.setString(9, member.getTypeOfTeaching());
            preparedStatement.setString(10, member.getLgac());
            preparedStatement.setString(11, member.getIes());
            preparedStatement.setString(12, member.getProdepParticipation());
            preparedStatement.setString(13, member.getPosition());
            preparedStatement.setString(14, member.getKeycodeAcademicGroup());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return member;
    }

    @Override
    public int removeMember(String fullName, String curp) {
        String query = "DELETE FROM member WHERE fullName = ? AND curp = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, curp);
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}