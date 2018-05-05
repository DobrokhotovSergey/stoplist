package ua.varus.stoplist.domain.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.varus.stoplist.domain.custom.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlagCodificatorApi{
    private boolean contains;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastEditDate;

}
