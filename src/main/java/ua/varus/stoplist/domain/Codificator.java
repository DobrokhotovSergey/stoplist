package ua.varus.stoplist.domain;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class Codificator {
    private byte code;
    private String name;
}
