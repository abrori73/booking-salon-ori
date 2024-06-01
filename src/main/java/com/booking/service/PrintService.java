package com.booking.service;

import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;


public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public void showRecentReservation(List<Reservation> reservationList){
        int num = 1;
        System.out.printf("| %-4s | %-8s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-8s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
    }

    public void showAllCustomer(List<Person> memberList){
        int num = 1;
        System.out.printf("| %-4s | %-8s | %-11s | %-15s | %-15s | %-15s\n",
                "No.", "ID", "Nama", "Alamat", "Membership", "Uang");
        System.out.println("+========================================================================================+");
        for (Person person : memberList) {
            if (person.getId().contains("Cust")) {
                System.out.printf("| %-4s | %-8s | %-11s | %-15s |", num, person.getId(), person.getName(), person.getAddress());
                    if (person instanceof Customer) {
                        Customer cust = (Customer) person;
                        System.out.printf(" %-15s | %-15s |\n", cust.getMember().getMembershipName(), cust.getWallet());
                        num++;
                    }
            } 
        }
    }

    public void showAllEmployee(List<Person> employeeList){
        int num = 1;
        System.out.printf("| %-4s | %-8s | %-11s | %-15s | %-15s\n",
                "No.", "ID", "Nama", "Alamat", "Pengalaman");
        System.out.println("+===============================================================+");
        for (Person person : employeeList) {
            if (person.getId().contains("Emp")) {
                System.out.printf("| %-4s | %-8s | %-11s | %-15s |", num, person.getId(), person.getName(), person.getAddress());
                    if (person instanceof Employee) {
                        Employee employee = (Employee) person;
                        System.out.printf(" %-15s |\n", employee.getExperience());
                        num++;
                    }
            } 
        }
    }
    public void showAllService(List<Service> serviceList) {
        int num = 1;
        System.out.printf("| %-4s | %-8s | %-16s | %-15s\n",
                "No.", "ID", "Nama", "Harga");
        System.out.println("+========================================================================================+");
        for (Service service : serviceList) {
                System.out.printf("| %-4s | %-8s | %-16s | %-15s |\n", num, service.getServiceId(), service.getServiceName(), service.getPrice());
                num++;
        } 
    }
    
    public void showHistoryReservation(List<Reservation> reservationList){
        int num = 1;
        float totalkeuntungan = 0;
        System.out.printf("| %-4s | %-8s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            System.out.printf("| %-4s | %-8s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
            num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
            num++;
            if (reservation.getWorkstage().equals("Finish")) {
                totalkeuntungan += reservation.getReservationPrice();
            }
        }
        System.out.println("+========================================================================================+");
        System.out.printf("| %-34s | %-17s |\n", "Total Keuntungan", "Rp. " + totalkeuntungan);
    }
}
