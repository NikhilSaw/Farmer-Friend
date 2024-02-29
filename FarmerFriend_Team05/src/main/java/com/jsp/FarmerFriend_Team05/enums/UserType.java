package com.jsp.FarmerFriend_Team05.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum UserType {
    @Enumerated(EnumType.STRING)
    FARMERS,
    @Enumerated(EnumType.STRING)
    CONSULTANTS;
}
