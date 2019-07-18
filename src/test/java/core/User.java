package core;

import java.util.Random;

public class User {
    private String lastName;
    private String firstName;
    private String patronymic;
    private String telephoneNumber;
    private String birthDate;
    private String passportSeries;
    private String passportNumber;
    private String passportIssuePlace;
    private String passportIssueDate;
    private String email;

    private static String[] lastNames = {"Иванов", "Петров", "Сидоров", "Пупкин"};
    private static String[] firstNames = {"Иван", "Петр", "Василий", "Александр"};
    private static String[] patronymics = {"Иванович", "Петрович", "Евгеньевич", "Андреевич"};
    private static String numbers = "0123456789";
    private static Random random;

    public User() {

    }

    public User(String lastName, String firstName, String patronymic, String telephoneNumber, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
    }

    public static User getRandomUserForRgs() {
        random = new Random();
                return new User(lastNames[random.nextInt(4)],
                        firstNames[random.nextInt(4)],
                        patronymics[random.nextInt(4)],
                        getRandomTelephoneNumber(random), "qwertyqwerty");
    }


    public static String getRandomTelephoneNumber(Random random) {
        StringBuilder telephoneNumberBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                telephoneNumberBuilder.append("9");
            } else {
                char ch = numbers.charAt(random.nextInt(numbers.length()));
                telephoneNumberBuilder.append(ch);
            }
        }
        telephoneNumberBuilder.insert(0,"(");
        telephoneNumberBuilder.insert(4,")");
        telephoneNumberBuilder.insert(5," ");
        telephoneNumberBuilder.insert(9,"-");
        telephoneNumberBuilder.insert(12,"-");
        return telephoneNumberBuilder.toString();
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }


    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportIssuePlace() {
        return passportIssuePlace;
    }

    public void setPassportIssuePlace(String passportIssuePlace) {
        this.passportIssuePlace = passportIssuePlace;
    }

    public String getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(String passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

}
