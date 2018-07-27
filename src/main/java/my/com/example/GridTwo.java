package my.com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;

@HtmlImport("styles/shared-styles.html")
public class GridTwo extends Grid<Person> {
    private static final Logger LOG = LoggerFactory.getLogger(GridTwo.class);
    private List<Person> items = new ArrayList<>();
    
    public GridTwo() {
        items = createItems();
        initGrid();
    }
    
    private void initGrid() {
        setItems(getItems());
        
        addColumn(Person::getName).setHeader("Name");
        addColumn(Person::getAge).setHeader("Age");
        addColumn(Person::getAddress).setHeader("Age");
        
        setSelectionMode(SelectionMode.MULTI);
        addClassName("person-grid");
    }
    
    private List<Person> getItems() {
        return items;
    }
    
    private List<Person> createItems() {
        return createItems(10);
    }
    
    private List<Person> createItems(int number) {
        Random random = new Random(0);
        
        return IntStream.range(1, number).mapToObj(index -> createPerson(index, random)).collect(Collectors.toList());
    }
    
    private static Person createPerson(int index, Random random) {
        return createPerson(Person::new, index, index, random);
    }
    
    private static <T extends Person> T createPerson(Supplier<T> constructor, int index, int id, Random random) {
        T person = constructor.get();
        person.setId(id);
        person.setName("Flow " + index);
        person.setAge(10 + random.nextInt(50));
        
        Address address = new Address();
        address.setStreet("Street " + ((char) ('B' + random.nextInt(26))));
        address.setNumber(2 + random.nextInt(50));
        address.setPostalCode(String.valueOf(10000 + random.nextInt(8999)));
        person.setAddress(address);
        
        return person;
    }
    
}
