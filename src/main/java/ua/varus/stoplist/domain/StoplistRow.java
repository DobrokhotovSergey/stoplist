package ua.varus.stoplist.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.varus.stoplist.domain.custom.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoplistRow {

    private int id;
    private BigDecimal inn;
    private BigDecimal okpo;
    private String fio;
    private Date birthDate;
    private String pasportSerial;
    private String pasportNumber;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime editDate;
    private String status;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime removeDate;
    private String comment;

    private Codificator codificator;

    private String createLoginEmployee;
    private String editLoginEmployee;
    private String createCompany;
    private String createDepartment;


    private String source;
}
