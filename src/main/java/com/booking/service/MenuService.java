package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

public class MenuService {
    private static List<Person> personList = PersonRepository.getAllPerson();
    private static List<Service> serviceList = ServiceRepository.getAllService();
    private static List<Reservation> reservationList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void mainMenu() {
        String[] mainMenuArr = {"Show Data", "Create Reservation", "Complete/cancel reservation", "Exit"};
        String[] subMenuArr = {"Recent Reservation", "Show Customer", "Show Available Employee", "Tampilkan History Reservation + Total Keuntungan","Back to main menu"};
        
        PrintService print = new PrintService();
        boolean adaCustId = false, adaEmpId = false;
        String empID = "",custID = "";
        int optionMainMenu;
        int optionSubMenu;

		boolean backToMainMenu = false;
        boolean backToSubMenu = false;
        do {
            PrintService.printMenu("Main Menu", mainMenuArr);
            optionMainMenu = Integer.valueOf(input.nextLine());
            switch (optionMainMenu) {
                case 1:
                    do {
                        PrintService.printMenu("Show Data", subMenuArr);
                        optionSubMenu = Integer.valueOf(input.nextLine());
                        // Sub menu - menu 1
                        switch (optionSubMenu) {
                            case 1:
                                print.showRecentReservation(reservationList);
                                break;
                            case 2:
                                print.showAllCustomer(personList);
                                break;
                            case 3:
                                print.showAllEmployee(personList);
                                break;
                            case 4:
                                print.showHistoryReservation(reservationList);
                                break;
                            case 0:
                                backToSubMenu = true;
                        }
                    } while (!backToSubMenu);
                    break;
                case 2:
                    print.showAllCustomer(personList);
                    do {
                        System.out.println("Silahkan Masukkan Customer Id:");
                        custID = input.nextLine();
                            for (Person customer : personList) {
                                if (customer.getId().equals(custID)) {
                                    adaCustId = true;
                                    break;
                                }
                            }
                            if (!adaCustId) {
                                System.out.println("Customer yang dicari tidak tersedia");
                            }
                    }while (!adaCustId);
                    print.showAllEmployee(personList);
                    do {
                        System.out.println("Silahkan Masukkan Employee Id:");
                        empID = input.nextLine();
                        for (Person employee : personList) {
                            if (employee.getId().equals(empID)) {
                                adaEmpId = true;
                                break;
                            }
                        }
                        if (!adaEmpId) {
                            System.out.println("Employee yang dicari tidak tersedia");
                        }
                    }while (!adaEmpId);
                    String choose = "Y";
                    ArrayList<Service> servID = new ArrayList<>();
                    while (choose.equalsIgnoreCase("Y") && !choose.equalsIgnoreCase("T")) { 
                        print.showAllService(serviceList);
                        System.out.println("Silahkan Masukkan Service Id:");
                        String serviceId = input.nextLine();
                        for (Service serv : serviceList ){
                            if (serv.getServiceId().equals(serviceId)) {
                                servID.add(serv);
                            }
                        }
                        System.out.println("Ingin pilih service yang lain (Y/T)?");
                        choose = input.nextLine();
                    }
                    Reservation reserve = ReservationService.createReservation(custID, empID, servID);
                    reservationList.add(reserve);
                    System.out.println("Booking berhasil!");
                    System.out.printf("Total Biaya Booking : Rp. ");
                    break;
                case 3:
                    print.showRecentReservation(reservationList);
                    ReservationService.editReservationWorkstage(reservationList);
                    break;
                case 0:
                    backToMainMenu = false;
                    break;
            }
        } while (!backToMainMenu);
		
	}
}
