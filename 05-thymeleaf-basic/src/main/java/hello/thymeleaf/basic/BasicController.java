package hello.thymeleaf.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {
    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

    /**
     * 실제 서비스를 개발하다 보면 escape를 사용하지 않아서 HTML이 정상 렌더링되지 않는 수 많은 문제가 발생한다.
     * escape를 기본으로 하고, 꼭 필요한 때만 unescape를 사용하자.
     * 왜 굳이 escape를 기본으로 할까?
     * 사용자들이 등록하는 데이터 유형은 예측할 수 없다. (그게 태그 형식일수도 있다.)
     * 따라서 escape 처리가 안되어있으면,
     * 해당 사용자가 임의로 작성한 태그에 의해서,
     * 게시판 html 화면 등이 다 깨질수 있다.
     * 따라서 기본으로 항상 escape 처리를 하되, 의도적으로 사용하고자 하는 경우 (어드민에서만 등록하고 하는 등) 에만 unescaped를 사용하자.
     * */
    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "<b>hello</b>");
        return "basic/text-basic";
    }

    @GetMapping("variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @GetMapping("basic-objects")
    public String basicObjects(
        Model model,
        HttpSession session,
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        session.setAttribute("sessionData", "Hello Session");
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        // boot 3.0이상

        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }

}
