package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandle(UserException e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }

    /**
     * @ControllerAdvice
     * 대상으로 지정한 여러 컨트롤러에 @ExceptionHandler, @InitBinder 기능을 부여해주는 역할
     * @ControllerAdvice에 대상을 지정하지 않으면 모든 컨트롤러에 적용 (글로벌 적용)
     * @RestControllerAdvice는 @ControllerAdvice와 같고,
     * @ResponseBody가 추가되어 있음. @Controller, @RestController의 차이와 같음
     *
     *
     * 적용 방법
     * // Target all Controllers annotated with @RestController
     * @ControllerAdvice(annotations = RestController.class)
     * public class ExampleAdvice1 {}
     *
     * // Target all Controllers within specific packages
     * @ControllerAdvice("org.example.controllers")
     * public class ExampleAdvice2 {}
     *
     * // Target all Controllers assignable to specific classes
     * @ControllerAdvice(assignableTypes = {ControllerInterface.class,
     * AbstractController.class})
     * public class ExampleAdvice3 {}
     * */
}
