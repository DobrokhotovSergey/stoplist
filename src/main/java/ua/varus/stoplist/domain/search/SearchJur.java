package ua.varus.stoplist.domain.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchJur {
    private String okpo;
    private String name;
    private String subject;
}
