package com.booking.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    //   workStage (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services,
            String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.reservationPrice = calculateReservationPrice();
        this.workstage = workstage;
    };

    private double calculateReservationPrice(){
        double totalPrice = 0;
        double totalPriceAfter = 0;
        double discount = 0;

        for (Service service : services) {
            totalPrice += service.getPrice();
        }
        if (customer.getMember().getMembershipName().equals("Silver")) {
            discount = totalPrice * 0.05;
        }
        if (customer.getMember().getMembershipName().equals("Gold")) {
            discount = totalPrice * 0.1;
        }
        totalPriceAfter = totalPrice - discount;
        return totalPriceAfter;
    }
}
