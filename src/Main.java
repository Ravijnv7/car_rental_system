import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    class Car{
        private int car_id;
        private String model;
        private String brand;
        private boolean isAvailable;
        private double basePrice;

        Car(int car_id,String model,String brand,double basePrice){
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
        public int getCar_id(){
            return car_id;
        }


    }
    class Customer{
        private int customer_id;
        private String customer_name;

        Customer(int customer_id,String customer_name){
            this.customer_name=customer_name;
            this.customer_id=customer_id;
        }
        public int getCustomer_id(){
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
            if(car.isAvailable){
                car.rent();
                rentals.add(new Rental(car,customer,days));
            }
            else{
                System.out.println("car is not available for rent");
            }
        }



    }


}