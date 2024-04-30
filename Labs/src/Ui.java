import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Ui {
    private static MobileCompany mobileCompany;

    public static void printMainMenu() {
        System.out.println(" ----- main menu ----- ");
        System.out.println(" 1) : admin login ");
        System.out.println(" 2) : user login ");
        System.out.println(" 3) : exit \n ");
        System.out.println(" please choose option between three option ");
    }

    public static void mainMenu(MobileCompany mobileCompany)
            throws CloneNotSupportedException, PlanException, IOException, UNException {
        Scanner sc = new Scanner(System.in);
        printMainMenu();
        int number = 0;
        number = sc.nextInt();
        while (number != 3) {
            switch (number) {
                case 1:
                    if (loginAdmin(mobileCompany)) {
                        break;
                    }
                case 2:
                    if (userLogin(mobileCompany)) {
                    }
                case 3:
                    break;
            }

        }

    }

    public static void pressEnterToContinue() {
        System.out.println(" Press Enter Key to Continue...");
        try {
            System.in.read();
        } catch (Exception e) {

        }
    }

    public static void printAdminMenu() {
        System.out.println(" \n\n\n\n\n\n\n\n\n\n\n\n ");
        System.out.println(" ------- admin menu ------ ");
        System.out.println(" 1) : Test code ");
        System.out.println(" 2) : Create User ");
        System.out.println(" 3) : Create Personal Plan ");
        System.out.println(" 4) : Create Business Plan ");
        System.out.println(" 5) : Print User Information ");
        System.out.println(" 6) : Filter by Mobile Model ");
        System.out.println(" 7) : Filter by Expiry Date ");
        System.out.println(" 8) : Update Address ");
        System.out.println(" 9) : Save To Binary File");
        System.out.println(" 10) : load from Binary File");
        System.out.println(" 11) : Load From Text File");
        System.out.println(" 12) : Save To Text File");
        System.out.println(" 13) : Log Out ");
    }

    public static void adminMenu(MobileCompany mobileCompany)
            throws CloneNotSupportedException, PlanException, IOException, UNException {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        while (number != 9) {
            printAdminMenu();
            number = sc.nextInt();
            sc.nextLine();
            switch (number) {
                case 1:
                    testCode(mobileCompany);
                    adminMenu(mobileCompany);
                    break;
                case 2:
                    addUser(mobileCompany);
                    break;
                case 3:
                    createPersonalPlan(mobileCompany);
                    break;
                case 4:
                    createBusinessPlan(mobileCompany);
                    break;
                case 5:
                    printUserInformation(mobileCompany);
                    break;
                case 6:
                    filterByMobileModel(mobileCompany);
                    break;
                case 7:
                    filterByExpiryDate(mobileCompany);
                    break;
                case 8:
                    updateAddress(mobileCompany);
                    break;
                case 9:
                    load(mobileCompany);
                    break;
                case 10:
                    save(mobileCompany);
                    break;
                case 11:
                    loadTextFile(mobileCompany);
                    break;
                case 12:
                    saveTextFile(mobileCompany);
                    break;
                case 13:
                    mainMenu(mobileCompany);
                    break;
                default:
                    System.out.println(" Log Out ");
            }
        }
        sc.close();
    }

    public static void printUserMenu() {
        System.out.println(" \n\n\n\n\n\n\n\n\n\n\n\n ");
        System.out.println(" --------- user menu -------- ");
        System.out.println(" 1) : Calculate total payments ");
        System.out.println(" 2) : print plans ");
        System.out.println(" 3) : Mobile price rise all plan ");
        System.out.println(" 4) : Filter by mobile model ");
        System.out.println(" 5) : Filter by expiry date ");
        System.out.println(" 6) : Create personal Plan ");
        System.out.println(" 7) : Create business Plan ");
        System.out.println(" 8) : report Payments Per Mobile Model ");
        System.out.println(" 9) : Report Mobile Model, Total Monthly Payment, and Average Monthly Payment");
        System.out.println(" 10) : Log Out ");
    }

    public static void userMenu(User user) {
        try {
            Scanner sc = new Scanner(System.in);
            int number = 0;
            while (number != 9) {
                printUserMenu();
                number = sc.nextInt();
                sc.nextLine();
                switch (number) {
                    case 1:
                        CalculateTotalPayments(user);
                        break;
                    case 2:
                        printPlans(user);
                        break;
                    case 3:
                        MobilepriceRiseAllPlan(user);
                        break;
                    case 4:
                        filterByMobileModel(user);
                        break;
                    case 5:
                        filterByExpiryDate(user);
                        break;
                    case 6:
                        createPersonalPlan(user);
                        break;
                    case 7:
                        createBusinesslPlan(user);
                        break;
                    case 8:
                        reportPaymentsPerMobileModel(user);
                        break;
                    case 9:
                    case 10:
                        break;
                    default:
                        System.out.println(" Log Out ");
                }
            }
            sc.close();
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
    }

    public static void createBusinessPlan(MobileCompany mobileCompany) {
        try {
            UiMethod.addPlan(mobileCompany, UiMethod.getUserID(), UiMethod.businessInformation());
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
    }

    public static void createPersonalPlan(MobileCompany mobileCompany) {
        try {
            UiMethod.addPlan(mobileCompany, UiMethod.getUserID(), UiMethod.personalInformation());
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
    }

    public static void filterByMobileModel(MobileCompany mobileCompany) {
        // UiMethod.filterByMobileModel(mobileCompany, UiMethod.getUserID(),
        // UiMethod.getModel());
    }

    public static void filterByExpiryDate(MobileCompany mobileCompany) {
        // UiMethod.filterByExpiryDate(mobileCompany, UiMethod.getUserID(),
        // UiMethod.getDate());

    }

    public static void addUser(MobileCompany mobileCompany) {
        UiMethod.addUser(mobileCompany, UiMethod.getName(), UiMethod.getUserID(), UiMethod.getAddress(),
                UiMethod.getPassowrd());
    }

    public static void printUserInformation(MobileCompany mobileCompany) {
        UiMethod.printPlans(mobileCompany, UiMethod.getUserID());
    }

    public static void testCode(MobileCompany mobileCompany)
            throws CloneNotSupportedException, PlanException, IOException, UNException {
        TestCode.testCode(mobileCompany);
    }

    public static void updateAddress(MobileCompany mobileCompany) {
        UiMethod.changeAddress(mobileCompany, UiMethod.getUserID(), UiMethod.getAddress());
    }

    public static boolean loginAdmin(MobileCompany mobileCompany)
            throws CloneNotSupportedException, PlanException, IOException, UNException {
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println(" \n\n\n\n\n\n\n\n\n\n ");
            System.out.println(" -- admin Login --- ");
            try {
                if (mobileCompany.validateAdmin(UiMethod.getUserName(), UiMethod.getPassowrd()) == true) {
                    System.out.println(mobileCompany.getName() + " is login ");
                    adminMenu(mobileCompany);
                    loggedIn = true;
                } else {
                    System.out.println(" userName or password is incorrect ");
                    System.out.println(" please try agian ");
                }
            } catch (InputMismatchException e) {
                System.out.println(e);
            }

        }
        return false;
    }

    public static boolean userLogin(MobileCompany mobileCompany) {
        boolean loggedIn = false;
        User user;
        while (!loggedIn) {
            System.out.println(" \n\n\n\n\n\n\n\n\n\n ");
            System.out.println(" -- User Login --- ");
            user = mobileCompany.validateUser(UiMethod.getUserID(), UiMethod.getPassowrd());
            if (user != null) {
                loggedIn = true;
                System.out.println(mobileCompany.getName() + " Login Successful ");
                userMenu(user);
            } else {
                System.out.println(" Login Unsuccessful ");
                System.out.println(" please try agian ");

            }
        }
        return false;

    }

    public static void printPlans(MobileCompany mobileCompany) {
        UiMethod.printPlans(mobileCompany, UiMethod.getUserID());
    }

    public static void calculateTotalPayments(MobileCompany mobileCompany) {
        UiMethod.calctoTtalPaymentsForOneUser(mobileCompany, UiMethod.getUserID());
    }

    public static void mobilePriceRiseAll(MobileCompany mobileCompany) {
        UiMethod.mobilePriceRise(mobileCompany);
    }

    // public static void populateDistinctCityNames(MobileCompany mobileCompany) {
    // UiMethod.populateDistinctCityNames(mobileCompany);
    // }

    // public static void totalPaymentPerCity(MobileCompany mobileCompany) {
    // UiMethod.totalPaymentForCity(mobileCompany);
    // }

    // public static void totalPaymentForCity(MobileCompany mobileCompany) {
    // UiMethod.totalPaymentForCity(mobileCompany);
    // }

    // public static void reportPaymentPerCity(MobileCompany mobileCompany) {
    // Ui.reportPaymentPerCity(mobileCompany);
    // }

    // user method
    public static void printPlans(User user) {
        user.print();
    }

    public static void CalculateTotalPayments(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" please enter flaRate ");
        int flatRate = sc.nextInt();
        sc.nextLine();
        user.calcTotalPayments(flatRate);
    }

    public static void MobilepriceRiseAllPlan(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" please enter rise Percent ");
        int risePercent = sc.nextInt();
        sc.nextLine();
        user.mobilePriceRiseAll(risePercent);
    }

    public static void filterByMobileModel(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" please enter mobile model ");
        String mobileModel = sc.nextLine();
        MobilePlan.printPlans(user.filterByMobileModel(mobileModel));
    }

    public static void filterByExpiryDate(User user) {
        System.out.println(" please enter Expiry Date ");
        MobilePlan.printPlans(user.filterByExpiryDate(UiMethod.getDate()));
    }

    public static void createPersonalPlan(User user) {
        UiMethod.addPlan(UiMethod.personalInformation(), user);
    }

    public static void createBusinesslPlan(User user) {
        UiMethod.addPlan(UiMethod.businessInformation(), user);
    }

    public static void reportPaymentsPerMobileModel(User user) {
        System.out.println("--------- Report Mobile Model, Total Monthly Payment, and Average Monthly Payment for User"
                + user.getUserID() + " ---------");
        HashMap<String, Integer> count = user.getTotalCountPerMobileModel();
        HashMap<String, Double> payment = user.getTotalPaymentPerMobileModel();
        user.reportPaymentsPerMobileModel(count, payment);
    }

    public static void load(MobileCompany mobileCompany) throws CloneNotSupportedException {
        mobileCompany.load(UiMethod.getFileName());
    }

    public static void save(MobileCompany mobileCompany) throws CloneNotSupportedException {
        mobileCompany.save(UiMethod.getFileName());
    }

    public static void loadTextFile(MobileCompany mobileCompany)
            throws CloneNotSupportedException, PlanException, IOException, UNException {
        mobileCompany.loadTextFile(UiMethod.getFileName());
    }

    public static void saveTextFile(MobileCompany mobileCompany) throws CloneNotSupportedException, IOException {
        mobileCompany.saveTextFile(UiMethod.getFileName());
    }
}
