package christmas;

import christmas.Controller.ChristmasPlannerController;
import christmas.Repository.EventBenefitRepository;
import christmas.Service.ChristmasPlannerService;
import christmas.View.InputView;
import christmas.View.OutputView;

public class Application {
    public static void main(String[] args) {
        try {
            EventBenefitRepository eventBenefitRepository = new EventBenefitRepository(); // 적절한 생성자로 초기화
            ChristmasPlannerService christmasPlannerService = new ChristmasPlannerService(eventBenefitRepository);
            InputView inputView = new InputView();
            OutputView outputView = new OutputView();

            ChristmasPlannerController christmasPlannerController = new ChristmasPlannerController(christmasPlannerService, inputView, outputView);
            christmasPlannerController.processEventPlanning();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
