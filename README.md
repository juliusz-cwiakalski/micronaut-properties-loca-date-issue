# Overview

This repository demonstrates pitfalls with Micronaut 3.6.0 properties mapping when
using `java.time.LocalDate` properties in `application.yml`.

See also [YAML timestamp specification](https://yaml.org/type/timestamp.html).

Tests use following properties class

```java

@ConfigurationProperties("jcw")
@Data
@Introspected
public class Config {

  LocalDate localDateProperty;
  List<DatesListItem> dates;

  @Builder
  @Data
  @Introspected
  public static class DatesListItem {

    private String id;
    private String stringDate;
    private Date javaUtilDate;
    private LocalDate localDate;
  }

}
```

## Problem 1: LocalDate property expressed as YAML date reports that `Property doesn't exist`

Example config

```yaml
jcw:
  local-date-property: 2022-01-01

```

Test that demonstrates the
issue [LocalDateAsYamlDateTest](src/test/java/pl/jcw/bugreport/LocalDateAsYamlDateTest.java).

## Problem 2: LocalDate property expressed as YAML string reports that `Unable to convert value [2022-01-01] to target type [LocalDate] due to: Text '2022-01-01' could not be parsed at index 4`

Example config

```yaml
jcw:
  local-date-property: '2022-01-01'
```

Test that demonstrates the
issue [LocalDateAsYamlStringTest](src/test/java/pl/jcw/bugreport/LocalDateAsYamlStringTest.java).

## Problem 3: List elements containing `LocalDate` property specified as YAML date are silently discarded

Example config

```yaml
jcw:
  dates:
    - id: stringDate
      stringDate: 2022-01-01
    - id: javaUtilDate
      javaUtilDate: 2022-01-01
    # below entry is not mapped by micronaut because local date is specified as yaml timestamp
    # see https://yaml.org/type/timestamp.html
    - id: localDateAsDate
      localDate: 2022-01-01
    - id: localDateAsString
      localDate: '2022-01-01'
    -
```

Test that demonstrates the
issue [DatesOnListTest#shouldContainLocalDateExpressedAsDateInYaml](src/test/java/pl/jcw/bugreport/DatesOnListTest.java)
.

