package com.jsp.FarmerFriend_Team05.enums;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum UserType {
    @Enumerated(EnumType.STRING)
    FARMERS,
    @Enumerated(EnumType.STRING)
    SPECIALIST;
}
