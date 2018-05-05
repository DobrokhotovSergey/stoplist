package ua.varus.stoplist.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.varus.stoplist.domain.search.SearchJur;
import ua.varus.stoplist.domain.search.SearchPhys;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoplistSearchForm {
    private String subject;
    private SearchJur searchJur;
    private SearchPhys searchPhys;
}
