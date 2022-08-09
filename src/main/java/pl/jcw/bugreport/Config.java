package pl.jcw.bugreport;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.Introspected;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

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
