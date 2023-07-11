package com.vetpet.apprest.domain.agregates;

import java.security.SecureRandom;

public class MicrochipIDGenerator {
    public String generateMicrochipID() {

        // Generate the country of origin (3 digits)
        String countryNumber = generateRandomNumber(3);

        // Generate the manufacturer's code (3 digits)
        String code = generateRandomNumber(3);

        // Generate the identification number (9 digits)
        String numberIdentification = generateRandomNumber(9);

        return countryNumber + code + numberIdentification;
    }

    private String generateRandomNumber(int length) {
        SecureRandom secureRandom = new SecureRandom();
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length) - 1;
        int randomNumber = secureRandom.nextInt(max - min + 1) + min;
        return String.valueOf(randomNumber);
    }

}