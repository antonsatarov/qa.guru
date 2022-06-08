package reqrestests.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    @JsonProperty
    private String name;
    @JsonProperty
    private String job;

    public Employee(){}
    public Employee(String name, String job) {
        this.name = name;
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(job, employee.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, job);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
