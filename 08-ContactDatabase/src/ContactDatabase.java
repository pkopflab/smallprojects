/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

//import java.util.*;

public class ContactDatabase {

    public static void main(String[] args) {

//        testOpenHashTable();
        testClosedHashTable();
    }

    private static void testOpenHashTable() {
        AuDOpenHashTable openHashTable = new AuDOpenHashTable(10);

        Contact contact1 = new Contact("asdswe@sssdf.com");
        contact1.setName("Person1");

        Contact contact2 = new Contact("kfjgik@sfhdfg.com");
        contact2.setName("Person2");

        Contact contact3 = new Contact("kflpghja@nvbdrtg.com");
        contact3.setName("Person3");

        openHashTable.insert(contact1);
        openHashTable.insert(contact2);
        openHashTable.insert(contact3);

        openHashTable.remove(contact2);

        openHashTable.getContact("asdswe@sssdf.com").setTelephone("+187 4269420123");

//        for (int i = 0; i < 500; i++) {
//            openHashTable.insert(new Contact(generateEmailString()));
//        }

        openHashTable.displayTable();
    }

    private static void testClosedHashTable() {
        AuDClosedHashTable closedHashTable = new AuDClosedHashTable(10);
//        closedHashTable.insert(new Contact(generateEmailString()));
        closedHashTable.insert(new Contact("@.123"));
        closedHashTable.getContact("@.123").setName("oskdjskejnfskmd");
        closedHashTable.remove(new Contact("@.123"));
        closedHashTable.insert(new Contact("@.123"));
        closedHashTable.insert(new Contact("@.123"));

//
//        for (int i = 0; i < 9; i++) {
//            closedHashTable.insert(new Contact(generateEmailString()));
//        }

//        for (int i = 0; i < 500; i++) {
//            closedHashTable.insert(new Contact(generateEmailString()));
//        }
        closedHashTable.displayTable();


    }

    //Test Methode um automatische Email Strings zu erzeugen
//    public static String generateEmailString() {
//        String uuid = UUID.randomUUID().toString();
//        return "@." + uuid.replace("-", "");
//    }
}
