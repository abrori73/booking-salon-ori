package com.booking.service;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;

public class ReservationService {
    public static Reservation createReservation(String custID, String empID, List<Service> servID){
        int num = 1;
        String reservationID = "Rsv-" + num;
        String workstage = "In Process";
        Customer customer = getCustomerByCustomerId(custID);
        Employee employee = getEmployeeByEmployeeId(empID);
        Reservation reserve = new Reservation(reservationID,customer,employee,servID,workstage);
        num++;
        return reserve;
    }

    public static Customer getCustomerByCustomerId(String custID){
        List<Person> persons = PersonRepository.getAllPerson();
        for (Person person : persons) {
            if (person instanceof Customer && person.getId().equals(custID)) {
                return (Customer) person;
            }  
        }
        return null;
    }
    public static Employee getEmployeeByEmployeeId(String empID){
        List<Person> persons = PersonRepository.getAllPerson();
        for (Person person : persons) {
            if (person instanceof Employee && person.getId().equals(empID)) {
                return (Employee) person;
            }
        }
        return null;
    }
    public static void editReservationWorkstage(List<Reservation> reservationList){
        Scanner input = new Scanner(System.in);
        System.out.println("Silahkan masukkan reservation Id:");
        String id = input.nextLine();
        System.out.println("Selesaikan reservasi:");
        String workstage = input.nextLine();

        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId().equals(id)) {
                reservation.setWorkstage(workstage);
            }
        }
        System.out.println("Reservation dengan id " + id + " sudah " + workstage);
    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
