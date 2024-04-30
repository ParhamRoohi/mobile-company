import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestCode {

        public static void testCode(MobileCompany mobileCompany)
                        throws CloneNotSupportedException, PlanException, IOException, UNException {
                int flatRate = 2;

                // _________________________________ lab 1 ____________________________________

                System.out.println(" Test code - Lab 01 ");

                User user1 = new User("parham", 01, new Address(22, "haft e tir", "Ab o bargh", "mashhad"),
                                "admin");
                BusinessPlan businessPlan1 = new BusinessPlan("USR000000U", 3000001,
                                new MobilePhone("x24", MobileType.Windows, 128, 7000),
                                86, 45, new MyDate(2023, 6, 17), 9, 56);
                PersonalPlan personalPlan1 = new PersonalPlan("USR000001U", 3000002,
                                new MobilePhone("iphone 12", MobileType.Ios, 128, 9000), 9, 88,
                                new MyDate(2026, 5, 26), "tehran");

                User user2 = new User("sepehr", 02, new Address(24, "jomhori", "tehran", "tehran"),
                                "parham@roohi");
                PersonalPlan personalPlan2 = new PersonalPlan("USR000002U", 3000003,
                                new MobilePhone(" s23 ", MobileType.Android, 256, 2000),
                                87, 77, new MyDate(2002, 4, 15), "mashhad");
                BusinessPlan businessPlan2 = new BusinessPlan("USR000003U", 3000004,
                                new MobilePhone(" Iphone 13 ", MobileType.Ios, 512, 5000), 45, 12,
                                new MyDate(2023, 8, 19), 98, 90);
                Ui ui = new Ui();

                // __________________________________ lab 2 ____________________________________

                System.out.println(" tset code - Lab 02 ");
                System.out.println(" \n\n\n\n\n ");
                user1.addPlan(personalPlan1);
                user1.addPlan(businessPlan1);
                user2.addPlan(personalPlan2);
                user2.addPlan(businessPlan2);
                mobileCompany.addUser(user1);
                mobileCompany.addUser(user2);

                user1.print();
                user1.toString();

                // MobilePlan userPlan1 = user1.findPlan(05);
                // UiMethod.findPlan(mobileCompany, 02, 01);// found
                // MobilePlan userPlan2 = user1.findPlan(01);// not found
                // UiMethod.findPlan(mobileCompany, 04, 02);
                // userPlan2.print();
                // userPlan2.mobilePriceRise(0.1);
                // userPlan2.print();
                // userPlan2.setUserName("Robert");
                // userPlan2.setMobileModel("iphone X");
                // user1.setCity("Wollongong");

                // ______________________________ check set address ___________________________

                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" check set address ");
                // System.out.println();
                // int streetNumber = 38;
                // String street = "saremi";
                // String suburb = "sanabad";
                // String city = "mashhad";
                // Address newAddress = new Address(streetNumber, street, suburb, city);
                // user1.setAddress(newAddress);
                // user1.print();

                // ______________________________ check mobile price ___________________________

                // System.out.println(" \n __________________________________________ \n ");
                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" check set price Rise ");
                // user1.printPlans(flatRate);
                // user1.mobilePriceRiseAll(0.1);
                // user1.printPlans(flatRate);

                // ___________________________ check filter mobile model _______________________

                // System.out.println(" \n __________________________________________ \n ");
                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" check filter mobile model ");
                // String mobileModel = "iphone";
                // MobilePlan.printPlans(user1.filterByMobileModel(mobileModel));

                // ___________________________ Lab 3 _______________________

                // System.out.println(" \n __________________________________________ \n ");
                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" tset code - Lab 03 ");
                // Address newAddress2 = new Address(24, "jomhori", "tehran", "tehran");
                // PersonalPlan personalPlan3 = new PersonalPlan("parham.roohi.123", 03,
                // new MobilePhone("nothing phone", MobileType.Android, 128, 600), 456, 786,
                // new MyDate(2024, 13, 3), "New York");

                // // ___________________________ check Login admin _______________________

                // System.out.println(" \n __________________________________________ \n ");
                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" check Login admin ");
                // UiMethod.loginAdmin(mobileCompany, "apple.compnay", "apple.123");// correct
                // UiMethod.loginAdmin(mobileCompany, "parham", "parham");// incorrect

                // // ___________________________ check add user _______________________

                // System.out.println(" \n __________________________________________ \n ");
                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" check add user ");
                // UiMethod.addUser(mobileCompany, "james", 01, newAddress, "james@Miller");//
                // was added
                // UiMethod.addUser(mobileCompany, "jack", 03, newAddress2, "jack.123");//
                // successfully added

                // // ___________________________ check add plan _______________________

                // System.out.println(" \n __________________________________________ \n");
                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" check add plan ");
                // UiMethod.addPlan(mobileCompany, 01, businessPlan1);// was added
                // UiMethod.addPlan(mobileCompany, 02, personalPlan3);// successfully added

                // // ___________________________ create BusinessPlan _________________

                // System.out.println(" \n\n\n\n\n ");
                // UiMethod.createBusinessPlan(mobileCompany, "parham.company", 05, 01,
                // new MobilePhone("A20", MobileType.Android, 64, 700), 67, 78, new MyDate(2023,
                // 4, 22),
                // 78, 29);// successfully added
                // UiMethod.createBusinessPlan(mobileCompany, "james.Miller", 01, 02,
                // new MobilePhone("x24", MobileType.Windows, 128, 700000),
                // 86, 45, new MyDate(2003, 6, 17), 24, 56);// was added

                // // ___________________________ create PersonalPlan _________________

                // System.out.println(" \n\n\n\n\n ");
                // UiMethod.createPersonalPlan(mobileCompany, 02, "parham.roohi.123", 03,
                // new MobilePhone("nothing phone", MobileType.Android, 128, 600), 456, 786,
                // new MyDate(2024, 13, 3), "rom");// successfully added
                // UiMethod.createPersonalPlan(mobileCompany, 01, "james.123", 02,
                // new MobilePhone("iphone 12", MobileType.Ios, 128, 90000), 99, 88,
                // new MyDate(2001, 5, 26),
                // " tehran ");// was added

                // // ___________________________ check Print Plan _________________

                // System.out.println(" \n __________________________________________ \n ");
                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" check Print Plan ");
                // UiMethod.printPlans(mobileCompany, 02);// print successfull
                // UiMethod.printPlans(mobileCompany, 05);// print was not successfull

                // // _____________________ check find Plan _________________

                // System.out.println(" \n __________________________________________ \n ");

                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" check find Plan ");
                // UiMethod.findPlan(mobileCompany, 02, 02);// finnd successfull
                // UiMethod.findPlan(mobileCompany, 04, 07);// finnd was not successfull

                // // ______________________ company Print _________________

                // System.out.println(" \n __________________________________________ \n");

                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" check company Print ");
                // mobileCompany.print();

                // // __________ check calculate total peyment for one user __________

                // System.out.println(" \n __________________________________________ \n");

                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" check calculate total peyment for one user ");
                // UiMethod.calctoTtalPaymentsForOneUser(mobileCompany, 02);

                // // ________________ check mobile price reise _________________

                // System.out.println(" \n __________________________________________ \n");
                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" check mobile price rise ");
                // UiMethod.mobilePriceRise(mobileCompany);

                // // __________ check calculate total peyment for one user __________

                // System.out.println(" \n __________________________________________ \n");
                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" check calculate total peyment for one user ");
                // UiMethod.calctoTtalPaymentsForOneUser(mobileCompany, 02);

                // // __________ check calculate total peyment for all user __________

                // System.out.println(" \n __________________________________________ \n");
                // System.out.println(" \n\n\n\n\n\n\n ");
                // System.out.println(" check calculate total peyment for all user ");
                // UiMethod.calcTotalPaymentsForAllUser(mobileCompany);

                // __________ check filter by expiry Date __________

                // System.out.println(" \n\n\n\n\n\n\n ");
                // UiMethod.filterByExpiryDate(mobileCompany, 01, new MyDate(2023, 5, 17));

                // __________ check filter by expiry Date 2 __________

                // System.out.println(" \n\n\n\n\n\n\n ");
                // UiMethod.filterByExpiryDate2(mobileCompany, new MyDate(2022, 8, 27));

                // __________ check filter by mobile model __________

                // System.out.println(" \n\n\n\n\n\n\n ");
                // UiMethod.filterByMobileModel(mobileCompany, 02, "Iphone ");

                // __________ check find user __________

                // System.out.println(" \n __________________________________________ \n");
                // System.out.println(" \n\n\n\n\n\n\n ");
                // System.out.println(" find user ");
                // UiMethod.findUser(mobileCompany, 01, new Address(22, "haft e tir", "Ab o
                // bargh", "mashhad"));// find
                // // successfull

                // UiMethod.findUser(mobileCompany, 05, new Address(45, "ahmad aobad",
                // "mashhad", "mashhad"));// find
                // // unsuccessfull

                // __________ populate Distinct City Names __________

                // System.out.println(" \n\n\n\n\n\n\n ");
                // ArrayList<String> cities = mobileCompany.populateDistinctCityNames();
                // System.out.println(cities);

                // __________ total Payment For City __________

                // System.out.println(" \n\n\n\n\n\n\n ");
                // System.out.println(" city : tehran total : " + cities);

                // __________ total Payment Per City __________

                // System.out.println(" \n\n\n\n\n\n\n ");
                // ArrayList<Double> totalPayments =
                // mobileCompany.getTotalPaymentPerCity(cities);
                // System.out.println(totalPayments);

                // __________ report Payment per City __________

                // System.out.println(" \n\n\n\n\n\n\n ");
                // mobileCompany.reportPaymentPerCity(cities, totalPayments);

                // ___________________________ Lab 4 _______________________

                // System.out.println(" \n __________________________________________ \n");
                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" tset code - Lab 04 \n\n ");

                // ArrayList<MobilePlan> mobilePlan = new ArrayList<>();
                // mobilePlan.add(personalPlan1);
                // mobilePlan.add(personalPlan2);
                // mobilePlan.add(businessPlan1);
                // mobilePlan.add(businessPlan2);
                // ArrayList<User> users = new ArrayList<>();
                // users.add(user1);
                // users.add(user2);
                // mobileCompany.addUser(user1);
                // mobileCompany.addUser(user2);

                // ArrayList<MobilePlan> shallowCopy = MobilePlan.shallowCopy(mobilePlan);
                // ArrayList<MobilePlan> deepCopy = new ArrayList<>();
                // try {
                // deepCopy = MobilePlan.deepCopy(mobilePlan);
                // } catch (CloneNotSupportedException e) {
                // throw new RuntimeException(e);
                // }
                // // System.out.println(" Original List : \n ");
                // // UiMethod.printMobilePlan(mobilePlan);

                // // System.out.println(" \n Shallow Copy list : \n ");
                // // UiMethod.printMobilePlan(shallowCopy);

                // // System.out.println(" \n Deep Copy list : \n ");
                // // UiMethod.printMobilePlan(deepCopy);

                // // System.out.println(" __________________________________________ \n ");

                // System.out.println(" cahange some personal plan's Ithem ");
                // // // Change the Username
                // personalPlan1.setUserName("farzam");

                // // Change the CapLimit
                // personalPlan1.setCapLimit(50);

                // // Change the City Name
                // personalPlan1.setCity("New York");

                // // Change the Mobile Model
                // personalPlan1.setMobileModel("iphone x");

                // // Change the Expiry Date of Plan to: 1/1/2020
                // personalPlan1.setExpiryDate(new MyDate(2020, 1, 1));

                // // System.out.println(" Original List after change Ithem : \n ");
                // // UiMethod.printMobilePlan(mobilePlan);

                // // System.out.println(" \n Shallow Copy list after change Ithem : \n ");
                // // UiMethod.printMobilePlan(shallowCopy);

                // // System.out.println(" \n Deep Copy list after change Ithem : \n ");
                // // UiMethod.printMobilePlan(deepCopy);

                // // System.out.println(" \n __________________________________________ \n");

                // System.out.println(" Add a new PersonalPlan \n ");
                // PersonalPlan personalPlan4 = new PersonalPlan("jordan", 3500000,
                // new MobilePhone(" iphone 7 ", MobileType.Android, 64, 200),
                // 56, 23, new MyDate(2002, 4, 15), "yazd");
                // mobilePlan.add(personalPlan4);
                // System.out.println(" print personalPlan 4 \n ");
                // personalPlan4.print();

                // System.out.println(" \n Original list after add new plan : \n ");
                // UiMethod.printMobilePlan(mobilePlan);

                // System.out.println(" \n Shallow Copy List after add new plan : \n ");
                // UiMethod.printMobilePlan(shallowCopy);

                // System.out.println(" \n Deep Copy List after add new plan : \n ");
                // UiMethod.printMobilePlan(deepCopy);

                // System.out.println(" \n __________________________________________ \n");

                // UiMethod.printSortedPlansByExpairyDate(users, 1);

                // System.out.println(" Original List After sorted by expiry date : \n ");
                // UiMethod.printMobilePlan(mobilePlan);

                // System.out.println(" \n Shallow Copy List After sorted by expiry date : \n
                // ");
                // UiMethod.printMobilePlan(shallowCopy);

                // System.out.println(" \n Deep Copy List After sorted by expiry date : \n ");
                // UiMethod.printMobilePlan(deepCopy);

                // ArrayList<User> shallowCopyUsers = User.shallowCopyUsers(users);
                // ArrayList<User> deepCopyUsers = new ArrayList<>();
                // try {
                // deepCopyUsers = User.deepCopyUsers(users);
                // } catch (CloneNotSupportedException e) {
                // throw new RuntimeException(e);
                // }

                // System.out.println(" \n __________________________________________ \n ");

                // System.out.println(" Original user's list : \n ");
                // UiMethod.printUser(users);

                // System.out.println(" \n Shallow Copy user's list : \n");
                // UiMethod.printUser(shallowCopyUsers);

                // System.out.println(" \n Deep Copy user's list : \n ");
                // UiMethod.printUser(deepCopyUsers);

                // System.out.println(" \n __________________________________________ \n ");

                // System.out.println(" \n Add a New User \n ");
                // User user3 = new User("faran", 03, new Address(34, "sajad", "sajad",
                // "mashhad"), "faran123");
                // users.add(user3);
                // System.out.println(" print user 3 : \n ");
                // user3.print();

                // System.out.println(" \n __________________________________________ \n ");

                // System.out.println(" \n Change User's City : \n ");
                // for (User user : users) {
                // user.setCity("karaj");
                // }

                // System.out.println(" \n Original list after change city : \n ");
                // UiMethod.printUser(users);

                // System.out.println(" \n Shallow Copy list after change city : \n ");
                // UiMethod.printUser(shallowCopyUsers);

                // System.out.println(" \n Deep Copy list after change city : \n");
                // UiMethod.printUser(deepCopyUsers);

                // System.out.println(" \n __________________________________________ \n ");

                // UiMethod.sortUser(mobileCompany);

                // System.out.println(" Original list After Sorted user : \n");
                // UiMethod.printUser(users);

                // System.out.println(" \n Shallow Copy After Sorted user :\n ");
                // UiMethod.printUser(shallowCopyUsers);

                // System.out.println(" \n Deep Copy After Sorted user : \n ");
                // UiMethod.printUser(deepCopyUsers);

                // System.out.println(" \n __________________________________________ \n ");

                // ___________________________ Lab 5 _______________________

                // System.out.println(" \n __________________________________________ \n");

                // System.out.println(" \n\n\n\n\n ");
                // System.out.println(" tset code - Lab 05 \n\n ");

                // HashMap<Integer, MobilePlan> shallowCopyHashMap =
                // MobilePlan.shallowCopyByHashMap(mobilePlan);
                // HashMap<Integer, MobilePlan> deepCopyHashMap = new HashMap<>();
                // try {
                // deepCopyHashMap = MobilePlan.deepCopyByHashMap(mobilePlan);
                // } catch (CloneNotSupportedException e) {
                // throw new RuntimeException(e);
                // }

                // System.out.println(" Original List : \n ");
                // UiMethod.printMobilePlan(mobilePlan);
                // System.out.println(" \n __________________________________________ \n");

                // System.out.println(" \n Shallow Copy list : \n ");
                // UiMethod.printMobilePlan(shallowCopyHashMap);
                // System.out.println(" \n __________________________________________ \n");

                // System.out.println(" \n Deep Copy list : \n ");
                // UiMethod.printMobilePlan(shallowCopyHashMap);
                // System.out.println(" \n __________________________________________ \n");

                // System.out.println(" \n total count per mobile model \n ");
                // HashMap<String, Integer> countHashMap = user1.getTotalCountPerMobileModel();
                // for (String model : countHashMap.keySet()) {
                // System.out.printf(" %s: %d\n ", model, countHashMap.get(model));
                // }
                // System.out.println(" \n __________________________________________ \n");

                // System.out.println(" \n total payments for mobile models \n ");
                // HashMap<String, Double> paymentsHashMap =
                // user1.getTotalPaymentPerMobileModel();
                // for (String model : paymentsHashMap.keySet()) {
                // System.out.printf(" %s: %,.2f$\n ", model, paymentsHashMap.get(model));
                // }
                // System.out.println(" \n __________________________________________ \n");

                // System.out.println(" \n Report payments per mobile model \n ");
                // user1.reportPaymentsPerMobileModel(countHashMap, paymentsHashMap);
                // user2.reportPaymentsPerMobileModel(countHashMap, paymentsHashMap);
                // System.out.println(" \n __________________________________________ \n");

                // System.out.println("\n Reports total payments per city \n ");
                // HashMap<String, Double> totalCity = mobileCompany.getTotalPaymentPerCity();
                // mobileCompany.reportPaymentPerCity(totalCity);

                // System.out.println(" \n __________________________________________ \n");

                // System.out.println(" \n total count per mobile model \n ");
                // countHashMap = new HashMap<>(mobileCompany.getTotalCountPerMobileModel());
                // for (String model : countHashMap.keySet()) {
                // System.out.printf(" %s: %d\n ", model, countHashMap.get(model));
                // }

                // System.out.println(" \n __________________________________________ \n");

                // System.out.println(" \n total monthly payments per mobile model \n ");
                // paymentsHashMap = new
                // HashMap<>(mobileCompany.getTotalPaymentPerMobileModel());
                // for (String model : paymentsHashMap.keySet()) {
                // System.out.printf(" %s: %,.2f$\n ", model, paymentsHashMap.get(model));
                // }
                // System.out.println(" \n __________________________________________ \n");

                // System.out.println(" \n Reports count and payments \n ");
                // mobileCompany.reportPaymentsPerMobileModel(countHashMap, paymentsHashMap);

                // ___________________________ Lab 6 _______________________

                System.out.println(" \n\n\n\n\n ");
                System.out.println(" tset code - Lab 06 \n\n ");

                System.out.println(" \n __________________________________________ \n ");
                System.out.println(" Testing Binary File and List of Plans \n ");
                MobilePlan.save(mobileCompany.allPlans(), "plans.ser");
                HashMap<Integer, MobilePlan> binaryPlan = MobilePlan.load("plans.ser");
                MobilePlan.printPlans(binaryPlan);
                binaryPlan.put(12,
                                new PersonalPlan("USR000004U", 3000000,
                                                new MobilePhone("iPhone 11", MobileType.Ios, 256, 1000),
                                                170, 12, new MyDate(2023, 5, 5), "Shiraz"));
                MobilePlan.save(binaryPlan, "plans.ser");
                binaryPlan.clear();
                binaryPlan = MobilePlan.load("plans.ser");
                MobilePlan.printPlans(binaryPlan);

                System.out.println(" \n\n\n\n\n ");
                System.out.println(" \n __________________________________________ \n ");
                System.out.println(" Testing Binary File and List of Users ");
                User.save(mobileCompany.getUser(), "users.ser");
                HashMap<Integer, User> binaryUser = User.load("users.ser");
                User.printUser(binaryUser);
                User user = new User("farzam.roohi", 200, new Address(56, "kaj", "sadat abad", "tehran"), "1234");
                user.addPlan(new PersonalPlan("USR000004U", new MobilePhone("iPhone 11", MobileType.Ios, 256, 1000),
                                170,
                                12, new MyDate(2023, 5, 5), "Shiraz"));
                binaryUser.put(120, user);
                User.save(binaryUser, "users.ser");
                binaryUser.clear();
                binaryUser = User.load("users.ser");
                User.printUser(binaryUser);

                System.out.println(" \n\n\n\n\n ");
                System.out.println(" \n __________________________________________ \n ");
                System.out.println(" Testing Text File and List of Plans with toDelimitedString \n ");
                MobilePlan.saveTextFile(mobileCompany.allPlans(), "plans.txt");
                HashMap<Integer, MobilePlan> plans = MobilePlan.loadTextFile("plans.txt");
                MobilePlan.printPlans(plans);
                plans.put(12,
                                new PersonalPlan("USR000004U", 3000000,
                                                new MobilePhone("iPhone 11", MobileType.Ios, 256, 1000),
                                                170, 12, new MyDate(2023, 5, 5), "Shiraz"));
                MobilePlan.saveTextFile(plans, "plans.txt");
                plans.clear();
                plans = MobilePlan.loadTextFile("plans.txt");
                MobilePlan.printPlans(plans);

                System.out.println(" \n\n\n\n\n ");
                System.out.println(" \n __________________________________________ \n ");
                System.out.println(" Testing Text File and List of Users with toDelimitedString \n ");
                User.saveTextFile(mobileCompany.getUser(), "users.txt");
                HashMap<Integer, User> users = User.loadTextFile("users.txt");
                User.printUser(users);
                User user3 = new User("farzam.roohi", 200, new Address(56, "kaj", "sadat abad", "tehran"),
                                "1234");
                user3.addPlan(new PersonalPlan("USR000004U", 3000000,
                                new MobilePhone("iPhone 11", MobileType.Ios, 256, 1000),
                                150, 15, new MyDate(2023, 12, 1), "Shiraz"));
                users.put(120, user3);
                User.saveTextFile(users, "users.txt");
                users.clear();
                users = User.loadTextFile("users.txt");
                User.printUser(users);

                System.out.println(" \n\n\n\n\n ");
                System.out.println(" \n __________________________________________ \n ");
                System.out.println(" MobileCompany and Binary File \n ");
                mobileCompany.save("company.ser");
                MobileCompany mobileCompanyTest1 = new MobileCompany("test", "test", "test", 2);// using default
                                                                                                // constructor
                mobileCompanyTest1.load("company.ser");// all users and all plans are loaded
                System.out.println(mobileCompanyTest1);
                mobileCompanyTest1.addUser(user1);
                mobileCompanyTest1.addPlan(1, businessPlan1);
                mobileCompanyTest1.save("company.ser");
                MobileCompany mobileCompanyTest2 = new MobileCompany("test2", "test2", "test2", 2);
                mobileCompanyTest2.load("company.ser");
                System.out.println(mobileCompanyTest2);

                System.out.println(" \n\n\n\n\n ");
                System.out.println(" \n __________________________________________ \n ");
                System.out.println(" MobileCompany and Text File \n ");
                mobileCompany.saveTextFile("company.txt");
                MobileCompany mobileCompanyText1 = new MobileCompany("test3", "test3", "test3", 2);// using default
                                                                                                   // constructor
                mobileCompanyText1.loadTextFile("company.txt");// all users and all plans are loaded
                System.out.println(mobileCompanyText1);
                mobileCompanyText1.addUser(user1);
                mobileCompanyText1.addPlan(1, personalPlan1);
                mobileCompanyText1.saveTextFile("company.txt");
                MobileCompany mobileCompanyText2 = new MobileCompany("test4", "test4", "test4", 2);
                mobileCompanyText2.loadTextFile("company.txt");
                System.out.println(mobileCompanyText2);

                System.out.println(" \n\n\n\n\n ");
                System.out.println(" \n __________________________________________ \n ");

                System.out.println(" \n\n Assignment 2 - Core TestCase\n\n ");

                HashMap<String, ArrayList<User>> usersCity = mobileCompany.getUsersPerCity();
                System.out.println(" Report Users Cities \n ");
                mobileCompany.reportUserPerCity(usersCity);
                System.out.println(" \n\n ");

                System.out.println(" \n __________________________________________ \n ");

                HashMap<String, ArrayList<MobilePlan>> filteredPlansByExpiryDate = mobileCompany
                                .filterPlansByExpiryDate(new MyDate(2026, 5, 7));
                System.out.println(" Report Filtered Mobile Plans By Expiry Date \n ");
                mobileCompany.reportFilterPlansByExpiryDate(filteredPlansByExpiryDate);
                System.out.println("\n\n");

                // ___________________________ Lab 8 _______________________

                System.out.println(" \n __________________________________________ \n ");

                System.out.println(" \n\n\n\n\n ");
                System.out.println(" Test code - Lab 08 \n\n ");

                ArrayList<MobilePlan> plans1 = mobileCompany.allPlan();

                System.out.println(" Username --- plans \n ");
                MobilePlan.printPlans((ArrayList<MobilePlan>) (plans1.stream()
                                .filter(x -> x.getUserName().contains("2")).sorted().collect(Collectors.toList())));

                System.out.println(" Username --- payment \n ");
                plans1.stream().filter(x -> x.getUserName().contains("2"))
                                .forEach(x -> System.out.printf(" Name : %s Total : %,.2f$ \n ", x.getUserName(),
                                                x.calcPay(mobileCompany.getFlatRate())));

                System.out.println(" Calculate payment between 1000 to 1300 ");
                plans1.stream().filter(x -> x.calcPay(mobileCompany.getFlatRate()) >= 1000
                                && x.calcPay(mobileCompany.getFlatRate()) <= 1300).findFirst()
                                .map(x -> System.out.printf(" Name : %s Total : %,.2f$ \n ", x.getUserName(), x.getId(),
                                                x.calcPay(mobileCompany.getFlatRate())));

                System.out.println(" Calculate  total payment between 1000 to 1300 ");
                System.out.printf(" Total : %.2f$ \n ", plans1.stream()
                                .filter(x -> x.calcPay(mobileCompany.getFlatRate()) >= 1000
                                                && x.calcPay(mobileCompany.getFlatRate()) >= 1300)
                                .map(x -> x.calcPay(mobileCompany.getFlatRate())).reduce(0.0, (x, y) -> x + y));
        }
}
