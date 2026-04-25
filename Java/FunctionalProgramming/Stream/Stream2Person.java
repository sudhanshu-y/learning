import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Person {

    private String name;
    private int age;
    private String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCity() { return city; }

    @Override
    public String toString() {
        return String.format("Person{name='%s', age=%d, city='%s'}", name, age, city);
    }
}

public class Stream2Person {

    public static void main(String[] args) {

        List<Person> personList = Arrays.asList(
                new Person("Alice", 23, "New York"),
                new Person("Bob", 30, "London"),
                new Person("Charlie", 28, "New York"),
                new Person("Diana", 35, "Paris"),
                new Person("Charmi", 35, "Mumbai")
        );

        // Filter: People from New York
        List<Person> newYorkResidents = personList.stream()
                .filter(person -> person.getCity().equals("New York"))
                .collect(Collectors.toList());

        System.out.println("People from New York: " + newYorkResidents);

        /*
        People from New York:
        [Person{name='Alice', age=23, city='New York'},
         Person{name='Charlie', age=28, city='New York'}]
        */


        // Extract all names and cities
        List<String> nameList = personList.stream()
                .map(Person::getName)
                .toList();

        List<String> cityList = personList.stream()
                .map(Person::getCity)
                .toList();

        System.out.println("All Names: " + nameList);
        System.out.println("All Cities: " + cityList);

        /*
        All Names: [Alice, Bob, Charlie, Diana, Charmi]
        All Cities: [New York, London, New York, Paris, Mumbai]
        */


        // Names from New York
        List<String> newYorkNameList = personList.stream()
                .filter(person -> person.getCity().equals("New York"))
                .map(Person::getName)
                .toList();

        System.out.println("Names from New York: " + newYorkNameList);

        /*
        Names from New York: [Alice, Charlie]
        */


        // Sort by age (ascending)
        List<Person> sortedByAgeList = personList.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .toList();

        List<Person> sortedByAgeListAlt = personList.stream()
                .sorted((p1, p2) -> p1.getAge() - p2.getAge())
                .toList();

        System.out.println("People sorted by age: " + sortedByAgeList);
        System.out.println("People sorted by age: " + sortedByAgeListAlt);

        /*
        People sorted by age:
        [Person{name='Alice', age=23, city='New York'},
         Person{name='Charlie', age=28, city='New York'},
         Person{name='Bob', age=30, city='London'},
         Person{name='Diana', age=35, city='Paris'},
         Person{name='Charmi', age=35, city='Mumbai'}]
        */


        // Sort by age (descending)
        List<Person> sortedByAgeDescList = personList.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .toList();

        List<Person> sortedByAgeDescListAlt = personList.stream()
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .toList();

        System.out.println("People sorted by age (desc): " + sortedByAgeDescList);
        System.out.println("People sorted by age (desc): " + sortedByAgeDescListAlt);

        /*
        People sorted by age (desc):
        [Person{name='Diana', age=35, city='Paris'},
         Person{name='Charmi', age=35, city='Mumbai'},
         Person{name='Bob', age=30, city='London'},
         Person{name='Charlie', age=28, city='New York'},
         Person{name='Alice', age=23, city='New York'}]
        */


        // Sort by age then name (ASC, ASC)
        List<Person> sortedByAgeThenNameList = personList.stream()
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getName))
                .toList();

        List<Person> sortedByAgeThenNameListAlt = personList.stream()
                .sorted((p1, p2) -> {
                    if (p1.getAge() != p2.getAge())
                        return p1.getAge() - p2.getAge();
                    return p1.getName().compareTo(p2.getName());
                })
                .toList();

        System.out.println("People sorted by age then name: " + sortedByAgeThenNameList);
        System.out.println("People sorted by age then name: " + sortedByAgeThenNameListAlt);

        /*
        People sorted by age then name:
        [Person{name='Alice', age=23, city='New York'},
         Person{name='Charlie', age=28, city='New York'},
         Person{name='Bob', age=30, city='London'},
         Person{name='Charmi', age=35, city='Mumbai'},
         Person{name='Diana', age=35, city='Paris'}]
        */


        // Sort by age ASC then name DESC
        List<Person> sortedByAgeAscNameDescList = personList.stream()
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getName, Collections.reverseOrder()))
                .toList();

        List<Person> sortedByAgeAscNameDescListAlt = personList.stream()
                .sorted((p1, p2) -> {
                    if (p1.getAge() != p2.getAge())
                        return p1.getAge() - p2.getAge();
                    return p2.getName().compareTo(p1.getName());
                })
                .toList();

        System.out.println("People sorted by age ascending then name descending: "
                + sortedByAgeAscNameDescList);
        System.out.println("People sorted by age ascending then name descending: "
                + sortedByAgeAscNameDescListAlt);

        /*
        People sorted by age ASC then name DESC:
        [Person{name='Alice', age=23, city='New York'},
         Person{name='Charlie', age=28, city='New York'},
         Person{name='Bob', age=30, city='London'},
         Person{name='Diana', age=35, city='Paris'},
         Person{name='Charmi', age=35, city='Mumbai'}]
        */


        // Sorted names (age then name)
        List<String> sortedNameList = personList.stream()
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getName))
                .map(Person::getName)
                .toList();

        System.out.println("Sorted names by age then name: " + sortedNameList);

        /*
        Sorted names by age then name:
        [Alice, Charlie, Bob, Charmi, Diana]
        */


        // Group people by city
        Map<String, List<Person>> personsGroupedByCity =
                personList.stream()
                        .collect(Collectors.groupingBy(Person::getCity));

        personsGroupedByCity.forEach((city, residents) ->
                System.out.println(city + " -> " + residents));

        /*
        New York -> [Alice, Charlie]
        London -> [Bob]
        Paris -> [Diana]
        Mumbai -> [Charmi]
        */


        //Group names by city
        Map<String, List<String>> namesGroupedByCity =
                personList.stream()
                        .collect(Collectors.groupingBy(
                                Person::getCity,
                                Collectors.mapping(Person::getName, Collectors.toList())
                        ));

        namesGroupedByCity.forEach((city, names) ->
                System.out.println(city + " -> " + names));

        /*
        New York -> [Alice, Charlie]
        London -> [Bob]
        Paris -> [Diana]
        Mumbai -> [Charmi]
        */


        // Count people by city
        Map<String, Long> personCountByCity =
                personList.stream()
                        .collect(Collectors.groupingBy(
                                Person::getCity,
                                Collectors.counting()
                        ));

        personCountByCity.forEach((city, count) ->
                System.out.println(city + " -> " + count));

        /*
        New York -> 2
        London -> 1
        Paris -> 1
        Mumbai -> 1
        */


        // Multi-level grouping (city -> age -> people)
        Map<String, Map<Integer, List<Person>>> personsGroupedByCityAndAge =
                personList.stream()
                        .collect(Collectors.groupingBy(
                                Person::getCity,
                                Collectors.groupingBy(Person::getAge)
                        ));

        personsGroupedByCityAndAge.forEach((city, ageMap) -> {
            System.out.println(city + " -> ");
            ageMap.forEach((age, residents) ->
                    System.out.println("  " + age + " -> " + residents));
        });

        /*
        Example:
        New York ->
           23 -> [Alice]
           28 -> [Charlie]
        */


        // Function.identity()
        Map<String, Long> nameCountMap = personList.stream()
                .map(Person::getName)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println("Function.identity(): " + nameCountMap);

        /*
        Function.identity(): {Alice=1, Bob=1, Charlie=1, Diana=1, Charmi=1}
        */


        // Distinct cities
        List<String> distinctCityList = personList.stream()
                .map(Person::getCity)
                .distinct()
                .toList();

        System.out.println("Distinct cities: " + distinctCityList);

        /*
        Distinct cities: [New York, London, Paris, Mumbai]
        */
    }
}
