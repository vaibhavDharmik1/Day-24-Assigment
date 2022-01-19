import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Person implements AddressBookIF  {
    static Scanner sc = new Scanner(System.in);
    static String firstName,lastName,address,state,email,city;
    static int zip;
    static long phoneNumber;
    ContactInfo infoContact = new ContactInfo();
    Contacts contact = new Contacts(firstName, lastName, address, city, state, zip, phoneNumber, email);
    static Map<String,Contacts> detailsBook = new HashMap<>();
    static Map<String,Contacts> personByCity = new HashMap<>();
    Map<String,Contacts> personByState = new HashMap<>();
    static Map<String,Map<String,Contacts>> book = new HashMap<>();

    public void createContact() {
        System.out.println("Enter how many contacts you want to create");
        int numOfContact = sc.nextInt();
        while(numOfContact > 0) {
            System.out.println("Enter Name of the person");
            String firstName = sc.next();
            if(!detailsBook.containsKey(firstName)) {
                infoContact.info();
                detailsBook.put(firstName,new Contacts(firstName, lastName, address, city, state, zip, phoneNumber, email));
                numOfContact--;
            }
            else {
                System.out.println("Contact already exists!");
            }
        }
    }
    public void addContact()  {
        System.out.println("Enter how many contacts you want to add");
        int numOfContacts = sc.nextInt();
        while(numOfContacts > 0) {
            System.out.println("Enter Name of person");
            String firstName = sc.next();
            if(detailsBook.containsKey(firstName)) {
                System.out.println("Contact already exists!");
                return;
            }
            else {
                infoContact.info();
                detailsBook.put(firstName,new Contacts(firstName, lastName, address, city, state, zip, phoneNumber, email));
                numOfContacts--;
            }
        }
    }
    public void editContact()  {
        System.out.println("Enter name of person whose contact you want to edit");
        String firstName = sc.next();
        if(detailsBook.containsKey(firstName)) {
            infoContact.info();
            detailsBook.put(firstName,new Contacts(firstName, lastName, address, city, state, zip, phoneNumber, email));
        }
        else {
            System.out.println("Contact is not present in book");
        }
    }
    public void deleteContact()  {
        System.out.println("Enter name of person whose contact you want to delete");
        String name = sc.next();
        if(detailsBook.containsKey(name)) {
            detailsBook.remove(name);
        }
        else {
            System.out.println("Contact is not present in book");
        }
    }
    public void display() {
        System.out.println("Created contact list is");
        for(Entry m : detailsBook.entrySet()) {
            System.out.println(m.getKey()+"--> "+m.getValue());
        }
    }
    public void addToBook() {
        System.out.println("Enter Name of Address Book");
        String bookName = sc.next();
        if(!book.containsKey(bookName)) {
            book.put(bookName, detailsBook);
        }
        else {
            System.out.println("Book already exists!");
        }
    }
    public void operation() {
        System.out.println("Enter how many address books you want to add");
        int noOfAddressBook = sc.nextInt();
        while(noOfAddressBook > 0) {
            addContact();
            addToBook();
            noOfAddressBook--;
        }
    }
    public void searchPerson() {
        System.out.println("Enter person name to search ");
        String name = sc.next();
        Map<String,Contacts> searchedPerson =detailsBook.entrySet().stream().filter(e->e.getKey().equals(name)).collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));
        System.out.println(searchedPerson);
    }
    public void displayBook() {
        System.out.println("Address Books are:");
        for(Entry e : book.entrySet()) {
            System.out.println(e.getKey()+"--> "+e.getValue());
        }
    }
    public void addContactByCity()  {
        System.out.println("Enter how many contacts you want to add");
        int numOfContacts = sc.nextInt();
        while(numOfContacts > 0) {
            System.out.println("Enter Name of city");
            String cityName = sc.next();
            if(personByCity.containsKey(cityName)) {
                System.out.println("Contact already exists!");
                return;
            }
            else {
                infoContact.info();
                personByCity.put(city,new Contacts(firstName, lastName, address, city, state, zip, phoneNumber, email));
                numOfContacts--;
            }
        }
        System.out.println(personByCity);
    }
    public void addContactByState()  {
        System.out.println("Enter how many contacts you want to add");
        int numOfContacts = sc.nextInt();
        while(numOfContacts > 0) {
            System.out.println("Enter Name of city");
            String stateName = sc.next();
            if(personByState.containsKey(stateName)) {
                System.out.println("Contact already exists!");
                return;
            }
            else {
                infoContact.info();
                personByState.put(state,new Contacts(firstName, lastName, address, city, state, zip, phoneNumber, email));
                numOfContacts--;
            }
        }
    }
    public void viewByCity() {
        System.out.println("Enter city name to search ");
        String cityName = sc.next();
        Map<String,Contacts> detailsByCity =personByCity.entrySet().stream().filter(e->e.getKey().equals(cityName)).collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));
        System.out.println(detailsByCity);
        System.out.println("Number of person belonging to city is: "+detailsByCity.size());
    }
    public void viewByState() {
        System.out.println("Enter state name to search ");
        String stateName = sc.next();
        Map<String,Contacts> detailsByState =personByState.entrySet().stream().filter(e->e.getKey().equals(stateName)).collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));
        if(detailsByState!=null) {
            System.out.println(detailsByState);
            System.out.println("Number of person belonging to state is: "+detailsByState.size());
        }else {
            System.out.println("Person with particular state is not present");
        }
    }
    public void sortAddressBook() {

        Map<String,Contacts> sortedContact =detailsBook.entrySet().stream()
                .sorted(Entry.comparingByKey()).collect(Collectors.toMap(Entry::getKey, Entry::getValue
                        ,(oldValue,newValue)->oldValue,LinkedHashMap::new));
        System.out.println("Sorted Address Book "+ sortedContact);

    }
    public void sortByCity() {

        Map<String,Contacts> sortedByCity =personByCity.entrySet().stream()
                .sorted(Entry.comparingByKey()).collect(Collectors.toMap(Entry::getKey, Entry::getValue
                        ,(oldValue,newValue)->oldValue,LinkedHashMap::new));
        System.out.println("Sorted Address Book "+sortedByCity);

    }
    public void sortByState() {

        Map<String,Contacts> sortedByState =personByState.entrySet().stream()
                .sorted(Entry.comparingByKey()).collect(Collectors.toMap(Entry::getKey, Entry::getValue
                        ,(oldValue,newValue)->oldValue,LinkedHashMap::new));
        System.out.println("Sorted Address Book "+sortedByState);

    }
    public void writeToAddressBookFile() {

        String bookName = "personByCity";
        String fileName = bookName+".txt";

        StringBuffer addressBookBuffer = new StringBuffer();
        personByCity.values().stream().forEach(contact -> {
            String personDataString = contact.toString().concat("\n");
            addressBookBuffer.append(personDataString);
        });

        try {
            Files.write(Paths.get(fileName), addressBookBuffer.toString().getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> readDataFromFile() {

        List<String> addressList = new ArrayList<>();
        String bookName ="personByCity";
        String fileName = bookName+".txt";
        System.out.println("Reading from : "+fileName+"\n");
        try {
            Files.lines(new File(fileName).toPath())
                    .map(line -> line.trim())
                    .forEach(employeeDetails -> {
                        System.out.println(employeeDetails);
                        addressList.add(employeeDetails);
                    });

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return addressList;
    }


}