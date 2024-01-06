//Patrycja Marucińska, nr albumu:42746
//Jest to poprawiony Lab07, którego nie udało mi się zaliczyć
//Liczę na ponowne sprawdzenie i poprawę mojej oceny za to laboratorium
//Dodałam do wyniku programu także coś takiego jak ukazanie ceny dostania sie na parking dla kazdego obiektu
//z osobna
import java.text.SimpleDateFormat;
import java.util.*;
interface ParkingEntity {
    String identify();
}
class Dog implements ParkingEntity {
    @Override
    public String identify() {
        return "dog";
    }
}
class AnonymousPedestrian implements ParkingEntity {
    @Override
    public String identify() {
        return "anonymous pedestrian";
    }
}
class KnownPedestrian implements ParkingEntity {
    private final String Name;
    public KnownPedestrian(String Name) {
        this.Name = Name;
    }
    @Override
    public String identify() {
        return (Name.isEmpty()) ? "known pedestrian" : "known pedestrian " + Name;
    }
}
class AnonymousCyclist implements ParkingEntity {
    @Override
    public String identify() {
        return "anonymous cyclist";
    }
}
class KnownCyclist implements ParkingEntity {
    private final String Name;
    public KnownCyclist(String Name) {
        this.Name = Name;
    }
    @Override
    public String identify() {
        return (Name.isEmpty()) ? "known cyclist" : "known cyclist " + Name;
    }
}
class AnonymousScooterRider implements ParkingEntity {
    @Override
    public String identify() {
        return "anonymous scooter rider";
    }
}
class KnownScooterRider implements ParkingEntity {
    private final String Name;

    public KnownScooterRider(String Name) {
        this.Name = Name;
    }
    @Override
    public String identify() {
        return (Name.isEmpty()) ? "known scooter rider" : "known scooter rider " + Name;
    }
}
class Motorcycle implements ParkingEntity {
    String licensePlate;
    public Motorcycle(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    @Override
    public String identify() {
        return "motorcycle" + ((licensePlate.isEmpty()) ? "" : " with license plate " + licensePlate);
    }
}
class NamedMotorcycle implements ParkingEntity {
    private final String Name;
    private final String licensePlate;

    public NamedMotorcycle(String Name, String licensePlate) {
        this.Name = Name;
        this.licensePlate = licensePlate;
    }
    @Override
    public String identify() {
        return "motorcycle" + ((Name.isEmpty()) ? "" : " " + Name) + ((licensePlate.isEmpty()) ? "" :
                " with license plate " + licensePlate);
    }
}
class Car implements ParkingEntity {
    String licensePlate;
    private final String brand;
    private final String color;
    public Car(String licensePlate, String brand, String color) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.color = color;
    }
    @Override
    public String identify() {
        return "car" + ((licensePlate.isEmpty()) ? "" : " with license plate " + licensePlate)
                + ((brand.isEmpty()) ? "" : " of brand " + brand) + ((color.isEmpty()) ? "" : " of color "
                + color);
    }
}
class NamedCar implements ParkingEntity {
    private final String Name;
    private final String licensePlate;
    String brand;
    private final String color;
    public NamedCar(String Name, String licensePlate, String brand, String color) {
        this.Name = Name;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.color = color;
    }
    @Override
    public String identify() {
        return "car" + ((Name.isEmpty()) ? "" : " " + Name) + ((licensePlate.isEmpty()) ? "" : " with license plate "
                + licensePlate) + ((brand.isEmpty()) ? "" : " of brand " + brand) + ((color.isEmpty()) ? "" :
                " of color " + color);
    }
}
class EmployeeCar extends NamedCar {
    public EmployeeCar(String Name, String licensePlate, String brand, String color) {
        super(Name, licensePlate, brand, color);
    }
}
class Ambulance implements ParkingEntity {
    private final String licensePlate;
    public Ambulance(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    @Override
    public String identify() {
        return "ambulance" + ((licensePlate.isEmpty()) ? "" : " with license plate " + licensePlate);
    }
}
class DeliveryTruck implements ParkingEntity {
    private final String companyName;
    private final String licensePlate;
    public DeliveryTruck(String companyName, String licensePlate) {
        this.companyName = companyName;
        this.licensePlate = licensePlate;
    }
    @Override
    public String identify() {
        return "delivery truck of " + companyName + ((licensePlate.isEmpty()) ? "" : " with license plate "
                + licensePlate);
    }
}
class Tank implements ParkingEntity {
    private final String licensePlate;
    public Tank(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    @Override
    public String identify() {
        return "tank" + ((licensePlate.isEmpty()) ? "" : " with license plate " + licensePlate);
    }
}
@SuppressWarnings("ALL")
class ParkingLot {
    protected int MAX_AVAILABLE_SPACES_CAR,MAX_AVAILABLE_SPACES_BIKE,MAX_AVAILABLE_SPACES_MOTORCYCLE,
            occupiedSpacesCar = 0,occupiedSpacesBike = 0,occupiedSpacesMotorcycle = 0,entrancesCount = 0,
            carSpacesOccupied = 0,bikeSpacesOccupied = 0,motorcycleSpacesOccupied = 0,carsReturned = 0,
            motorcyclesReturned = 0, bicyclesReturned = 0, blacklistedCarsEntranceAttempted = 0;
    protected Set<String> blackListedLicensePlates;
    protected double totalMoneyCollected = 0.0;
    public ParkingLot(int maxCarSpaces, int maxBikeSpaces, int maxMotorcycleSpaces, Set<String>
            blackListedLicensePlates) {
        MAX_AVAILABLE_SPACES_CAR = maxCarSpaces;
        MAX_AVAILABLE_SPACES_BIKE = maxBikeSpaces;
        MAX_AVAILABLE_SPACES_MOTORCYCLE = maxMotorcycleSpaces;
        this.blackListedLicensePlates = blackListedLicensePlates;
    }
    public void letIn(ParkingEntity entity) {
        String name = entity.identify();
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        entrancesCount++;
        if (entity instanceof Dog || entity instanceof Tank) {
            System.out.println(name + " turned away at " + now);
            return;
        }
        if (entity instanceof Motorcycle && occupiedSpacesMotorcycle >= MAX_AVAILABLE_SPACES_MOTORCYCLE) {
            System.out.println(name + " returned at " + now);
            motorcyclesReturned++;
            return;
        }
        if (entity instanceof Car && blackListedLicensePlates.contains(getLicensePlate(entity)) &&
                !(entity instanceof Ambulance)) {
            System.out.println(name + " turned away at " + now);
            blacklistedCarsEntranceAttempted++;
            return;
        }
        if ((entity instanceof Car && occupiedSpacesCar >= MAX_AVAILABLE_SPACES_CAR)
                || (entity instanceof NamedCar && ((NamedCar) entity).brand.isEmpty())
                || (entity instanceof EmployeeCar && ((EmployeeCar) entity).brand.isEmpty())
                || (entity instanceof Motorcycle && occupiedSpacesMotorcycle >= MAX_AVAILABLE_SPACES_MOTORCYCLE)
                || (entity instanceof AnonymousCyclist && occupiedSpacesBike >= MAX_AVAILABLE_SPACES_BIKE)
                || (entity instanceof KnownCyclist && occupiedSpacesBike >= MAX_AVAILABLE_SPACES_BIKE)
        ) {
            System.out.println(name + " returned at " + now);
            if (entity instanceof Car) {
                carsReturned++;
            } else if (entity instanceof Motorcycle) {
                motorcyclesReturned++;
            } else if (entity instanceof AnonymousCyclist || entity instanceof KnownCyclist) {
                bicyclesReturned++;
            }
            return;
        }
        double entranceFee = calculateEntranceFee(entity);
        System.out.println(name + " entering at " + now + " - Entrance fee: " + entranceFee + "zł");
        totalMoneyCollected += entranceFee;
        if (entity instanceof Car) {
            occupiedSpacesCar++;
            carSpacesOccupied++;
        } else if (entity instanceof AnonymousCyclist || entity instanceof KnownCyclist) {
            occupiedSpacesBike++;
            bikeSpacesOccupied++;
        } else if (entity instanceof Motorcycle) {
            occupiedSpacesMotorcycle++;
            motorcycleSpacesOccupied++;
        }
    }
    private double calculateEntranceFee(ParkingEntity entity) {
        if (entity instanceof Motorcycle) {
            return 2.0; // koszt wjazdu dla motocykli: 2zł
        } else if (entity instanceof Car || entity instanceof NamedCar || entity instanceof EmployeeCar) {
            return 5.0; // koszt wjazdu dla samochodów: 5zł
        } else if (entity instanceof Ambulance || entity instanceof DeliveryTruck) {
            return 0.0; // koszt wjazdu dla ambulansów i pojazdów dostawczych: 0zł
        }
        return 0.0; // domyślna cena dostania się na parking
    }
    private String getLicensePlate(ParkingEntity entity) {
        if (entity instanceof Car) {
            return ((Car) entity).licensePlate;
        } else if (entity instanceof Motorcycle) {
            return ((Motorcycle) entity).licensePlate;
        }
        return "";
    }
    public void printParkingStatus() {
        System.out.println("\nMoney collected: " + totalMoneyCollected + "zł");
        System.out.println("Entrances count: " + entrancesCount);
        System.out.println("\nCar spaces occupied: " + carSpacesOccupied + "/" + MAX_AVAILABLE_SPACES_CAR +
                " (" + calculateOccupancyPercentage(occupiedSpacesCar, MAX_AVAILABLE_SPACES_CAR) + "%)");
        System.out.println("Motorcycle spaces occupied: " + motorcycleSpacesOccupied + "/" +
                MAX_AVAILABLE_SPACES_MOTORCYCLE + " (" + calculateOccupancyPercentage(occupiedSpacesMotorcycle,
                MAX_AVAILABLE_SPACES_MOTORCYCLE) + "%)");
        System.out.println("Bicycle spaces occupied: " + bikeSpacesOccupied + "/" + MAX_AVAILABLE_SPACES_BIKE +
                " (" + calculateOccupancyPercentage(occupiedSpacesBike, MAX_AVAILABLE_SPACES_BIKE) + "%)\n");
        System.out.println("Cars returned: " + carsReturned);
        System.out.println("Motorcycles returned: " + motorcyclesReturned);
        System.out.println("Bicycles returned: " + bicyclesReturned + "\n");
        System.out.println("Blacklisted cars entrance attempted: " + blacklistedCarsEntranceAttempted);
    }
    private double calculateOccupancyPercentage(int occupiedSpaces, int maxSpaces) {
        return (double) occupiedSpaces / maxSpaces * 100;
    }
}
public class Parking {
    public static void main(String[] args) {
        Collection<ParkingEntity> entities = generateParkingEntities();
        Set<String> blackListedLicensePlates = new HashSet<>(List.of("DLU00005"));
        ParkingLot parkingLot = new ParkingLot(20, 10, 10,
                blackListedLicensePlates);
        for (ParkingEntity entity : entities) {
            parkingLot.letIn(entity);
        }
        parkingLot.printParkingStatus();
    }
    private static Collection<ParkingEntity> generateParkingEntities() {
        Collection<ParkingEntity> entities = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            int entityType = random.nextInt(20);
            switch (entityType) {
                case 0 -> entities.add(new Dog());
                case 1 -> entities.add(new AnonymousPedestrian());
                case 2 -> entities.add(new KnownPedestrian("Lucas Graham"));
                case 3 -> entities.add(new KnownPedestrian("Brittany Skirt"));
                case 4 -> entities.add(new AnonymousCyclist());
                case 5 -> entities.add(new KnownCyclist("Susan Brake"));
                case 6 -> entities.add(new KnownCyclist("Nicolas Smith"));
                case 7 -> entities.add(new AnonymousScooterRider());
                case 8 -> entities.add(new KnownScooterRider("Katie Blue"));
                case 9 -> entities.add(new KnownScooterRider("Richard Flu"));
                case 10 -> entities.add(new Motorcycle("DLU" + random.nextInt(100000)));
                case 11 -> entities.add(new NamedMotorcycle("Mike Skye", "DLU" + random.nextInt
                        (100000)));
                case 12 -> entities.add(new Car("DLU" + random.nextInt(100000),
                        "Audi", "black"));
                case 13 -> entities.add(new Car("DLU" + random.nextInt(100000),
                        "Mercedes", "silver"));
                case 14 -> entities.add(new NamedCar("Johnny Moore", "DLU" + random.nextInt
                        (100000), "Volkswagen Passat", "red"));
                case 15 -> entities.add(new EmployeeCar("Bernard Twitch", "DLU" + random.nextInt
                        (100000), "Ford", "blue"));
                case 16 -> entities.add(new EmployeeCar("Amy May", "DLU" + random.nextInt
                        (100000), "Opel", "black"));
                case 17 -> entities.add(new Ambulance("DLU" + random.nextInt(100000)));
                case 18 -> entities.add(new DeliveryTruck("DHL", "DLU" + random.nextInt
                        (100000)));
                case 19 -> entities.add(new Tank("DLU" + random.nextInt(100000)));
                default -> {
                }
            }
        }
        return entities;
    }
}
