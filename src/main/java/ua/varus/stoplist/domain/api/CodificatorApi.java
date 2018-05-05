package ua.varus.stoplist.domain.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.varus.stoplist.domain.Codificator;
import ua.varus.stoplist.domain.custom.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodificatorApi extends Codificator {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastEditDate;

    public CodificatorApi(LocalDateTime lastEditDate, byte code, String name) {
        super(code, name);
        this.lastEditDate = lastEditDate;
    }

}
