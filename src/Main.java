import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

    class Car{
        private String car_id;
        private String model;
        private String brand;
        private boolean isAvailable;
        private double basePrice;

        Car(String car_id,String model,String brand,double basePrice){
            this.car_id=car_id;
            this.model=model;
            this.brand=brand;
            isAvailable=true;
            this.basePrice=basePrice;
        }
        double rentPrice(int days){
            return basePrice*days;
        }
        public void rent(){
            isAvailable=false;
        }
        public void carReturn(){
            isAvailable=true;
        }
        public String getCar_id(){
            return car_id;
        }
        public String getModel(){
            return model;
        }
        public String getBrand(){
            return brand;
        }
        public boolean isAvailable(){
            return isAvailable;
        }


    }
    class Customer{
        private String customer_id;
        private String customer_name;

        Customer( String customer_id,String customer_name){
            this.customer_name=customer_name;
            this.customer_id=customer_id;
        }
        public String getCustomer_id(){
            return customer_id;
        }
        public String getCustomer_name(){
            return customer_name;
        }

    }

    class Rental{
       private Car car;
      private  Customer customer;
      private int days;
      Rental(Car car, Customer customer,int days){
          this.car=car;
          this.customer=customer;
          this.days=days;
      }
      public Car getCar(){
          return car;
      }
      public Customer getCustomer(){
          return customer;

      }



    }

    class RentalSystem{
        List<Car> cars;
        List<Customer>customers;
        List<Rental>rentals;

        RentalSystem(){
            cars=new ArrayList<>();
            customers=new ArrayList<>();
            rentals=new ArrayList<>();
        }
        void addCar(Car car){
            cars.add(car);
        }
        void addCustomer(Customer customer){
            customers.add(customer);
        }
        void rentCar(Car car,Customer customer,int days){
            if(car.isAvailable()){
                car.rent();
                rentals.add(new Rental(car,customer,days));
            }
            else{
                System.out.println("car is not available for rent");
            }
        }

        void returnCar(Car car){
            car.carReturn();
            Rental rentalToRemove=null;
            for(Rental rental:rentals){
                if(rental.getCar()==car){
                    rentalToRemove=rental;
                    break;
                }
            }
            if(rentalToRemove!=null){
                rentals.remove(rentalToRemove);
            }
            else{
                System.out.println("car is not rented");
            }
        }

        public void menu(){
            Scanner scanner= new Scanner(System.in);
            while(true){
                System.out.println("======Car Rental system======");
                System.out.println("1.Rent a Car");
                System.out.println("2.Return a Car");
                System.out.println("3.Exit");
                System.out.println("Enter your choice");
                int choice=scanner.nextInt();
                scanner.nextLine();
                if(choice==1){
                    System.out.println("\n ==rent a car==\n");
                    System.out.println("Enter your name");
                    String customerName=scanner.nextLine();
                    System.out.println("\nAvailable cars:");
                    for(Car car:cars){
                        if(car.isAvailable()){
                            System.out.println(car.getCar_id()+"-"+car.getBrand()+"-"+car.getModel());
                        }
                    }
                    System.out.println("Enter the car_id you want to rent");
                    String carId=scanner.nextLine();
                    System.out.println("Number of days you want to rent");
                    int days= scanner.nextInt();
                    scanner.nextLine();
                    Customer newCustomer= new Customer("CUS"+customers.size(),customerName);
                    addCustomer(newCustomer);
                    Car selectedCar=null;
                    for(Car car:cars){
                        if(car.getCar_id().equals(carId) && car.isAvailable()){
                            selectedCar=car;
                            break;
                        }

                    }
                    if(selectedCar!=null) {
                        double totalPrice = selectedCar.rentPrice(days);
                        System.out.println("\n== Rental Information ==\n");
                        System.out.println("Customer ID: " + newCustomer.getCustomer_id());
                        System.out.println("Customer Name: " + newCustomer.getCustomer_name());
                        System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                        System.out.println("Rental Days: " + days);
                        System.out.printf("Total Price: $%.2f%n", totalPrice);

                        System.out.print("\nConfirm rental (Y/N): ");
                        String confirm = scanner.nextLine();
                        if (confirm.equalsIgnoreCase("Y")) {
                            rentCar(selectedCar, newCustomer, days);
                            System.out.println("\nCar rented successfully.");
                        } else {
                            System.out.println("\nRental canceled.");
                        }
                    }
                    else{
                        System.out.println("\nInvalid car selection or car not available for rent.");
                    }
                }
                else if(choice==2){
                    System.out.println("\n== Return a Car ==\n");
                    System.out.print("Enter the car ID you want to return: ");
                    String carId = scanner.nextLine();

                    Car carToReturn = null;
                    for (Car car : cars) {
                        if (car.getCar_id().equals(carId) && !car.isAvailable()) {
                            carToReturn = car;
                            break;
                        }
                    }
                    if (carToReturn != null) {
                        Customer customer = null;
                        for (Rental rental : rentals) {
                            if (rental.getCar() == carToReturn) {
                                customer = rental.getCustomer();
                                break;
                            }
                        }
                        if (customer != null) {
                            returnCar(carToReturn);
                            System.out.println("Car returned successfully by " + customer.getCustomer_name());
                        } else {
                            System.out.println("Car was not rented or rental information is missing.");
                        }
                    } else {
                        System.out.println("Invalid car ID or car is not rented.");
                    }
                }

            else if (choice == 3) {
                break;
            }
            else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

            System.out.println("\nThank you for using the Car Rental System!");
    }
            }

    public class Main {
        public void main(String[] args) {
            RentalSystem carRentalSystem = new RentalSystem();

            Car car1 = new Car("C001", "Toyota", "Camry", 60.0); // Different base price per day for each car
            Car car2 = new Car("C002", "Honda", "Accord", 70.0);
            Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
            carRentalSystem.addCar(car1);
            carRentalSystem.addCar(car2);
            carRentalSystem.addCar(car3);
            carRentalSystem.menu();
        }
    }











