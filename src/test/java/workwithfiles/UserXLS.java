package workwithfiles;

public class UserXLS {
    Double xlsId;
    String firstName;
    String lastName;
    String gender;
    String country;
    Double age;
    String date;
    Double id;
    int rowNumber;

    public UserXLS(Double xlsId, String firstName, String lastName, String gender, String country, Double age, String date, Double id, int rowNumber) {
        this.xlsId = xlsId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.country = country;
        this.age = age;
        this.date = date;
        this.id = id;
        this.rowNumber = rowNumber;
    }
}
