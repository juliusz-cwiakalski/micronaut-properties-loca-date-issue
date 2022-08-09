package pl.jcw.bugreport;

import static org.assertj.core.api.Assertions.assertThat;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

@MicronautTest(environments = "property-as-date")
class LocalDateAsYamlDateTest {

  @Inject Config config;

  @Test
  void shouldContainLocalDateExpressedAsStringInYaml() {
    assertThat(config.getLocalDateProperty()).isEqualTo(LocalDate.of(2022, 1, 1));
  }
}
