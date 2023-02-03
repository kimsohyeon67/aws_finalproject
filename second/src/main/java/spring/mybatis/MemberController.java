package spring.mybatis;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired
	@Qualifier("memberservice")
	MemberService service;

	@RequestMapping("/")
	public String start() {
		return "mybatis/start";
	}

	@GetMapping("login")
	public String login() {
		return "mybatis/loginform";
	}

	@PostMapping("login")
	public String login2(String id, String pw, HttpSession session) {
		MemberDTO dto = service.onemember(id);
		String view = "";
		if (dto == null) {
			// 회원가입화면 뷰
			view = "mybatis/memberinsert";
		} else {
			if (pw.equals(dto.getPw())) {
				// 로그인
				session.setAttribute("loginid", dto.id);
				view = "mybatis/start";
			} else {
				// 암호가 다르다
				view = "mybatis/loginform";
			}
		}
		return view;
	}

	@RequestMapping("/mybatismemberlist")
	public ModelAndView memberlist() {
		List<MemberDTO> memberlist = service.memberlist();
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberlist", memberlist);
		mv.setViewName("mybatis/memberlist");
		return mv;
	}

	@GetMapping("/memberinsert")
	public String memberinsert() {
		return "mybatis/memberinsert";

	}

	@PostMapping("/memberinsert")
	public ModelAndView memberisnert(MemberDTO dto) throws IOException {
		// dto 객체 저장 값 (폼 사용자값)
		// 가입일 입력 x
		// 저장한 결과 "정상회원가입처리" 모델로 저장
		// mybatis/memberinsert2 뷰
		ModelAndView mv = new ModelAndView();
		MemberDTO db_dto = service.onemember(dto.id);
		String result = "";

		if (db_dto == null) {
			dto.image = FileUpload(dto);
			int row = service.insertmember(dto);
			if (row == 1) {
				result = "정상회원가입처리";

			} else {
				result = "회원가입처리 오류 발생";
			}

		} else {
			result = "이미 사용중인 아이디입니다.";
		}
		mv.setViewName("mybatis/memberinsert2");
		mv.addObject("message", result);

		return mv;
	}

	@RequestMapping("memberinform")
	public ModelAndView memberinform(HttpSession session) {
		/*
		 * 1. HttpSession 객체에 저장된 loginid 값을 가져와서 2. service.onmember() 호출 3. 모델 저장 4.
		 * mybatis/memberinform 뷰 5. 뷰 - form 태그로 출력 submit-내정보수정
		 */
		ModelAndView mv = new ModelAndView();

		if (session.getAttribute("loginid") != null) {
			String id = (String) session.getAttribute("loginid");
			MemberDTO member = service.onemember(id);

			mv.setViewName("mybatis/memberinform");
			mv.addObject("member", member);
		} else {
			mv.setViewName("mybatis/loginform");
		}
		return mv;
	}

	@PostMapping("memberupdate")
	public ModelAndView memberinform2(MemberDTO dto) {
		/*
		 * 1. HttpSession 객체에 저장된 loginid 값을 가져와서 2. service.onmember() 호출 3. 모델 저장 4.
		 * mybatis/memberinform 뷰 5. 뷰 - form 태그로 출력 submit-내정보수정
		 */
		ModelAndView mv = new ModelAndView();
		int updateCount = service.updatemember2(dto);
		String message = "";
		if (updateCount == 1) {
			message = "수정되었습니다.";
			MemberDTO member = service.onemember(dto.id);
			mv.addObject("member", member);
		} else {
			message = "회원 정보 수정 에러";
		}
		mv.setViewName("mybatis/start");
		mv.addObject("message", message);
		return mv;

	}

	/* 로그아웃 */
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String message = "";
		if (session.getAttribute("loginid") != null) {
			session.removeAttribute("loginid");
			message = "로그아웃 되었습니다.";
		}
		mv.setViewName("mybatis/start");
		mv.addObject("message", message);
		return mv;
	}

	/* 회원탈퇴 */
	// 1. 세션에서 loginid 속성 얻는다.
	@GetMapping("memberdelete")
	public ModelAndView deletemember(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String message = "";

		if (session.getAttribute("loginid") != null) {
			String id = (String) session.getAttribute("loginid");
			int result = service.deletemember(id);
			System.out.println(result);
			message = result == 1 ? "회원 삭제 되었습니다." : "회원 삭제 과정에서 에러가 발생했습니다.";

		} else {
			message = "회원 삭제 에러";
		}

		mv.setViewName("mybatis/start");
		mv.addObject("message", message);
		return mv;
	}

	@ResponseBody
	@GetMapping("/othermemberinform")
	public MemberDTO othermemberinform(String id, HttpSession session) {
		/*
		 * 1. 로그인 되었는지 확인 2. 안된경우 start.jsp 3. 로그인 아이디가 "admin"인지 확인하여 4. 아래코드 수행
		 */
		String resultPage = "start";
		String model = "";
		MemberDTO dto = new MemberDTO();

		System.out.println(session.getAttribute("loginid"));

		if (session.getAttribute("loginid") == null) 
		{
			model = "로그인 이전입니다.";
			dto.setId(model);
		} 
		else if (!session.getAttribute("loginid").equals("admin")) {
			model="권한이 없습니다.";
			dto.setId(model);
		} 
		else {
			dto = service.onemember(id);
		}

		return dto;

	}

	public String FileUpload(MemberDTO dto) throws IOException {
		String savePath = "c:/upload/";
		MultipartFile mf1 = dto.getImagefile();

		// 파일명1 추출
		String filename1 = mf1.getOriginalFilename();

		// 파일이름. 확장자 분리
		String beforeext1 = filename1.substring(0, filename1.lastIndexOf('.'));
		String ext1 = filename1.substring(filename1.lastIndexOf('.'));
		// UUID.randomUUID()
		String newfilename1 = beforeext1 + "(" + UUID.randomUUID().toString() + ")" + ext1;

		// 파일내용1 추출해서 c:/upload/filename 저장
		File serverfile1 = new File(savePath + newfilename1);
		mf1.transferTo(serverfile1);
		return newfilename1;
	}
}
