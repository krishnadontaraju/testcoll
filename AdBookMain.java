import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdBookMain {
    public Scanner fetch = new Scanner(System.in);
    List<AdBookSource> contactBook = new ArrayList<>();

    public void contactOperations() {

        System.out.println("Welcome Contact Operations \nProceed with below");

        boolean userSatisfaction = false;

        while (!userSatisfaction) {
            System.out
                    .println("Please select\n1.Add a new Contact\n2.Change an Existing Contact\n3.Delete an Existing Contact\n4.Move back to previous menu");
            int choice = fetch.nextInt();
            switch (choice) {
                case 1://Checking for duplicates if not, then add contact
                    if (!duplicateCheck(getFirstName(),getLastName())) {
                        contactBook.add(addContact());
                    }
                    System.out.println(contactBook.toString());
                    break;
                case 2://Checking for contacts and then editing them
                    System.out.println("Type first name for checking");
                    String testFirstName = fetch.next();

                    AdBookSource firstTestContact = searchContact(testFirstName,getLastName());

                    if (firstTestContact != null) {
                        changeContact(firstTestContact);
                    }

                    System.out.println(contactBook.toString());
                    break;
                case 3://Checking for contacts and then deleting them
                    System.out.println("Type the first name for Checking");
                    String newTestFirstName = fetch.next();

                    AdBookSource secondTestContact = searchContact(newTestFirstName,getLastName());

                    if (secondTestContact != null) {
                        contactBook.remove(secondTestContact);
                    }
                    System.out.println(contactBook.toString());
                    break;
                default:
                    userSatisfaction = true;

            }
        }

    }

    public boolean duplicateCheck(String firstName , String lastName) {
        //Looking for match in address book to avoid duplicate
        return contactBook.stream()
                .anyMatch(rotator -> rotator.getFirstName().equals(firstName)
                                            && rotator.getLastName().equals(lastName));

    }

    public AdBookSource searchContact(String firstName, String lastName) {
        //Filtering through the Book to find the contact based on input
        AdBookSource person = contactBook.stream().filter(
                                                personElement -> personElement.getFirstName().equals(firstName)
                                                && personElement.getLastName().equals(lastName))
                                                .findFirst().orElse(null);

        if (person == null) {
            System.out.println("No person present matching with your given name");
            return null;
        } else {
            return person;
        }

    }

    public String getFirstName() {
        System.out.println("Type the first name for checking");
        return fetch.next();

    }
    public String getLastName(){
        System.out.println("Type the last name for checking for even more depth Checking");
        return fetch.next();
    }

    public AdBookSource changeContact(AdBookSource testContact) {

        System.out.println(testContact);

        System.out.println("Which detail do you want to change ?");
        System.out.println("1.First Name \n2.Last Name \n3.Mobile\n4.Email \n5.City\n6.State\7.Zip Code");

        switch (fetch.nextInt()){
            case 1:
                System.out.println("To what you want to change the First Name");
                testContact.setFirstName(fetch.next());
                break;

            case 2 :
                System.out.println("To what you want to change the Last Name");
                testContact.setLastName(fetch.next());
                break;

            case 3 :
                System.out.println("To what you want to change the Mobile");
                testContact.setMobile(fetch.nextLong());
                break;

            case 4 :
                System.out.println("To what you want to change the Email");
                testContact.setEmail(fetch.next());
                break;

            case 5 :
                System.out.println("To what you want to change the City");
                testContact.setCity(fetch.next());
                break;

            case 6 :
                System.out.println("To what you want to change the State");
                testContact.setState(fetch.next());
                break;

            case 7 :
                System.out.println("To what you want to change the Zip Code");
                testContact.setZipCode(fetch.nextInt());
                break;

            default:
                break;

        }

        return testContact;
    }

    public List<AdBookSource> findByStateOrCity(String cityOrState){

        List<AdBookSource> collectList = contactBook.stream().filter(contact -> contact.getCity().equals(cityOrState))
                                                        .collect(Collectors.toList());

        return collectList;
    }

    public AdBookSource addContact() {
        AdBookSource person = new AdBookSource();
        person.setDetails();
        return person;
    }
    

}
