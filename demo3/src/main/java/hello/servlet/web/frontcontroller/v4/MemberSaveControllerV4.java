package hello.servlet.web.frontcontroller.v4;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    /*@Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username"); // paramMap에서 필요한 요청 파라미터 조회
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member); // 모델에 뷰에서 필요한 객체를 담고 반환
        return mv;
    }*/

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member); // 모델이 파라미터로 전달되기 때문에 모델을 직접 생성하지 않아도 된다.

        return "save-result";
    }
}
