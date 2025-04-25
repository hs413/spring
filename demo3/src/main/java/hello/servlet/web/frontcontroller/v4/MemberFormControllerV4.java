package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {
    /*@Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }*/

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form"; // 뷰의 논리 이름만 반환하면 된다.
    }
}
