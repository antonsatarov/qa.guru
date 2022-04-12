package demoqa_tests.docs;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FakerExamples {




    public static void main(String[] args) throws ParseException {
        Faker faker = new Faker();

        Date date = faker.date().birthday();
        System.out.println(date);

        DateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");

        System.out.println(dateFormat.format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        System.out.println(calendar.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.getDefault()));
        System.out.println(new SimpleDateFormat("dd").format(date));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.YEAR));



    }


}
