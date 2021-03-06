package bookstore.api.bookstore.service.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 28-Mar-21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchCriteria extends SearchCriteria{
    private String username;
    private String firstName;
    private String lastName;
    private Set<String> roles;

}
