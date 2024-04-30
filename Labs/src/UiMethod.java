import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class UiMethod {

    // ui method for user
    public static void addPlan(MobilePlan mobilePlan, User user) {
        if (user.addPlan(mobilePlan)) {
            System.out.println(" The plan has been successfully added ");
        } else {
            System.out.println(" The plan unsuccessfull ");
        }
    }

    public static void loginUser(User user, int userID, String password) {
        if (user.validateUser(userID, password)) {
            System.out.println(" User Login Successful ");
        } else {
            System.out.println(" User Login Unsuccessful ");
        }
    }

    // company ui method
    public static boolean loginAdmin(MobileCompany mobileCompany, String userName, String password) {
        if (mobileCompany.validateAdmin(userName, password) == true) {
            System.out.println(" " + mobileCompany.getName() + " is login ");
            return true;
        } else {
            System.out.println(" userName or password is incorrect ");
            System.out.println(" please try agian ");
            return false;
        }
    }

    public static void addUser(MobileCompany mobileCompany, String name, int userID, Address address, String password) {
        if (mobileCompany.addUser(name, userID, address, password)) {
            System.out.println(name + " was not added ");
            System.out.println(" please try agin ");
        } else if (mobileCompany.addUser(name, userID, address, password)) {
            System.out.println(name + " is  added ");
        }
    }

    public static void addPlan(MobileCompany mobileCompany, int userID, MobilePlan plan) {
        if (mobileCompany.addPlan(userID, plan) == true) {
            System.out.println(" This plan was added ");
        } else if (mobileCompany.addPlan(userID, plan) == false) {
            System.out.println(" This plan wasn't added ");
            System.out.println(" please try agin ");
        }
    }

    public static void createPersonalPlan(MobileCompany mobileCompany, int userID, String userName, int ID,
            MobilePhone handSet, int internetQuota, int capLimit, MyDate date, String city) throws PlanException {
        System.out.println(" check  create personalPlan ");
        if (mobileCompany.createPersonalPlan(userID, userName, ID, handSet, internetQuota, capLimit, date,
                city)) {
            System.out.println(" there is  successful added ");
        } else {
            System.out.println(" the plan is not successful  added ");
            System.out.println(" please try agin ");
        }
    }

    public static void createBusinessPlan(MobileCompany mobileCompany, String userName, int ID, int userID,
            MobilePhone handSet, int internetQuota, int capLimit, MyDate date, int numberOfEmployees, int ABN)
            throws PlanException,UNException {
        System.out.println(" check create BusinessPlan ");
        if (mobileCompany.createBusinessPlan(userID, userName, ID, handSet, internetQuota, capLimit, date,
                numberOfEmployees, ABN)) {
            System.out.println(" there is successful added ");
        } else {
            System.out.println(" the plan is not successful added ");
            System.out.println(" please try agin ");
        }
    }
    public static void addBusinessPlan(User user, String userName, int planID, MobilePhone mobile, int internetQuota, int capLimit, MyDate expiryDate, int numOfEmployees, int ABN) throws PlanException, UNException {      
       try {
            if (user.createBusinessPlan(userName,planID,mobile, internetQuota, capLimit, expiryDate, numOfEmployees, ABN)) {
                System.out.println("The plan was added successfully");
            } else {
                System.out.println("The plan can't be added.");
            }
        } catch (PlanException e) {
            System.out.println(e.toString());
            user.createBusinessPlan(userName,planID, mobile, internetQuota, capLimit, expiryDate, numOfEmployees, ABN);
        } 
    }

    public static void addPersonalPlan(User user, String userName, int planID, MobilePhone mobile, int internetQuota, int capLimit, MyDate expiryDate, String city) throws PlanException, UNException {
        
                try {
            if (user.createPersonalPlan(userName,planID,mobile, internetQuota, capLimit, expiryDate, city)) {
                System.out.println("The plan was added successfully");
            } else {
                System.out.println("The plan can't be added.");
            }
        } catch (PlanException e) {
            System.out.println(e.toString());
            user.createPersonalPlan(userName,planID, mobile, internetQuota, capLimit, expiryDate, city);
        } 
           
    }

    public static void printPlans(MobileCompany mobileCompany, int userID) {
        mobileCompany.printPlans(userID);
    }

    public static void findPlan(MobileCompany mobileCompany, int userID, int planID) {
        System.out.println(" find plan ");
        MobilePlan plan = mobileCompany.findPlan(userID, planID);
        if (plan != null) {
            plan.print();
        } else {
            System.out.println(" plan was not found ");
        }
    }

    public static void mobilePriceRise(MobileCompany mobileCompany) {
        mobileCompany.mobilePriceRise(0.1);
        mobileCompany.print();
    }

    public static void calctoTtalPaymentsForOneUser(MobileCompany mobileCompany, int userID) {
        System.out.println(" montly payment : " + mobileCompany.calcTotalPayments(userID));
    }

    public static void calcTotalPaymentsForAllUser(MobileCompany mobileCompany) {
        System.out.println(" montly payment for all user  : " + mobileCompany.calcTotalPayments());
    }

    // public static void allPlans(MobileCompany mobileCompany) {
    // ArrayList<MobilePlan> allPlans = mobileCompany.allPlans();
    // MobilePlan.printPlans(allPlans);
    // }

    // public static void filterByExpiryDate(MobileCompany mobileCompany, int
    // userID, MyDate date) {
    // System.out.println(" filter by expiry Date ");
    // ArrayList<MobilePlan> filterPlans = mobileCompany.filterByExpiryDate(userID,
    // date);
    // MobilePlan.printPlans(filterPlans);
    // }

    // public static void filterByMobileModel(MobileCompany mobileCompany, int
    // userID, String mobileModel) {
    // System.out.println(" filter by mobile model ");
    // ArrayList<MobilePlan> filterPlans = mobileCompany.filterByMobileModel(userID,
    // mobileModel);
    // MobilePlan.printPlans(filterPlans);
    // }

    // public static void filterByExpiryDate2(MobileCompany mobileCompany, MyDate
    // date) {
    // System.out.println(" filter by expiry Date ");
    // ArrayList<MobilePlan> filterPlans = mobileCompany.filterByExpiryDate(date);
    // MobilePlan.printPlans(filterPlans);
    // }

    // public static void reportDataAggregation(MobileCompany mobileCompany) {
    // mobileCompany.reportPaymentPerCity(mobileCompany.populateDistinctCityNames(),
    // mobileCompany.getTotalPaymentPerCity(mobileCompany.populateDistinctCityNames()));
    // }

    public static void findUser(MobileCompany mobileCompany, int userID, Address address) {
        User user = mobileCompany.findUser(userID);
        if (user != null) {
            user.setAddress(address);
            user.print();
        } else
            System.out.println(" Plan has not found ");
    }

    public static void changeAddress(MobileCompany mobileCompany, int userId, Address address) {
        System.out.println(" change addrres ");
        User user = mobileCompany.findUser(userId);
        if (user != null) {
            System.out.println(" your old adress : ");
            user.print();
            System.out.println(" your new adress : ");
            user.setAddress(address);
            user.print();
        }
    }

    public static MobilePlan personalInformation() {
            Scanner sc = new Scanner(System.in);
            try {
                return new PersonalPlan(getUserName(), getMobilePhone(),
                        getInternetQuota(),
                        getCapLimit(), getDate(), getCity());
            } catch (PlanException e) {
                System.out.println(e);
            }
            return null;
    }

    
    public static MobilePlan businessInformation() {
        Scanner sc = new Scanner(System.in);
        try {
         return new BusinessPlan(getUserName(), getMobilePhone(), getInternetQuota(),
                getCapLimit(), getDate(), getNumberOfEmployees(), getABN());
    } catch(PlanException e){
        System.out.println(e);
    }
    return null;
}

    public static MyDate getDate() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" enter year ");
            int year = sc.nextInt();
            sc.nextLine();
            System.out.println(" enter mounth ");
            int mounth = sc.nextInt();
            sc.nextLine();
            System.out.println(" enter day ");
            int day = sc.nextInt();
            sc.nextLine();
            MyDate date = new MyDate(year, mounth, day);
            return date;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return null;

    }

    public static MobilePhone getMobilePhone() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter mobile model ");
            String model = sc.nextLine();
            System.out.println(" please choose a ithem ");
            System.out.println(" 1) : Android ");
            System.out.println(" 2) : Ios ");
            System.out.println(" 3) : Windows ");
            int option = sc.nextInt();
            sc.nextLine();
            MobilePhone mobilePhone = new MobilePhone(model, MobileType.values()[option], getMemorySize(), getPrice());
            return mobilePhone;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return null;
    }

    public static Address getAddress() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" enter the city name ");
            String city = sc.nextLine();
            System.out.println(" enter the suburb name ");
            String suburb = sc.nextLine();
            System.out.println(" enter the street name ");
            String street = sc.nextLine();
            System.out.println(" enter the street number ");
            int streetNum = sc.nextInt();
            sc.nextLine();
            Address address = new Address(streetNum, street, suburb, city);
            return address;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return null;
    }

    public static int getUserID() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter your  user id ");
            int id = sc.nextInt();
            sc.nextLine();
            return id;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static int getPlanID() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter your planID ");
            int planID = sc.nextInt();
            sc.nextLine();
            return planID;

        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return 0;

    }

    public static String getCity() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter city ");
            String city = sc.nextLine();
            return city;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return "";
    }

    public static String getUserName() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter userName ");
            String userName = sc.nextLine();
            return userName;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return "";
    }

    public static String getPassowrd() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter password ");
            String password = sc.nextLine();
            return password;
        } catch (IncompatibleClassChangeError e) {
            System.out.println(e);
        }
        return "";

    }

    public static int getInternetQuota() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter your internet Quota ");
            int internetQuota = sc.nextInt();
            sc.nextLine();
            return internetQuota;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static int getCapLimit() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter capLimit ");
            int capLimit = sc.nextInt();
            sc.nextLine();
            return capLimit;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static int getMemorySize() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter memory Size ");
            int memorySize = sc.nextInt();
            sc.nextLine();
            return memorySize;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static int getNumberOfEmployees() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter number Of Employees ");
            int numberOfEmployees = sc.nextInt();
            sc.nextLine();
            return numberOfEmployees;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static int getABN() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter ABN ");
            int ABN = sc.nextInt();
            sc.nextLine();
            return ABN;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static double getPrice() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter price ");
            int price = sc.nextInt();
            sc.nextLine();
            return price;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static String getName() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter name ");
            String name = sc.nextLine();
            return name;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return "";

    }

    public static String getModel() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(" please enter mobile model ");
            String model = sc.nextLine();
            return model;
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return "";
    }

    public static void printMobilePlan(ArrayList<MobilePlan> mobilePlan) {
        MobilePlan.printPlans(mobilePlan);
    }

    // public static void printSortedPlansByExpairyDate(ArrayList<User> user, int
    // userID) {
    // MobilePlan.printPlans(user.getUserID().sortedByExpiryDate());

    // }

    // lab 5

    public static void allPlans(MobileCompany mobileCompany, HashMap<Integer, MobilePlan> allPlans) {
        allPlans = mobileCompany.allPlans();
        MobilePlan.printPlans(allPlans);
    }

    public static void filterByExpiryDate(MobileCompany mobileCompany, HashMap<Integer, MobilePlan> filterList) {
        filterList = mobileCompany.filterByExpiryDate(getUserID(), getDate());
        MobilePlan.printPlans(filterList);
    }

    public static void filterByMobileModel(MobileCompany mobileCompany, HashMap<Integer, MobilePlan> filterList) {
        filterList = mobileCompany.filterByMobileModel(getUserID(), getModel());
        MobilePlan.printPlans(filterList);
    }

    public static void deepCopyByHashMap(MobileCompany mobileCompany, HashMap<Integer, User> filterList)
            throws CloneNotSupportedException {
        filterList = mobileCompany.deepCopyUsersHashMap();
        User.printUser(filterList);
    }

    public static void shallowCopyByHashMap(MobileCompany mobileCompany, HashMap<Integer, User> filterList) {
        filterList = mobileCompany.shallowCopyUsersHashMap();
        User.printUser(filterList);
    }

    public static void deepCopy(MobileCompany mobileCompany, ArrayList<User> filterList)
            throws CloneNotSupportedException {
        filterList = mobileCompany.deepCopyUsers();
        User.printUser(filterList);
    }

    //  public static void sortUser(MobileCompany mobileCompany, HashMap<Integer,
    //  User> filterList) {
    //  filterList = mobileCompany.sortUser();
    //  User.printUser(filterList);
    //  }

     public static void sortUser(MobileCompany mobileCompany) {
     mobileCompany.sortUser();
     }

    public static void printSortedPlansByExpairyDate(HashMap<Integer, User> user, int userID) {
        MobilePlan.printPlans(user.get(userID).sortPlansByDate());

    }

    public static void printMobilePlan(HashMap<Integer, MobilePlan> mobilePlan) {
        MobilePlan.printPlans(mobilePlan);
    }
    // public static void reportPaymentsPerMobileModel(HashMap<Integer, User> user,
    // int userID,ArrayList<String>mobileModel) {
    // printMobilePlan(user.get(userID).getTotalPaymentPerMobileModel());
    // }
    public static String getFileName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter File Name: ");
        String fileName = sc.nextLine();
        return fileName;
    }
    
    public static void load(MobileCompany mobileCompany, String fileName) throws CloneNotSupportedException {
        mobileCompany.load(fileName);
    }

    public static void save(MobileCompany mobileCompany, String fileName) throws CloneNotSupportedException {
        mobileCompany.save(fileName);
    }

    public static void loadTextFile(MobileCompany mobileCompany, String fileName) throws CloneNotSupportedException, PlanException, IOException, UNException {
        mobileCompany.loadTextFile(fileName);
    }

    public static void saveTextFile(MobileCompany mobileCompany, String fileName) throws CloneNotSupportedException, IOException {
        mobileCompany.saveTextFile(fileName);
    }
}
