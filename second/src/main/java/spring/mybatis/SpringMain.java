package spring.mybatis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) throws IOException {
		ApplicationContext factory = new ClassPathXmlApplicationContext("spring/mybatis/spring-mybatis.xml");
		String names[] = factory.getBeanDefinitionNames();
		System.out.println("==================================");
		for (String n : names) {
			System.out.println(n);
		}
		System.out.println("==================================");

		// 4. sql-mapping.xml id=memberlist resultType="mybatis.MemberDTO" 정의 sql 호출
//		MemberDAO dao = new MemberDAO();

		MemberService service = (MemberService) factory.getBean("service");
//		((MemberServiceImpl) service).setDao(dao);
		// 리스트 조회
		List<MemberDTO> list = service.memberlist();
		for (MemberDTO m : list) {
			System.out.println(m.getId() + ":" + m.getPw() + ":" + m.getName());
		}

		// 전체 회원 수 (조회 int)
		System.out.println("전체회원 수 = " + service.membercount());

		MemberDTO m = service.onemember("mem10");
		if (m != null) {
			System.out.println(m.getId() + ":" + m.getPw() + ":" + m.getName());
		}

		/*
		 * // 페이징처리 리스트 조회 int[] limit = { 0, 3 }; List<MemberDTO> list2 =
		 * service.paginglist(limit); for (MemberDTO m2 : list2) {
		 * System.out.println(m2.getId() + ":" + m2.getPw() + ":" + m2.getName()); }
		 * 
		 * // insert
		 * 
		 * MemberDTO insertdto = new MemberDTO(); insertdto.setId("mybatis");
		 * insertdto.setPw("mybatis"); insertdto.setName("박대한");
		 * insertdto.setPhone("01012345678"); insertdto.setEmail("mybatis@test.com");
		 * insertdto.setAddress("서울시 용산구"); service.insertmember(insertdto);
		 * 
		 * 
		 * // update id=mybatis, name="박한국" phone=01087654321 email=mybatis@b.com 수정
		 * MemberDTO updatedto = new MemberDTO(); updatedto.setName("박한국");
		 * updatedto.setPhone("01087654321"); updatedto.setEmail("mybatis@b.com");
		 * updatedto.setId("mybatis"); int count = service.updatemember(updatedto);
		 * System.out.println(count);
		 * 
		 * // delete int count2 = service.deletemember("mybatis");
		 * System.out.println(count2);
		 * 
		 * HashMap<String, String> map = new HashMap(); map.put("colname", "indate");
		 * map.put("colvalue", "2023%"); List<MemberDTO> searchlist =
		 * service.searchmember(map); for (MemberDTO m3 : searchlist) {
		 * System.out.println(m3.getId() + ":" + m3.getPw() + ":" + m3.getName() + ":" +
		 * m3.getEmail() + ":" + m3.getPhone() + m3.getIndate()); }
		 * 
		 * // ArrayList<String> addresslist = new ArrayList();
		 * addresslist.add("서울시 용산구"); addresslist.add("제주시 용산구");
		 * addresslist.add("서울시 중구"); List<MemberDTO> list3 =
		 * service.addresssearch(addresslist);
		 * 
		 * for (MemberDTO m4 : list3) { System.out.println(m4.getId() + ":" + m4.getPw()
		 * + ":" + m4.getName() + ":" + m4.getEmail() + ":" + m4.getPhone() + ":" +
		 * m4.getAddress() + ":" + m4.getIndate()); }
		 * 
		 * // test10 조합+동적조건절
		 * 
		 * MemberDTO dto = new MemberDTO(); //dto.setName("유사원");
		 * dto.setEmail("kim3@sam.com"); List<MemberDTO> list4 =
		 * service.combination(dto);
		 * 
		 * for (MemberDTO m4 : list4) { System.out.println(m4.getId() + ":" + m4.getPw()
		 * + ":" + m4.getName() + ":" + m4.getEmail() + ":" + m4.getPhone() + ":" +
		 * m4.getAddress() + ":" + m4.getIndate()); }
		 * 
		 * 
		 * List<HashMap<String, String>> boardlist = service.memberboard("maria"); for
		 * (HashMap map4 : boardlist) { Set<String> keys = map4.keySet(); for (String s
		 * : keys) { System.out.println(s + " : " + map4.get(s)); } }
		 * 
		 * // 1. id 회원이 작성 글이 있는지 검사 // 2. 글이 있으면 사용자 탈퇴전에 글도 삭제하시겠습니까? y/n // 3-1. 사용자
		 * 탈퇴 // 3-2. 탈퇴 취소 MemberDTO m2 = service.onemember("maria");
		 * 
		 * if (m2 != null) { String writer = m2.id;
		 * System.out.println("글이 있으면 사용자 탈퇴전에 글도 삭제하시겠습니까? y/n"); Scanner scan = new
		 * Scanner(System.in); String isRemove = scan.next(); int result; if
		 * (isRemove.equals("y")) { service.deleteboard(writer); 
		 * result =
		 * service.deletemember(writer); } else {
		 * 
		 * result = service.deleteboard(writer); System.out.println(result); } }
		 */
	}

}
