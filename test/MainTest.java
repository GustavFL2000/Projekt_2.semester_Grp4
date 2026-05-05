import controller.Controller;
import model.Destillering;
import model.Kornsort;
import model.Maltbatch;
import storage.IStorage;
import storage.Storage;

import java.time.LocalDate;

public class MainTest {
    public static void main(String[] args) {
        IStorage storage = new Storage();
        Controller controller = new Controller(storage);
        Maltbatch maltbatch1 = new Maltbatch(1, Kornsort.EVERGREEN);
        Maltbatch maltbatch2 = new Maltbatch(1, Kornsort.STAIRWAY);

        controller.createDestillering(1,LocalDate.of(2020,11,4),LocalDate.of(2023,11,5), 70, false,"Ny november batch", 1000, maltbatch1);
        controller.createDestillering(2,LocalDate.of(2020,1,1),LocalDate.of(2023,1,2), 70, false,"Nyårs bryg", 1000, maltbatch1);
        controller.createDestillering(3,LocalDate.of(2020,7,4),LocalDate.of(2023,7,5), 70, false,"Fyrværkri med goffeluffe whiskey", 1000, maltbatch2);

        System.out.println(controller.getDestilleringer());

    }
}
