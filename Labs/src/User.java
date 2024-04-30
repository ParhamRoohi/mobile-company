
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import static java.lang.String.format;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.stream.Collectors;

public class User implements Cloneable, Comparable<User>, Serializable {

    private String name;
    private int userID;
    private String userPassword;
    private Address address;
    // private ArrayList<MobilePlan> plans;
    private HashMap<Integer, MobilePlan> plans;
    private int lastUserID = 2000;

    public User(String name, int userID, Address address, String userPassword) {
        this.userPassword = userPassword;
        this.name = name;
        this.userID = userID;
        this.address = address;
        // plans = new ArrayList<MobilePlan>();
        plans = new HashMap<Integer, MobilePlan>();

    }

    public User(String name, Address address, String userPassword) {
        this.userPassword = userPassword;
        this.name = name;
        this.userID = lastUserID++;
        this.address = address;
        // plans = new ArrayList<MobilePlan>();
        plans = new HashMap<Integer, MobilePlan>();
    }

    public User(User user) {
        try {
            name = user.name;
            userID = user.userID;
            userPassword = user.userPassword;
            address = new Address(user.address);
            // plans = new ArrayList<MobilePlan>();
            plans = new HashMap<Integer, MobilePlan>();
            for (MobilePlan mobilePlan : user.plans.values()) {
                if (mobilePlan instanceof PersonalPlan) {
                    plans.put(mobilePlan.getId(), new PersonalPlan((PersonalPlan) mobilePlan));

                } else if (mobilePlan instanceof BusinessPlan) {
                    plans.put(mobilePlan.getId(), new BusinessPlan((BusinessPlan) mobilePlan));
                }

            }
        } catch (PlanException e) {
            System.out.println(e);
        }
    }

    public User clone() throws CloneNotSupportedException {
        User output = (User) super.clone();
        output.address = address.clone();
        // plans = new ArrayList<MobilePlan>();
        plans = new HashMap<Integer, MobilePlan>();
        for (MobilePlan mobilePlan : plans.values()) {
            output.plans.put(mobilePlan.getId(), mobilePlan.clone());
        }
        return output;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getStreet() {
        return address.getStreet();
    }

    public int getStreetNum() {
        return address.getStreetNum();
    }

    public String getSuburb() {
        return address.getStreet();
    }

    public String getCity() {
        return address.getcity();
    }
    public Address getAddress() {
        return address;
    }

    // public ArrayList<MobilePlan> getPlans() {
    // return plans;
    // }
    public void setCity(String city) {
        address.setCity(city);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String userPass) {
        this.userPassword = userPass;
    }

    public void setStreet(String street) {
        address.setStreet(street);
    }

    public void setStreetNum(int streetNum) {
        address.setStreetNum(streetNum);
    }

    public void setSuburb(String Suburb) {
        address.setSuburb(Suburb);
    }

    public boolean validateUser(int userID, String userPassword) {
        if (this.userID == userID && this.userPassword.equals(userPassword)) {
            return true;
        }
        return false;

    }

    public MobilePlan findPlan(int planID) {
        for (MobilePlan plan : plans.values()) {
            if (plan.getId() == planID) {
                return plan;
            }
        }
        return null;
    }

    public boolean addPlan(MobilePlan mobilePlans) {
        if (findPlan(mobilePlans.getId()) == null) {
            plans.put(mobilePlans.getId(), mobilePlans);
            return true;
        } else {
            return false;
        }
    }

    public void print() {
        System.out.println(" name : " + name + " userID : " + userID + " address : " + address);
    }

    public String toString() {
        String print = " name : " + name + " userID : " + userID + " address : " + address;
        for (MobilePlan plan : plans.values()) {
            print += plan.toString() + " \n ";
        }

        return print;
    }
    public String usertoString(){
        String print = " name : " + name + " userID : " + userID + " address : " + address;
               return print;
    }

    // public void printPlans(int flatRate) {
    // MobilePlan.calcTotalPayments(plans, flatRate);
    // MobilePlan.printPlans(plans);
    // }
    // public double calcTotalPayments(int flatRate) {
    // return MobilePlan.calcTotalPayments(plans, flatRate);
    // }
    // public void mobilePriceRiseAll(double risePercent) {
    // MobilePlan.mobilePriceRiseAll(plans, risePercent);
    // }
    // public ArrayList<MobilePlan> filterByMobileModel(String mobileModel) {
    // return MobilePlan.filterByMobileModel(plans, mobileModel);
    // }
    public boolean createPersonalPlan(String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit,
            MyDate expiryDate, String city) throws PlanException {
        PersonalPlan plan = new PersonalPlan(username, mobilePhone, internetQuota, capLimit, expiryDate, city);
        return addPlan(plan);
    }

    public boolean createBusinessPlan(String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit,
            MyDate expiryDate, int numberOfEmployees, int ABN) throws PlanException, UNException {
        BusinessPlan plan = new BusinessPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate,
                numberOfEmployees, ABN);
        return addPlan(plan);
    }

    // public ArrayList<MobilePlan> filterByExpiryDate(MyDate date) {
    // return MobilePlan.filterByExpiryDate(plans, date);
    // }
    // lab 4
    public static void printUser(ArrayList<User> users) {
        for (User user : users) {
            user.print();
        }
    }

//    public static ArrayList<User> shallowCopy(ArrayList<User> users) {
//        ArrayList<User> shallowCopy = new ArrayList<User>();
//        for (User user : users) {
//            shallowCopy.add(user);
//        }
//        return shallowCopy;
//    }
//    public static ArrayList<User> deepCopy(ArrayList<User> users)
//            throws CloneNotSupportedException {
//        ArrayList<User> deepCopy = new ArrayList<User>();
//        for (User user : users) {
//            deepCopy.add(user.clone());
//        }
//        return deepCopy;
//    }
    public int compareTo1(User user, int flatRate) {
        double totalPayment = calcTotalPayments(flatRate);
        if (totalPayment == 0) {
            return 0;
        }
        return totalPayment > user.calcTotalPayments(flatRate) ? 1 : -1;
    }

    public int compareTo(User otherUser) {
        return address.compareTo(otherUser.address);
    }
    // lab 5

    public static void printUser(HashMap<Integer, User> users) {
        for (User user : users.values()) {
            user.print();
        }
    }

    public HashMap<Integer, MobilePlan> getPlans() {
        return plans;
    }

    public ArrayList<MobilePlan> deepCopyPlans()
            throws CloneNotSupportedException {
        return MobilePlan.deepCopy(plans);
    }

    public ArrayList<MobilePlan> shallowCopyPlans() {
        return MobilePlan.shallowCopy(plans);
    }

    public HashMap<Integer, MobilePlan> filterByMobileModel(String mobileModel) {
        return MobilePlan.filterByMobileModel(plans, mobileModel);
    }

    public void mobilePriceRiseAll(double risePercent) {
        MobilePlan.mobilePriceRiseAll(plans, risePercent);
    }

    public double calcTotalPayments(int flatRate) {
        return MobilePlan.calcTotalPayments(plans, flatRate);
    }

    public void printPlans(int flatRate) {
        MobilePlan.calcTotalPayments(plans, flatRate);
        MobilePlan.printPlans(plans);
    }

    public HashMap<Integer, MobilePlan> shallowCopyHashMap() {
        return MobilePlan.shallowCopyHashMap(plans);
    }

    public HashMap<Integer, MobilePlan> deepCopyHashMap() throws CloneNotSupportedException {
        return MobilePlan.deepCopyHashMap(plans);
    }

    public HashMap<Integer, MobilePlan> filterByExpiryDate(MyDate date) {
        return MobilePlan.filterByExpiryDate(plans, date);
    }

    public static HashMap<Integer, User> shallowCopyHashMap(HashMap<Integer, User> users) {
        HashMap<Integer, User> shallowCopy = new HashMap<Integer, User>();
        for (User user : users.values()) {
            shallowCopy.put(user.getUserID(), user);
        }
        return shallowCopy;
    }

    public static HashMap<Integer, User> deepCopyHashMap(HashMap<Integer, User> users)
            throws CloneNotSupportedException {
        HashMap<Integer, User> deepCopy = new HashMap<Integer, User>();
        for (User user : users.values()) {
            try {
                deepCopy.put(user.getUserID(), user.clone());
            } catch (CloneNotSupportedException e) {
                System.err.println(e);
            }
        }
        return deepCopy;
    }

    public static ArrayList<User> shallowCopy(HashMap<Integer, User> users) {
        ArrayList<User> shallowCopy = new ArrayList<User>();
        for (User user : users.values()) {
            shallowCopy.add(user);
        }
        return shallowCopy;
    }

    public static ArrayList<User> deepCopy(HashMap<Integer, User> users)
            throws CloneNotSupportedException {
        ArrayList<User> deepCopy = new ArrayList<User>();
        for (User user : users.values()) {
            try {
                deepCopy.add(user.clone());
            } catch (CloneNotSupportedException e) {
                System.err.println(e);
            }

        }
        return deepCopy;
    }

    public ArrayList<MobilePlan> sortPlansByDate() {
        ArrayList<MobilePlan> sortedList = shallowCopyPlans();
        Collections.sort(sortedList);
        return sortedList;
    }

    public ArrayList<String> populateDistinctMobileModels() {
        ArrayList<String> mobileModels = new ArrayList<String>();
        for (MobilePlan mobilePlan : plans.values()) {
            boolean found = false;
            for (String model : mobileModels) {
                if (mobilePlan.getMobileModel().equalsIgnoreCase(model)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                mobileModels.add(mobilePlan.getMobileModel());
            }
        }
        return mobileModels;
    }

    public int getTotalCountForMobileModel(String mobileModel) {
        int value = 0;
        for (MobilePlan mobilePlan : plans.values()) {
            if (mobilePlan.getMobileModel().equals(mobileModel)) {
                value++;
            }
        }
        return value;
    }

    public double getTotalPaymentForMobileModel(String mobileModel, int flatRate) {
        double total = 0;
        for (MobilePlan mobilePlan : plans.values()) {
            if (mobilePlan.getMobileModel().equals(mobileModel)) {
                total += mobilePlan.calcPay(flatRate);
            }
        }
        return total;
    }

    public HashMap<String, Integer> getTotalCountPerMobileModel() {
        HashMap<String, Integer> models = new HashMap<>();
        for (MobilePlan plan : plans.values()) {
            Integer count = models.get(plan.getMobileModel());
            if (count == null) {
                models.put(plan.getMobileModel(), 1);
            } else {
                models.put(plan.getMobileModel(), count++);
            }
        }
        return models;
    }

    public HashMap<String, Double> getTotalPaymentPerMobileModel() {
        int flatRate = 2;
        HashMap<String, Double> models = new HashMap<>();
        for (MobilePlan plan : plans.values()) {
            Double val = models.get(plan.getMobileModel());
            if (val == null) {
                models.put(plan.getMobileModel(), plan.calcPay(flatRate));
            } else {
                models.put(plan.getMobileModel(), val + plan.calcPay(flatRate));
            }
        }
        return models;
    }

    ArrayList<Double> getTotalPaymentPerMobileModel(ArrayList<String> mobileModels, int flatRate) {
        ArrayList<Double> totalPayments = new ArrayList<Double>();
        for (String model : mobileModels) {
            totalPayments.add(getTotalPaymentForMobileModel(model, flatRate));
        }
        return totalPayments;
    }

    public void reportPaymentsPerMobileModel(HashMap<String, Integer> models, HashMap<String, Double> payments) {
        System.out.println(" \n MobileModel \t \t \t Total Monthly Payments \t \t \t Average Monthly Payment ");
        for (String model : models.keySet()) {
            System.out.printf(" %s: \t \t \t %,.2f$ \t \t \t %,.2f$ \n ", model, payments.get(model),
                    payments.get(model) / models.get(model));
        }
    }

    public HashMap<String, Integer> getTotalCountPerMobileModel(HashMap<String, Integer> mobileModels) {
        mobileModels = new HashMap<>(getTotalCountPerMobileModel());
        return mobileModels;
    }

    public HashMap<String, Double> getTotalPaymentsPerMobileModel(HashMap<String, Double> models) {
        models = new HashMap<>(getTotalPaymentPerMobileModel());
        return models;
    }

    // lab 6
    public static HashMap<Integer, User> load(String fileName) throws IOException {
        HashMap<Integer, User> users = new HashMap<>();
        ObjectInputStream inputStream = null;
        users.clear();
        try {
            inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println(" Error : can't create/open this file ! ");
        }
        try {
            while (true) {
                User user = (User) inputStream.readObject();
                users.put(user.getUserID(), user);
            }
        } catch (EOFException ex) {
            System.out.println(" No more plans ! ");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error : can't find class in the file ! ");
        } catch (IOException ex) {
            System.err.println(" Error : can't add object to this file ! ");
        }
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException ex) {
            System.err.println(" Error : can't close this file ! ");
        }
        return users;
    }

    public static Boolean save(HashMap<Integer, User> users, String fileName) throws IOException {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println(" Error : can't create/open this file ! ");
            System.exit(1);
        }
        try {
            for (User user : users.values()) {
                outputStream.writeObject(user);
            }
        } catch (IOException ex) {
            System.err.println(" Error : add the objects to this file ! ");
            System.exit(1);
        }
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException ex) {
            System.err.println(" Error : can't close this file ! ");
            System.exit(1);
        }
        return true;
    }

    public String toDelimitedString() {
        String output = name + "," + userID + "," + address.toDelimitedString() + "," + userPassword + ","
                + plans.size();
        for (MobilePlan mobilePlan : plans.values()) {
            output += "," + mobilePlan.toDelimitedString();
        }
        return output;
    }

    public void addPersonalPlan(PersonalPlan personalPlan) {
        plans.put(personalPlan.getId(), personalPlan);
    }

    public void addBusinessPlan(BusinessPlan businessPlan) {
        plans.put(businessPlan.getId(), businessPlan);
    }

    public static HashMap<Integer, User> loadTextFile(String fileName) throws PlanException, IOException, UNException {
        HashMap<Integer, User> users = new HashMap<>();
        users.clear();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            System.err.println(" Error : can't create/open this file ! ");
        }
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e) {
            System.err.println(" Error : can't add the objects to this file ! ");
        }
        try {
            while (line != null) {
                line = line.trim();
                String[] field = line.split(",");
                String userName = field[0];
                int userID = Integer.parseInt(field[1]);
                int streetNum = Integer.parseInt(field[2]);
                String street = field[3];
                String suburb = field[4];
                String city = field[5];
                String password = field[6];
                int numOfPlans = Integer.parseInt(field[7]);
                User user = new User(userName, userID, new Address(streetNum, street, suburb, city), password);
                int j = 8;
                for (int i = 0; i < numOfPlans; i++) {
                    switch (field[j++]) {
                        case "pP":
                            int id1 = Integer.parseInt(field[j++]);
                            String userName1 = field[j++];
                            String mobileModel1 = field[j++];
                            String mobileType1 = field[j++];
                            MobileType enumType1 = null;
                            if (mobileType1.equalsIgnoreCase("ANDROID")) {
                                enumType1 = MobileType.Android;
                            } else if (mobileType1.equalsIgnoreCase("IOS")) {
                                enumType1 = MobileType.Ios;
                            } else if (mobileType1.equalsIgnoreCase("WINDOWS")) {
                                enumType1 = MobileType.Windows;
                            }
                            int memorySize1 = Integer.parseInt(field[j++]);
                            double price1 = Double.parseDouble(field[j++]);
                            int internetQuota1 = Integer.parseInt(field[j++]);
                            int capLimit1 = Integer.parseInt(field[j++]);
                            int year1 = Integer.parseInt(field[j++]);
                            int month1 = Integer.parseInt(field[j++]);
                            int day1 = Integer.parseInt(field[j++]);
                            String city1 = field[j++];
                            PersonalPlan personalPlan = new PersonalPlan(userName1, id1,
                                    new MobilePhone(mobileModel1, enumType1, memorySize1, price1), internetQuota1,
                                    capLimit1, new MyDate(year1, month1, day1), city1);
                            user.addPlan(personalPlan);
                            break;
                        case "bP":
                            int id2 = Integer.parseInt(field[j++]);
                            String userName2 = field[j++];
                            String mobileModel2 = field[j++];
                            String mobileType2 = field[j++];
                            MobileType enumType2 = null;
                            if (mobileType2.equalsIgnoreCase("ANDROID")) {
                                enumType2 = MobileType.Android;
                            } else if (mobileType2.equalsIgnoreCase("IOS")) {
                                enumType2 = MobileType.Ios;
                            } else if (mobileType2.equalsIgnoreCase("WINDOWS")) {
                                enumType2 = MobileType.Windows;
                            }
                            int memorySize2 = Integer.parseInt(field[j++]);
                            double price2 = Double.parseDouble(field[j++]);
                            int internetQuota2 = Integer.parseInt(field[j++]);
                            int capLimit2 = Integer.parseInt(field[j++]);
                            int year2 = Integer.parseInt(field[j++]);
                            int month2 = Integer.parseInt(field[j++]);
                            int day2 = Integer.parseInt(field[j++]);
                            int numberOfEmployees = Integer.parseInt(field[j++]);
                            int ABN = Integer.parseInt(field[j++]);
                            BusinessPlan businessPlan = new BusinessPlan(userName2, id2,
                                    new MobilePhone(mobileModel2, enumType2, memorySize2, price2), internetQuota2,
                                    capLimit2, new MyDate(year2, month2, day2), numberOfEmployees, ABN);
                            user.addPlan(businessPlan);
                            break;
                    }
                }
                users.put(user.getUserID(), user);
                line = in.readLine();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        try {
            in.close();
        } catch (IOException e) {
            System.out.println(" Error in closing the file ! ");
        }
        return users;
    }

    public static Boolean saveTextFile(HashMap<Integer, User> users, String fileName) throws IOException {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(fileName));
            for (User user : users.values()) {
                out.write(user.toDelimitedString() + "\n");
            }
            out.close();
        } catch (IOException e) {
            System.err.println(" Error : can't close this file ! ");
            return false;
        }
        return true;
    }

    //lab8
    public static ArrayList<User> shallowCopy(ArrayList<User> users) {
        return (ArrayList<User>) (users.stream().collect(Collectors.toList()));
    }

    public static ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException {
        return (ArrayList<User>) (users.stream().map(x -> x.cloneException(x)).collect(Collectors.toList()));

    }

    private User cloneException(User user) {
        try {
            return user.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void printPlans(int flatRate, int userID, String userPassword) {
        if (validateUser(userID, userPassword)) {
            plans.values().forEach(x -> String.format("% Total: %,.2f$ \n", x.toString(), x.calcPay(flatRate)));
        }
    }
}
