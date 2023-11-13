package christmas.Controller;

import christmas.Domain.Order;
import christmas.Exception.DateException;
import christmas.Repository.OrderRepository;
import christmas.Service.ChristmasPlannerService;
import christmas.Util.DateValidator;
import christmas.Util.InputParser;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.List;

public class ChristmasPlannerController {
    private final ChristmasPlannerService christmasPlannerService;
    private final InputView inputView;
    private final OutputView outputView;
    private boolean orderMenuPrinted = true;
    private boolean benefitsPrinted = true;
    public ChristmasPlannerController(ChristmasPlannerService christmasPlannerService, InputView inputView,
                                      OutputView outputView) {
        this.christmasPlannerService = christmasPlannerService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void processEventPlanning() {
        outputView.printEventPlannerGreeting();

        int dateInput;
        while (true) {
            try {
                dateInput = inputView.getDate();
                if (DateValidator.isValidDate(String.valueOf(dateInput))) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                continue;
            }

            DateException dateException = new DateException();
            try {
                dateException.checkDate("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.", dateInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }

        int date = InputParser.parseDate(String.valueOf(dateInput));


        String orderInput = inputView.getOrder();
        List<Order> orders = null;

        while (true) {
            try {
                orders = InputParser.parseOrders(orderInput);
                christmasPlannerService.processOrders(date, orders);
                OrderRepository.addOrder(orders);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            orderInput = inputView.getOrder();
        }

        outputView.printOrderPreview(dateInput, orders);

        if (!orderMenuPrinted) {
            outputView.printOrderMenu(christmasPlannerService.getOrders());
            orderMenuPrinted = false;
        }

        int totalAmount = calculateTotalOrderAmount(orders);
        String giftMenu = calculateGiftMenu(totalAmount);

        outputView.printTotalOrderAmount(totalAmount);

        outputView.printGiftMenu(giftMenu);

        printBenefitsAndEventBadge(christmasPlannerService.getEventBadge());

        int totalDiscount = christmasPlannerService.calculateTotalDiscount();
        int totalBenefits = christmasPlannerService.calculateTotalBenefit();

        outputView.printTotalBenefitsAmount(totalBenefits);

        if (!benefitsPrinted) {
            outputView.printTotalBenefitsAmount(totalBenefits);
            benefitsPrinted = false;
        }

        outputView.printTotalPaymentAmount(totalAmount, totalDiscount);

        printBenefitsAndEventBadge(christmasPlannerService.getEventBadge());
    }

    private void printBenefitsAndEventBadge(String eventBadge) {
        List<String> eventBenefitDetails = christmasPlannerService.calculateEventBenefits();

        outputView.printEventBenefits(eventBenefitDetails);

        outputView.printEventBadge(new OutputView.EventBadge(eventBadge));
    }

    private int calculateTotalOrderAmount(List<Order> orders) {
        return orders.stream().mapToInt(Order::getTotalPrice).sum();
    }

    private String calculateGiftMenu(int totalAmount) {
        if (totalAmount >= 120000) {
            return "샴페인 1개";
        }
        return "없음";
    }
}
