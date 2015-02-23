package hello.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting {
    @JsonProperty("id")
    private  long id;

    @JsonProperty("name")
    private String content;

    @JsonProperty("person")
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
