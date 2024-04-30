
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.sound.sampled.Line;

public abstract class MobilePlan implements Cloneable, Comparable<MobilePlan>, Serializable {

    private int lastID;
    protected String userName;
    private int id;
    protected MobilePhone handSet;
    protected int internetQuota;
    protected int capLimit;
    protected MyDate expiryDate;

    public MobilePlan(String userName, MobilePhone handSet, int internetQuota, int capLimit,
            MyDate expiryDate) throws PlanException {
        // this.userName = userName;
        // if (3000000 > id || id > 4000000) {
        // id = generateId();
        // throw new PlanException();
        // }
        this.id = generateId();
        this.handSet = handSet;
        this.internetQuota = internetQuota;
        this.capLimit = capLimit;
        this.expiryDate = expiryDate;
    }

    public MobilePlan(String userName, int id, MobilePhone handSet, int internetQuota, int capLimit,
            MyDate expiryDate) throws PlanException, UNException {
        Pattern namePattern = Pattern.compile("^USR\\d{6}U$");
        Matcher nameMatcher = namePattern.matcher(userName);
        boolean matcher = nameMatcher.find();
        if (matcher != true) {
            userName = usernameGenerator();
            throw new UNException(userName);
        }
        this.userName = userName;
        this.id = id;
        this.handSet = handSet;
        this.internetQuota = internetQuota;
        this.capLimit = capLimit;
        this.expiryDate = expiryDate;
    }

    public MobilePlan(MobilePlan mobilePlan) {
        userName = mobilePlan.userName;
        // id = mobilePlan.id;
        handSet = new MobilePhone(mobilePlan.handSet);
        internetQuota = mobilePlan.internetQuota;
        capLimit = mobilePlan.capLimit;
        expiryDate = new MyDate(mobilePlan.expiryDate);
    }

    public MobilePlan clone() throws CloneNotSupportedException {
        MobilePlan plans = (MobilePlan) super.clone();
        plans.handSet = handSet.clone();
        plans.expiryDate = expiryDate.clone();
        return plans;
    }

    public void print() {
        System.out.println(" username : " + userName + " Id : " + id);
        handSet.print();
        System.out.println(
                " internetQuota : " + internetQuota + " caplimit : " + capLimit);
        expiryDate.print();
    }

    public String toString() {
        return " username : " + userName + " Id : " + id + " internetQuota : " + internetQuota + " cap limit : "
                + capLimit + " expiryDate : " + expiryDate;
    }

    public abstract double calcPay(int flatRate);

    // lab 2
    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }

    public String getMobileModel() {
        return handSet.getModel();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setInternetQuota(int internetQuota) {
        this.internetQuota = internetQuota;
    }

    public void setMobilePhone(MobilePhone handSet) {
        this.handSet = handSet;
    }

    public void setMobileModel(String model) {
        this.handSet.setModel(model);
    }

    public void setCapLimit(int capLimit) {
        this.capLimit = capLimit;
    }

    public MyDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(MyDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getHandSetPrice() {
        return handSet.getPrice();
    }

    public String getHandSetModel() {
        return handSet.getModel();
    }

    public static void printPlans(ArrayList<MobilePlan> plans) {
        for (MobilePlan plan : plans) {
            plan.print();
        }
    }

    // public static double calcTotalPayments(ArrayList<MobilePlan> plans, int
    // flatRate) {
    // double total = 0;
    // for (MobilePlan plan : plans) {
    // total += plan.calcPay(flatRate);
    // }
    // return total;
    // }
    public void mobilePriceRise(double risePercent) {
        handSet.priceRise(risePercent);
    }

    // public static void mobilePriceRiseAll(ArrayList<MobilePlan> plans, double
    // risePercent) {
    // for (MobilePlan mobilePlan : plans) {
    // mobilePlan.mobilePriceRise(risePercent);
    // }
    // }
    // public static ArrayList<MobilePlan> filterByMobileModel(ArrayList<MobilePlan>
    // plans, String mobileModel) {
    // ArrayList<MobilePlan> filteredMobilePlans = new ArrayList<MobilePlan>();
    // for (MobilePlan mobilePlan : plans)
    // if (mobilePlan.handSet.getModel().contains(mobileModel)) {
    // filteredMobilePlans.add(mobilePlan);
    // }
    // return filteredMobilePlans;
    // }
    // lab 3
    public static ArrayList<MobilePlan> filterByExpiryDate(ArrayList<MobilePlan> plans, MyDate date) {
        ArrayList<MobilePlan> filteredExpiryDate = new ArrayList<MobilePlan>();
        for (MobilePlan mobilePlan : plans) {
            if (mobilePlan.expiryDate.isExpired(date) == true) {
                filteredExpiryDate.add(mobilePlan);
            }
        }
        return filteredExpiryDate;
    }

    // lab 4
    public static ArrayList<MobilePlan> shallowCopy(ArrayList<MobilePlan> plans) {
        ArrayList<MobilePlan> shallowCopy = new ArrayList<MobilePlan>();
        for (MobilePlan plan : plans) {
            shallowCopy.add(plan);
        }
        return shallowCopy;
    }

    public static ArrayList<MobilePlan> deepCopy(ArrayList<MobilePlan> plans)
            throws CloneNotSupportedException {
        ArrayList<MobilePlan> deepCopy = new ArrayList<MobilePlan>();
        for (MobilePlan plan : plans) {
            try {
                deepCopy.add(plan.clone());
            } catch (CloneNotSupportedException e) {
                System.err.println(e);
            }

        }
        return deepCopy;
    }

//    public int compareTo(MobilePlan otherMobilePlans) {
//        return expiryDate.compareTo(otherMobilePlans.expiryDate);
//    }
    public int compareTo(MobilePlan otherUserName) {
        return userName.compareTo(otherUserName.userName);
    }

    public int generateId() {
        lastID = (int) ((Math.random() * ((4000000 - 3000000) + 1)) + 3000000);
        return lastID;
    }

    // lab 5
    public static void printPlans(HashMap<Integer, MobilePlan> plans) {
        for (MobilePlan plan : plans.values()) {
            plan.print();
        }
    }

    public static void mobilePriceRiseAll(HashMap<Integer, MobilePlan> plans, double risePercent) {
        for (MobilePlan mobilePlan : plans.values()) {
            mobilePlan.mobilePriceRise(risePercent);
        }
    }

    public static double calcTotalPayments(HashMap<Integer, MobilePlan> plans, int flatRate) {
        double total = 0;
        for (MobilePlan plan : plans.values()) {
            total += plan.calcPay(flatRate);
        }
        return total;
    }

    public static HashMap<Integer, MobilePlan> filterByMobileModel(HashMap<Integer, MobilePlan> plans,
            String mobileModel) {
        HashMap<Integer, MobilePlan> filteredMobilePlans = new HashMap<Integer, MobilePlan>();
        for (MobilePlan mobilePlan : plans.values()) {
            if (mobilePlan.handSet.getModel().contains(mobileModel)) {
                filteredMobilePlans.put(mobilePlan.getId(), mobilePlan);
            }
        }
        return filteredMobilePlans;
    }

    public static HashMap<Integer, MobilePlan> filterByExpiryDate(HashMap<Integer, MobilePlan> plans, MyDate date) {
        HashMap<Integer, MobilePlan> filteredExpiryDate = new HashMap<Integer, MobilePlan>();
        for (MobilePlan mobilePlan : plans.values()) {
            if (mobilePlan.expiryDate.isExpired(date) == true) {
                filteredExpiryDate.put(mobilePlan.getId(), mobilePlan);
            }
        }
        return filteredExpiryDate;
    }

    public static HashMap<Integer, MobilePlan> shallowCopyHashMap(HashMap<Integer, MobilePlan> plans) {
        HashMap<Integer, MobilePlan> shallowCopy = new HashMap<Integer, MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            shallowCopy.put(plan.getId(), plan);
        }
        return shallowCopy;
    }

    public static HashMap<Integer, MobilePlan> deepCopyHashMap(HashMap<Integer, MobilePlan> plans)
            throws CloneNotSupportedException {
        HashMap<Integer, MobilePlan> deepCopy = new HashMap<Integer, MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            try {
                deepCopy.put(plan.getId(), plan.clone());
            } catch (CloneNotSupportedException e) {
                System.err.println(e);
            }

        }
        return deepCopy;
    }

    public static ArrayList<MobilePlan> shallowCopy(HashMap<Integer, MobilePlan> plans) {
        ArrayList<MobilePlan> shallowCopy = new ArrayList<MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            shallowCopy.add(plan);
        }
        return shallowCopy;
    }

    public static ArrayList<MobilePlan> deepCopy(HashMap<Integer, MobilePlan> plans)
            throws CloneNotSupportedException {
        ArrayList<MobilePlan> deepCopy = new ArrayList<MobilePlan>();
        for (MobilePlan plan : plans.values()) {
            try {
                deepCopy.add(plan.clone());
            } catch (CloneNotSupportedException e) {
                System.err.println(e);
            }

        }
        return deepCopy;
    }

    // public static HashMap<Integer, MobilePlan>
    // shallowCopyByHashMap(ArrayList<MobilePlan> plans) {
    // HashMap<Integer, MobilePlan> shallowCopy = new HashMap<Integer,
    // MobilePlan>();
    // for (MobilePlan plan : plans) {
    // shallowCopy.put(plan.getId(), plan);
    // }
    // return shallowCopy;
    // }
    // public static HashMap<Integer, MobilePlan>
    // deepCopyByHashMap(ArrayList<MobilePlan> plans)
    // throws CloneNotSupportedException {
    // HashMap<Integer, MobilePlan> deepCopy = new HashMap<Integer, MobilePlan>();
    // for (MobilePlan plan : plans) {
    // try {
    // deepCopy.put(plan.getId(), plan.clone());
    // } catch (CloneNotSupportedException e) {
    // System.err.println(e);
    // }
    // }
    // return deepCopy;
    // }
    // lab 6 ----------------------------
    public static HashMap<Integer, MobilePlan> load(String fileName) throws IOException {
        HashMap<Integer, MobilePlan> plans = new HashMap<>();
        ObjectInputStream inputStream = null;
        plans.clear();
        try {
            inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println(" Error : can't create/open this file ! ");

        }
        try {
            while (true) {
                MobilePlan mobilePlan = (MobilePlan) inputStream.readObject();
                plans.put(mobilePlan.getId(), mobilePlan);
            }
        } catch (EOFException ex) {
            System.out.println(" Error : No more plans ! ");
        } catch (ClassNotFoundException ex) {
            System.err.println(" Error : this class is not found !");
        } catch (IOException ex) {
            System.err.println(" Error : can't add object to this file ! ");

        }
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException ex) {
            System.err.println(" Error : can't close the file ! ");

        }
        return plans;
    }

    public static Boolean save(HashMap<Integer, MobilePlan> plans, String fileName) throws IOException {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println(" Error : can't create/open this file ! ");

            return false;
        }
        try {
            for (MobilePlan mobilePlan : plans.values()) {
                outputStream.writeObject(mobilePlan);
            }
        } catch (IOException ex) {
            System.err.println(" Error : can't add the objects to this file ! ");

            return false;
        }
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException ex) {
            System.err.println(" Error : can't close this file ! ");

            return false;
        }
        return true;
    }

    public String toDelimitedString() {
        return userName + "," + id + "," + handSet.toDelimitedString() + "," + internetQuota + "," + capLimit + ","
                + expiryDate.toDelimitedString();
    }

    public static HashMap<Integer, MobilePlan> loadTextFile(String fileName)
            throws PlanException, IOException, UNException {
        HashMap<Integer, MobilePlan> plans = new HashMap<>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            System.err.println(" Error : can't create/open this file ! ");

        }
        String line = in.readLine();
        while (line != null) {
            line = line.trim();
            String[] field = line.split(",");
            String userName = field[1];
            int id = Integer.parseInt(field[2]);
            String mobileModel = field[3];
            String mobileType = field[4];
            MobileType enumType = null;
            if (mobileType.equalsIgnoreCase("ANDROID")) {
                enumType = MobileType.Android;
            } else if (mobileType.equalsIgnoreCase("IOS")) {
                enumType = MobileType.Ios;
            } else if (mobileType.equalsIgnoreCase("WINDOWS")) {
                enumType = MobileType.Windows;
            }
            int memorySize = Integer.parseInt(field[5]);
            double price = Double.parseDouble(field[6]);
            int internetQuota = Integer.parseInt(field[7]);
            int capLimit = Integer.parseInt(field[8]);
            int year = Integer.parseInt(field[9]);
            int month = Integer.parseInt(field[10]);
            int day = Integer.parseInt(field[11]);
            switch (field[0]) {
                case "pP":
                    String city = field[12];
                    PersonalPlan personalPlan = new PersonalPlan(userName, id,
                            new MobilePhone(mobileModel, enumType, memorySize, price), internetQuota, capLimit,
                            new MyDate(year, month, day), city);
                    plans.put(personalPlan.getId(), personalPlan);
                    in.readLine();
                    break;
                case "bP":
                    int numberOfEmployees = Integer.parseInt(field[12]);
                    int ABN = Integer.parseInt(field[13]);
                    BusinessPlan businessPlan = new BusinessPlan(userName, id,
                            new MobilePhone(mobileModel, enumType, memorySize, price), internetQuota, capLimit,
                            new MyDate(year, month, day), numberOfEmployees, ABN);
                    plans.put(businessPlan.getId(), businessPlan);
                    in.readLine();
                    break;
            }
            line = in.readLine();
        }
        in.close();
        return plans;
    }

    public static Boolean saveTextFile(HashMap<Integer, MobilePlan> plans, String fileName) throws IOException {
        boolean value = false;
        if (value == false) {
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new FileWriter(fileName));
            } catch (IOException e) {
                System.out.println(" Error : creat this file ! ");
            }
            try {
                for (MobilePlan plan : plans.values()) {
                    out.write(plan.toDelimitedString() + "\n");
                }
            } catch (IOException e) {
                System.out.println(" Error : can't save this files ! ");
            }
            try {
                out.close();
            } catch (IOException e) {
                System.out.println("Error : can't close this file ! ");
            }
            value = true;
        }
        return value;
    }
    // assignment 2

    public String usernameGenerator() {
        int randomNum = (int) ((Math.random() * (999999 - 100000)) + 100000);
        String username = "USR" + randomNum + "U";
        return username;
    }

    // Lab 8
    public static double calcPay(ArrayList<MobilePlan> plans, int flatRate) {
        return plans.stream().map(y -> y.calcPay(flatRate)).reduce(0.0, (a, b) -> a + b);
    }

    public static void mobilePriceRise(ArrayList<MobilePlan> plans, double risePercent) {
        plans.stream().forEach(x -> x.mobilePriceRise(risePercent));
    }

    public static ArrayList<MobilePlan> filterByMobileModel(ArrayList<MobilePlan> plans, String mobileModel) {
        return (ArrayList<MobilePlan>) (plans.stream().filter(x -> x.getMobileModel().contains(mobileModel))
                .collect(Collectors.toList()));
    }

    public static ArrayList<MobilePlan> filterByExpiryDate2(ArrayList<MobilePlan> plans, MyDate date) {
        return (ArrayList<MobilePlan>) (plans.stream().filter(x -> x.getExpiryDate().isExpired(date))
                .collect(Collectors.toList()));

    }
}
