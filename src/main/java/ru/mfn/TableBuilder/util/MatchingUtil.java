package ru.mfn.TableBuilder.util;


import lombok.experimental.UtilityClass;
import ru.mfn.TableBuilder.model.auth.Role;
import ru.mfn.TableBuilder.repository.RoleRepository;

import java.util.Set;

@UtilityClass
public class MatchingUtil {

    public static final String FIELD_NAME_REGEX = "[a-zA-Z]{1}[a-zA-Z0-9_-]*$";
    public static final String ID_REGEX = "^[1-9][0-9]*$";


}
