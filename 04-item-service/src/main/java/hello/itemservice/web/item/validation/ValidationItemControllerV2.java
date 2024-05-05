package hello.itemservice.web.item.validation;

import hello.itemservice.domain.item.DeliveryCode;
import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.ItemType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {
    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        log.info("init binder {}", dataBinder);
        dataBinder.addValidators(itemValidator);
    }

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");
        return regions;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));
        return deliveryCodes;
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/item";
    }

    @GetMapping("/add")
    public String addFrom(Model model) {
        model.addAttribute("item", new Item());
        return "validation/addForm";
    }

    /**
     * RedirectAttributes
     */
//    @PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        Map<String, String> errors = new HashMap<>();

        //검증 로직
        if (!StringUtils.hasText(item.getItemName())) {
//            errors.put("itemName", "상품 이름은 필수입니다.");
            bindingResult.addError(new FieldError(
                "item", "itemName", "상품 이름은 필수입니다." ));
        }

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
//            errors.put("price", "가격은 1,000 ~ 1,000,000 까지 허용합니다.");
            bindingResult.addError(new FieldError(
                "item", "price", "가격은 1,000 ~ 1,000,000 까지 허용합니다." ));

        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
//            errors.put("quantity", "수량은 최대 9,999 까지 허용합니다.");
            bindingResult.addError(new FieldError(
                "item", "quantity", "수량은 최대 9,999 까지 허용합니다." ));
        }

        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();

            if (resultPrice < 10000) {
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.addError(new ObjectError(
                    "item",  "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
//            model.addAttribute("errors", errors);
            return "validation/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!StringUtils.hasText(item.getItemName())) {
            /**
             * objectName : 오류가 발생한 객체 이름
             * field : 오류 필드 (필드명)
             * rejectedValue : 사용자가 입력한 값 (거절된 값)
             * bindingFailure : 타입 오류 같은 바인딩 실패인지, 검증 실패인지 구분 값. (데이터 자체가 넘어오는 것에 실패했는지를 묻는 것. 위 예시같은 경우, 데이터 자체는 잘 들어왔고 이후 비즈니스 검증상 요구사항에 맞지않는 것이기 때문에 false로 지정한다.)
             * codes : 메시지 코드
             * arguments : 메시지에서 사용하는 인자
             * defaultMessage : 기본 오류 메시지
             * */
            bindingResult.addError(new FieldError(
                "item", "itemName", item.getItemName(), false,
                null, null , "상품 이름은 필수입니다." ));
        }

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError(
                "item", "price", item.getPrice(), false,
                null, null , "가격은 1,000 ~ 1,000,000 까지 허용합니다." ));

        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(new FieldError(
                "item", "quantity", item.getQuantity(), false,
                null, null , "수량은 최대 9,999 까지 허용합니다." ));
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();

            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError(
                    "item", null, null,
                    "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!StringUtils.hasText(item.getItemName())) {
            /**
             * objectName : 오류가 발생한 객체 이름
             * field : 오류 필드 (필드명)
             * rejectedValue : 사용자가 입력한 값 (거절된 값)
             * bindingFailure : 타입 오류 같은 바인딩 실패인지, 검증 실패인지 구분 값. (데이터 자체가 넘어오는 것에 실패했는지를 묻는 것. 위 예시같은 경우, 데이터 자체는 잘 들어왔고 이후 비즈니스 검증상 요구사항에 맞지않는 것이기 때문에 false로 지정한다.)
             * codes : 메시지 코드
             * arguments : 메시지에서 사용하는 인자
             * defaultMessage : 기본 오류 메시지
             * */
            bindingResult.addError(new FieldError(
                "item", "itemName", item.getItemName(), false,
                new String[] {"range.item.itemName"}, null , null ));
        }

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError(
                "item", "price", item.getPrice(), false,
                new String[] {"range.item.price"}, new Object[] {1000, 1000000} , null ));

        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(new FieldError(
                "item", "quantity", item.getQuantity(), false,
                new String[] {"range.item.quantity"}, new Object[] {9999} , null ));
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();

            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError(
                    "item",
                    new String[] {"range.item.quantity"}, new Object[] {10000, resultPrice},
                    null));
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/items/{itemId}";
    }

    /**
     * 크게 중요하지 않은 메시지는 범용성 있는 requried 같은 메시지로 끝내고,
     * 정말 중요한 메시지는 꼭 필요할 때 구체적으로 적어서 사용하는 방식이 더 효과적이다.
     * */
//    @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.rejectValue("itemName", "required");
        }

        // ValidationUtils 사용
        ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "itemName", "required");

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.rejectValue("price", "range",
                new Object[]{1000, 1000000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.rejectValue("quantity", "max",
                new Object[]{9999}, null);
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();

            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin",
                    new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV5(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        itemValidator.validate(item, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/items/{itemId}";
    }

    @PostMapping("/add")
    public String addItemV6(@Validated @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        itemValidator.validate(item, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/items/{itemId}";
    }

    /**
     * 테스트 데이터 추가
     * */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }
}
