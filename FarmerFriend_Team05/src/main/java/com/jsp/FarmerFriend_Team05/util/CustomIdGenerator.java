package com.jsp.FarmerFriend_Team05.util;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.jsp.FarmerFriend_Team05.entity.*;

public class CustomIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = 1L;
    private static final ConcurrentHashMap<Class<?>, AtomicLong> SEQUENCES = new ConcurrentHashMap<>();

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        AtomicLong sequence = SEQUENCES.computeIfAbsent(object.getClass(), k -> new AtomicLong(1));
        String prefix = getPrefix(object);
        long sequenceNumber = sequence.getAndIncrement();
        return prefix + "_" + String.format("%05d", sequenceNumber);
    }

    private String getPrefix(Object object) {
        if (object instanceof User) {
            return "User";
        } else if (object instanceof Address) {
            return "Address";
        } else if (object instanceof Image) {
            return "Image";
        } else if (object instanceof Comment) {
            return "Comment";
        } else if (object instanceof Equipment) {
            return "Equipment";
        } else if (object instanceof Post) {
            return "Post";
        }else if(object instanceof Payment){
        	return "Payment";
        }else if(object instanceof Rental){
        	return "Rental";
        }else {
            return "Default";
        }
    }
}
