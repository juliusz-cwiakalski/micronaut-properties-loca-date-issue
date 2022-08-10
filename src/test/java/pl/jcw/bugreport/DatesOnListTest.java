package pl.jcw.bugreport;

import static pl.jcw.bugreport.Config.DatesListItem.builder;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.Date;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.jcw.bugreport.Config.DatesListItem;

@MicronautTest(environments = "list")
class DatesOnListTest {

  @Inject Config config;

  @Test
  void shouldContainStringDate() {
    Assertions.assertThat(config.getDates())
        .contains(builder().id("stringDate").stringDate("1640995200000").build());
  }

  @Test
  void shouldContainJavaUtilDate() {
    Assertions.assertThat(config.getDates())
        .contains(builder().id("javaUtilDate").javaUtilDate(new Date(1640995200000L)).build());
  }

  @Test
  void shouldContainLocalDateExpressedAsDateInYaml() {
    Assertions.assertThat(config.getDates())
        .contains(builder().id("localDateAsDate").localDate(LocalDate.of(2022, 1, 1)).build());
  }

  @Test
  void shouldContainLocalDateExpressedAsStringInYaml() {
    Assertions.assertThat(config.getDates())
        .contains(builder().id("localDateAsString").localDate(LocalDate.of(2022, 1, 1)).build());
  }

  @Test
  void shouldReadAllConfiguredItems() {
    Assertions.assertThat(config.getDates())
        .extracting(DatesListItem::getId)
        .containsExactlyInAnyOrder(
            "stringDate",
            "javaUtilDate",
            "localDateAsDate",
            "localDateAsString",
            "justId",
            "invalidLocalDateAsString");
  }
}
