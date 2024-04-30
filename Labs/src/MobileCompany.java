
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class MobileCompany implements Cloneable, Serializable {

    private String name;
    // private ArrayList<User> usersArrayList;
    private HashMap<Integer, User> users;
    private String adminUsername;
    private String adminPassword;
    private int flatRate;

    public MobileCompany(String name, String adminUsername, String adminPassword, int flatRate) {
        this.name = name;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
        // users = new ArrayList<User>();
        users = new HashMap<Integer, User>();

    }

    public MobileCompany(MobileCompany company) {
        name = company.name;
        adminPassword = company.adminPassword;
        adminUsername = company.adminUsername;
        flatRate = company.flatRate;
        // users = new ArrayList<User>();
        users = new HashMap<Integer, User>();
        for (User user : company.users.values()) {
            users.put(user.getUserID(), new User(user));
        }
    }

    public MobileCompany clone() throws CloneNotSupportedException {
        MobileCompany company = (MobileCompany) super.clone();
        // company.users = new ArrayList<User>();
        company.users = new HashMap<Integer, User>();
        for (User user : users.values()) {
            company.users.put(user.getUserID(), user.clone());
        }
        return company;
    }

    public String getName() {
        return name;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public int getFlatRate() {
        return flatRate;
    }

    public boolean validateAdmin(String username, String password) {
        return adminUsername.equals(username) && (adminPassword.equals(password));

    }

    public User validateUser(int userID, String password) {
        User user = findUser(userID);

        if (user != null && user.validateUser(userID, password)) {
            return user;
        } else {
            return null;
        }
    }

    public boolean addUser(User user) {
        if ((findUser(user.getUserID())) == null) {
            // return users.add(user);
            // return false;
            users.put(user.getUserID(), user);
            return true;
        } else {
            return false;
        }

    }

    public User findUser(int userID) {
        for (User user : users.values()) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    public boolean addUser(String name, int userID, Address address, String password) {
        User user = new User(name, userID, address, password);
        return addUser(user);

    }

    public boolean addPlan(int userID, MobilePlan plan) {
        User user = findUser(userID);
        if (user != null) {
            return user.addPlan(plan);
        }

        return false;

    }

    public MobilePlan findPlan(int userID, int planID) {
        User user = findUser(userID);
        if (user != null) {
            return user.findPlan(planID);
        } else {
            return null;
        }
    }

    public void printPlans(int userID) {
        User user = findUser(userID);
        if (user != null) {
            // user.print();
            user.printPlans(flatRate);
        }

    }

    public void print() {
        // for (User user : users) {
        for (User user : users.values()) {
            user.print();
            user.printPlans(flatRate);
        }

    }

    public String toString() {
        String outPut = " name: " + name + " flat rate: " + flatRate;
        // for (User user : users) {
        for (User user : users.values()) {
            outPut += user.toString();
        }
        return outPut;
    }

    public boolean createPersonalPlan(int userID, String username, int id, MobilePhone mobilePhone, int internetQuota,
            int capLimit, MyDate expiryDate, String city) throws PlanException {
        User user = findUser(userID);
        if (user != null) {
            return user.createPersonalPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate, city);
        }

        return false;
    }

    public boolean createBusinessPlan(int userID, String username, int id, MobilePhone mobilePhone, int internetQuota,
            int capLimit, MyDate expiryDate, int numberOfEmployees, int ABN) throws PlanException, UNException {
        User user = findUser(userID);
        if (user != null) {
            return user.createBusinessPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate,
                    numberOfEmployees, ABN);
        }

        return false;

    }

    public double calcTotalPayments(int userID) {
        User findUser = findUser(userID);
        return findUser.calcTotalPayments(flatRate);
    }

    public double calcTotalPayments() {
        double total = 0;
        // for (User user : users) {
        for (User user : users.values()) {
            total += user.calcTotalPayments(flatRate);
        }
        return total;
    }

    public boolean mobilePriceRise(int userID, double risePercent) {
        User user = findUser(userID);
        if (user != null) {
            users.get(userID).mobilePriceRiseAll(risePercent);
            return true;
        }
        return false;
    }

    public void mobilePriceRise(double risePercent) {
        // for (User user : users) {
        for (User user : users.values()) {
            user.mobilePriceRiseAll(risePercent);
        }
    }

    public ArrayList<MobilePlan> allPlan() {
        ArrayList<MobilePlan> allPlans = new ArrayList<MobilePlan>();
        for (User user : users.values()) {
            for (MobilePlan mobilePlan : user.getPlans().values()) {
                allPlans.add(mobilePlan);
            }
        }
        return allPlans;
    }
    // public ArrayList<MobilePlan> filterByMobileModel(int userID, String
    // mobileModel) {
    // User user = findUser(userID);
    // if (user != null) {
    // return user.filterByMobileModel(mobileModel);
    // }
    // return null;
    // }
    // public ArrayList<Integer,MobilePlan> filterByExpiryDate(int userID, MyDate
    // date) {
    // User user = findUser(userID);
    // if (user != null) {
    // return user.filterByExpiryDate(date);
    // }
    // return null;
    // }
    // public ArrayList<MobilePlan> filterByMobileModel(String mobileModel) {
    // ArrayList<MobilePlan> mobileModels = new ArrayList<MobilePlan>();
    // for (User user : users) {
    // for (MobilePlan mobilePlan : user.filterByMobileModel(mobileModel)) {
    // mobileModels.add(mobilePlan);
    // }
    // }
    // return mobileModels;
    // }
    // public ArrayList<MobilePlan> filterByExpiryDate(MyDate date) {
    // ArrayList<MobilePlan> expiryDate = new ArrayList<MobilePlan>();
    // for (User user : users) {
    // for (MobilePlan mobilePlan : filterByExpiryDate(user.getUserID(), date)) {
    // expiryDate.add(mobilePlan);
    // }
    // }
    // return expiryDate;
    // }
    // public ArrayList<String> populateDistinctCityNames() {
    // ArrayList<String> populateCityNames = new ArrayList<>();
    // for (User user : users) {
    // boolean found = false;
    // for (String city : populateCityNames) {
    // if (user.getCity().equals(city)) {
    // found = true;
    // break;
    // } else if (!found) {
    // populateCityNames.add(user.getCity());
    // }
    // }
    // }
    // return populateCityNames;
    // }
    // public double getTotalPaymentForCity(String city) {
    // double calcTotalPayment = 0;
    // for (User user : users.values()) {
    // if (user.getCity().equalsIgnoreCase(city)) {
    // calcTotalPayment += user.calcTotalPayments(flatRate);
    // }
    // }
    // return calcTotalPayment;
    // }
    // public ArrayList<Double> getTotalPaymentPerCity(ArrayList<String> cities) {
    // ArrayList<Double> perCities = new ArrayList<>();
    // for (String city : cities) {
    // perCities.add(getTotalPaymentForCity(city));
    // }
    // return perCities;
    // }
    // public void reportPaymentperCity(ArrayList<String> cities, ArrayList<Double>
    // payments) {
    // System.out.println(" city total Balance ");
    // for (int i = 0; i < cities.size(); i++) {
    // System.out.println(" \n " + cities.get(i) + " " + payments.get(i));
    // }
    // }
    // public ArrayList<Integer, User> shallowCopy() {
    // return User.shallowCopy(users);
    // }
    // public ArrayList< User> deepCopy()
    // throws CloneNotSupportedException {
    // return User.deepCopy(users);
    // }
    // public ArrayList<User> getUser() {
    // return users;
    // }
    // public ArrayList<User> sortUser() {
    // ArrayList<User> sortList = new ArrayList<>();
    // for (User user : users) {
    // sortList.add(user);
    // }
    // Collections.sort(sortList);
    // return sortList;
    // }
    // public int compareTo(MobileCompany otherCompany) {
    // return mobilePlan.compareTo(otherCompany.);
    // }

    // lab 5
    public HashMap<Integer, MobilePlan> allPlans() {
        HashMap<Integer, MobilePlan> allPlans = new HashMap<>();
        for (User user : users.values()) {
            for (MobilePlan mobilePlan : user.getPlans().values()) {
                allPlans.put(mobilePlan.getId(), mobilePlan);
            }
        }
        return allPlans;
    }

    public HashMap<Integer, MobilePlan> filterByMobileModel(int userID, String mobileModel) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByMobileModel(mobileModel);
        }
        return new HashMap<Integer, MobilePlan>();
    }

    public HashMap<Integer, MobilePlan> filterByExpiryDate(int userID, MyDate date) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByExpiryDate(date);
        }
        return new HashMap<Integer, MobilePlan>();
    }

    // public HashMap<Integer, MobilePlan> filterByMobileModel(String mobileModel) {
    // HashMap<Integer, MobilePlan> filterLists = new HashMap<>();
    // for (User user : users.values()) {
    // HashMap<Integer, MobilePlan> userFilterLists =
    // filterByMobileModel(user.getUserID(), mobileModel);
    // for (MobilePlan mobilePlan : userFilterLists.values()) {
    // filterLists.put(mobilePlan.getId(), mobilePlan);
    // }
    // }
    // return filterLists;
    // }
    public HashMap<Integer, MobilePlan> filterByExpiryDate(MyDate date) {
        HashMap<Integer, MobilePlan> filterLists = new HashMap<Integer, MobilePlan>();
        for (User user : users.values()) {
            HashMap<Integer, MobilePlan> userFilterLists = filterByExpiryDate(user.getUserID(), date);
            for (MobilePlan mobilePlan : userFilterLists.values()) {
                filterLists.put(user.getUserID(), mobilePlan);
            }
        }
        return filterLists;
    }

    public ArrayList<User> deepCopyUsers()
            throws CloneNotSupportedException {
        return User.deepCopy(users);
    }

    public ArrayList<User> shallowCopyUsers() {
        return User.shallowCopy(users);
    }

    public HashMap<Integer, User> deepCopyUsersHashMap()
            throws CloneNotSupportedException {
        return User.deepCopyHashMap(users);
    }

    public HashMap<Integer, User> shallowCopyUsersHashMap() {
        return User.shallowCopyHashMap(users);
    }

    public HashMap<Integer, User> getUser() {
        return users;
    }

    public ArrayList<User> sortUser() {
        ArrayList<User> sortedList = shallowCopyUsers();
        Collections.sort(sortedList);
        return sortedList;
    }

    public HashMap<String, Integer> getTotalCountPerMobileModel() {
        HashMap<String, Integer> models = new HashMap<>();
        for (User user : users.values()) {
            HashMap<String, Integer> count = user.getTotalCountPerMobileModel();
            for (String model : count.keySet()) {
                Integer counts = models.get(model);
                if (counts == null) {
                    models.put(model, count.get(model));
                } else {
                    models.put(model, models.get(model) + count.get(model));
                }
            }
        }
        return models;
    }

    public HashMap<String, Double> getTotalPaymentPerMobileModel() {
        HashMap<String, Double> models = new HashMap<>();
        for (User user : users.values()) {
            HashMap<String, Double> count = user.getTotalPaymentPerMobileModel();
            for (String model : count.keySet()) {
                Double counts = models.get(model);
                if (counts == null) {
                    models.put(model, count.get(model));
                } else {
                    models.put(model, counts + count.get(model));
                }
            }
        }
        return models;
    }

    public void reportPaymentsPerMobileModel(HashMap<String, Integer> models, HashMap<String, Double> payments) {
        for (String model : models.keySet()) {
            System.out.printf(" %s: %d %,.2f$\n ", model, models.get(model), payments.get(model));
        }
    }

    public HashMap<String, Double> getTotalPaymentPerCity() {
        HashMap<String, Double> payments = new HashMap<>();
        for (User user : users.values()) {
            Double val = payments.get(user.getCity());
            if (val == null) {
                payments.put(user.getCity(), user.calcTotalPayments(flatRate));
            } else {
                payments.put(user.getCity(), val + user.calcTotalPayments(flatRate));
            }
        }
        return payments;
    }

    public double getTotalPaymentForCity(String city) {
        double totalPaymentForCity = 0;
        for (User user : users.values()) {
            if (user.getCity().equals(city)) {
                totalPaymentForCity += user.calcTotalPayments(flatRate);
            }
        }
        return totalPaymentForCity;
    }

    public void reportPaymentPerCity(HashMap<String, Double> payments) {
        for (String city : payments.keySet()) {
            System.out.printf(" %s: %,.2f$\n ", city, payments.get(city));

        }
    }

     public ArrayList<String> populateDistinctCityNames() {
        ArrayList<String> cities = new ArrayList<String>();
        for (User user : users.values()) {
            boolean found = false;
            for (String city : cities) {
                if (user.getCity().equals(city)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                cities.add(user.getCity());
            }
        }
        return cities;
    }

    public ArrayList<String> populateDistinctMobileModels() {
        ArrayList<String> allModels = new ArrayList<>();
        for (User user : users.values()) {
            ArrayList<String> userModels = user.populateDistinctMobileModels();
            for (String userModel : userModels) {
                boolean found = false;
                for (String model : allModels) {
                    if (model.equals(userModel)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    allModels.add(userModel);
                }
            }
        }
        return allModels;
    }

    // lab 6
    public Boolean load(String fileName) {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println(" Error : can't create/open this file ! ");
            return false;
        }
        try {
            while (true) {
                MobileCompany mobileCompany = (MobileCompany) inputStream.readObject();
                this.name = mobileCompany.getName();
                this.adminUsername = mobileCompany.getAdminUsername();
                this.adminPassword = mobileCompany.getAdminPassword();
                this.flatRate = mobileCompany.getFlatRate();
            }
        } catch (EOFException ex) {
            System.out.println(" No more record ! ");
        } catch (ClassNotFoundException ex) {
            System.err.println(" Error : can't find class in this file ! ");
        } catch (IOException ex) {
            System.err.println(" Error : can't add object to this file ! ");
            return false;
        }
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException ex) {
            System.err.println(" Error : can't close this file ! ");
            return false;
        }
        return true;
    }

    public Boolean save(String fileName) {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println(" Error : can't create/open this file ! ");
            return false;
        }
        try {
            outputStream.writeObject(this);
        } catch (IOException ex) {
            System.err.println(" Error : can't add the objects to this file ! ");
            return false;
        }
        try {
            if (outputStream != null) {
                outputStream.close();
            }
            return true;
        } catch (IOException ex) {
            System.err.println("Error : can't close this file ! ");
            return false;
        }
    }

    public String toDelimitedString() {
        String outPut = name + "," + adminUsername + "," + adminPassword + "," + flatRate + "," + users.size();
        for (User user : users.values()) {
            outPut += "," + user.toDelimitedString() + "\n";
        }
        return outPut;
    }

    public Boolean loadTextFile(String fileName) throws PlanException, IOException, UNException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            System.err.println(" Error : can't create/open this file ! ");
            return false;
        }
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e) {
            System.err.println(" Error : can't add the objects to this file ! ");
            return false;
        }
        try {
            while (line != null) {
                line = line.trim();
                String[] field = line.split(",");
                this.name = field[0];
                this.adminUsername = field[1];
                this.adminPassword = field[2];
                this.flatRate = Integer.parseInt(field[3]);
                int numOfUsers = Integer.parseInt(field[4]);
                users = new HashMap<Integer, User>();
                int j = 5;
                for (int i = 0; i < numOfUsers; i++) {
                    String name = field[j++];
                    int userID = Integer.parseInt(field[j++]);
                    int streetNum = Integer.parseInt(field[j++]);
                    String street = field[j++];
                    String suburb = field[j++];
                    String city = field[j++];
                    String password = field[j++];
                    int numOfPlans = Integer.parseInt(field[j++]);
                    User user = new User(name, userID, new Address(streetNum, street, suburb, city), password);
                    for (i = 0; i < numOfPlans; i++) {
                        switch (field[j++]) {
                            case "pP":
                                String userName1 = field[j++];
                                int id1 = Integer.parseInt(field[j++]);
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
                                String userName2 = field[j++];
                                int id2 = Integer.parseInt(field[j++]);
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
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        try {
            in.close();
        } catch (IOException e) {
            System.out.println(" Error : can't close this file ! ");
            return false;
        }
        return true;
    }

    public Boolean saveTextFile(String fileName) throws IOException {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(fileName));
            out.write(this.toDelimitedString() + " \n ");
            out.close();
        } catch (IOException e) {
            System.err.println(" Error : can't close this file ! ");
            return false;
        }
        return true;

    }

    // assignment 2
    public class monthlyComparator implements Comparator<User> {

        public int compare(User user1, User user2) {
            if (user1.calcTotalPayments(flatRate) == user2.calcTotalPayments(flatRate)) {
                return 0;
            }
            return user1.calcTotalPayments(flatRate) < user2
                    .calcTotalPayments(flatRate) ? 1 : -1;
        }
    }

    public HashMap<String, ArrayList<User>> getUsersPerCity() {
        HashMap<String, ArrayList<User>> usersCity = new HashMap<>();

        for (User user : users.values()) {
            ArrayList<User> usersList = new ArrayList<>();
            String cities = user.getCity();
            if (cities == null) {
                usersList.add(user);
                usersCity.put(cities, usersList);
            } else {
                usersList.add(user);
                usersCity.put(cities, usersList);
            }
        }
        return usersCity;
    }

    public void reportUserPerCity(HashMap<String, ArrayList<User>> cityUsers) {
        for (String city : cityUsers.keySet()) {
            String report = "";
            ArrayList<User> users = cityUsers.get(city);
            for (User user : users) {
                report += user.getName();
                System.out.format(" This city " + city + " Has this users " + report + " \n ");
            }

        }
    }

    public ArrayList<User> sortUsersByMonthlyPayment() {
        ArrayList<User> sort = shallowCopyUsers();
        Collections.sort(sort, new monthlyComparator());
        return sort;
    }

    HashMap<String, ArrayList<MobilePlan>> filterPlansByExpiryDate(MyDate expiryDate) {
        HashMap<String, ArrayList<MobilePlan>> filterPlan = new HashMap<>();
        for (User user : users.values()) {
            HashMap<Integer, MobilePlan> planList = user.filterByExpiryDate(expiryDate);
            ArrayList<MobilePlan> plans = new ArrayList<>();
            for (MobilePlan plan : planList.values()) {
                plans.add(plan);
            }
            filterPlan.put(user.getName(), plans);
        }
        return filterPlan;
    }

    public void reportFilterPlansByExpiryDate(HashMap<String, ArrayList<MobilePlan>> filterPlan) {
        for (String username : filterPlan.keySet()) {
            String report = "";
            ArrayList<MobilePlan> Plans = filterPlan.get(username);
            for (MobilePlan plan : Plans) {
                report += plan.toString() + " \n ";
            }
            System.out.println(" This user " + username + " has these expired plans " + report);
        }
    }

    // Lab 8
    public Double calcPay(String adminUsername, String adminPassword) {
        if (validateAdmin(adminUsername, adminPassword)) {
            users.values().stream().map(x -> x.calcTotalPayments(flatRate))
                    .reduce(0.0, (x, y) -> x + y);
        }
        return 0.0;
    }

    public ArrayList<MobilePlan> filterByMobileModel(String mobileModel) {
        return (ArrayList<MobilePlan>) (allPlan().stream()
                .filter(x -> x.getMobileModel().contains(mobileModel)).collect(Collectors.toList()));
    }

    public ArrayList<MobilePlan> filterByExpairyDate(MyDate date) {
        return (ArrayList<MobilePlan>) (allPlan().stream()
                .filter(x -> x.getExpiryDate() == (date)).collect(Collectors.toList()));
    }

    public void mobilePriceRise(String adminUsername, String adminPassword, double risePercent) {
        if (validateAdmin(adminUsername, adminPassword)) {
            users.values().stream().forEach(x -> x.mobilePriceRiseAll(risePercent));
        }
    }

    public HashMap<String, Double> getTotalPaymentPerCity(String adminUsername, String adminPassword) {
        if (validateAdmin(adminUsername, adminPassword)) {
            users.values().stream().collect(Collectors.groupingBy(x -> x.getCity(),
                    Collectors.summingDouble(x -> x.calcTotalPayments(flatRate))));
        }
        return null;
    }

}
