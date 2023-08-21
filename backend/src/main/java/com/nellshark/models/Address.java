package com.nellshark.models;

public record Address(long id,
                      String address,
                      String city,
                      String country,
                      long zipcode) {

}
