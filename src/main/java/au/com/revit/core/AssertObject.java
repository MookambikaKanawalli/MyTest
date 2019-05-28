package au.com.revit.core;

import org.assertj.core.api.SoftAssertions;

public class AssertObject {

    private SoftAssertions softAssertions;

    private SoftAssertions softly;

    private Object actual;
    private Object expected;
    private String description;

    public AssertObject() {
        this.softly = new SoftAssertions();

    }

    public AssertObject assertThat(Object actual) {
        this.actual = actual;
        return this;
    }

    public AssertObject as(String description) {
        this.description = description;
        return this;
    }

    public void isEqualTo(Object expected) {
        this.expected = expected;
        this.softly.assertThat(this.actual).as(this.description).isEqualTo(this.expected);
    }

    public void assertAll() {
        this.softly.assertAll();
    }

    public void isGreaterThan(Object expected) {
        this.softly.assertThat(this.actual).as(this.description).isEqualTo(this.expected);
    }
}
