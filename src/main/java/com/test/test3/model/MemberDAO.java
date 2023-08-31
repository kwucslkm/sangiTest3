package com.test.test3.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "system", "1234");
        return con;
	}
	
	public List<VoteRankingDTO> voteRanking() throws Exception{
		con = getConnection();
		String sql ="select m.m_no, m.m_name, count(v.m_no) "
				+ "        from TBL_VOTE_202005 v, tbl_member_202005 m "
				+ "        where v.m_no = m.m_no and v.v_confirm = 'Y'"
				+ "        group by m.m_no, m.m_name"
				+ "        order by count(v.m_no) desc";
		pstmt = con.prepareStatement(sql);
		
		rs=pstmt.executeQuery();
		List<VoteRankingDTO> v = new ArrayList<>();
		for(;rs.next();) {
			VoteRankingDTO voteRankingDTO = new VoteRankingDTO();
			voteRankingDTO.setM_no(rs.getString(1));
			voteRankingDTO.setM_name(rs.getString(2));
			voteRankingDTO.setVoteCount(rs.getString(3));
			v.add(voteRankingDTO);
		}
		return v;
	}
	
	public List<VoteDTO> voteList() throws Exception{
		con = getConnection();
		String sql = "select v_name, "
				+ "        case "
				+ "            when substr(v_jumin,7,1)='1' or substr(v_jumin,7,1)='2' then '19'||substr(v_jumin,1,2)||'년'"
				+ "            else '20'||substr(v_jumin,1,2)||'년'"
				+ "        end "
				+ "        ||substr(v_jumin,3,2)||'월'||substr(v_jumin,5,2)||'일생' as 생년월일,"
				+ "        '만'||(to_number(to_char(sysdate,'yyyy'))- "
				+ "        case "
				+ "            when substr(v_jumin,7,1)='1' or substr(v_jumin,7,1)='2' then to_number('19'||substr(v_jumin,1,2))"
				+ "            else to_number('20'||substr(v_jumin,1,2)) "
				+ "        end) ||"
				+ "        '세' as 나이,"
				+ "        case when substr(v_jumin,7,1)='1' or substr(v_jumin,7,1)='3' then '남성' "
				+ "             else '여성' "
				+ "        end as 성별, m_no as 후보번호, "
				+ "        substr(v_time,1,2)||':'||substr(v_time,3,2) as 투표시간,"
				+ "        decode(v_confirm,'Y','확인','N','미확인') 유권자확인 "
				+ "        from tbl_vote_202005 where v_area = '제1투표장'";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<VoteDTO> voteList = new ArrayList<>();
		for (;rs.next();) {
			VoteDTO v = new VoteDTO();
			v.setV_name(rs.getString(1));
			v.setV_birth(rs.getString(2));
			v.setV_age(rs.getString(3));
			v.setV_gender(rs.getString(4));
			v.setM_no(rs.getString(5));
			v.setV_time(rs.getString(6));
			v.setV_confirm(rs.getString(7));
			voteList.add(v);
		}
		return voteList;
	}
	public int saveVote(String v_jumin,String  v_name,String m_no,String  v_time,String  v_area,String  v_confirm) throws Exception{
//		System.out.println("주민번호, 성명, 투표번호, 투표시간, 투표장소, 유권자확인"+v_jumin+ v_name +m_no +v_time +v_area +v_confirm);
		con= getConnection();
		String sql = "insert into tbl_vote_202005 values (?,?,?,?,?,?) ";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, v_jumin);
		pstmt.setString(2, v_name);
		pstmt.setString(3, m_no);
		pstmt.setString(4, v_time);
		pstmt.setString(5, v_area);
		pstmt.setString(6, v_confirm);
		int result = pstmt.executeUpdate();
		return result;
	}
	public List<MemberDTO> memberList() throws Exception{
//		System.out.println("parameter"+member);
		con = getConnection();
        String sql = "select m.m_no, m.m_name,  p.p_name, decode(p_school,'1','고졸','2','학사','3','석사','4','박사') p_school, "
        		+ "substr(m_jumin,1,6)||'-'||substr(m_jumin,7,7), m.m_city, p_tel1||'-'||p_tel2||'-'||p_tel3"
        		+ " from tbl_member_202005 m, tbl_party_202005 p where m.p_code = p.p_code";
        System.out.println(sql);
//		String sql= "select m.m_no as 후보번호, m.m_name as 성명,  p.p_name as 소속정당, \r\n"
//				+ "        case\r\n"
//				+ "            when m.p_school = 1 then '고졸'\r\n"
//				+ "            when m.p_school = 2 then '학사'\r\n"
//				+ "            when m.p_school = 3 then '석사'\r\n"
//				+ "            when m.p_school = 4 then '박사'\r\n"
//				+ "        end as 학력, \r\n"
//				+ "        substr(m_jumin,1,6)||'-'||substr(m_jumin,7,7) as 주민번호,\r\n"
//				+ "        m.m_city as 지역구, p_tel1||'-'||p_tel2||'-'||p_tel3 as 대표전화\r\n"
//				+ "            from tbl_member_202005 m, tbl_party_202005 p where m.p_code = p.p_code";
		pstmt = con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		List<MemberDTO> memberDTOList = new ArrayList<>();
//		System.out.println("시작해볼까 rs.next()"+rs.next());
		System.out.println(rs.getRow());
		for(;rs.next();) {
			MemberDTO memberDTO = new MemberDTO();
			System.out.println(rs.getString(1));
			memberDTO.setM_no(rs.getString(1));
			memberDTO.setM_name(rs.getString(2));
			memberDTO.setP_name(rs.getString(3));
			memberDTO.setP_school(rs.getString(4));
			memberDTO.setM_jumin(rs.getString(5));
			memberDTO.setM_city(rs.getString(6));
			memberDTO.setP_tel(rs.getString(7));
			memberDTOList.add(memberDTO);
		}
		System.out.println(memberDTOList);
		
		return memberDTOList;
	
	}
}
