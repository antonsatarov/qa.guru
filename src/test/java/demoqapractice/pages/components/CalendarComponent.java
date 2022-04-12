package demoqapractice.pages.components;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void setDate(String month, String year, String day) {
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day.react-datepicker__day--0"+day+":not(.react-datepicker__day--outside-month)").click();
    }
}
