package myjava.stream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectStream {

    public static void main(String[] args) {
        Function<String, String> toName = line -> line.split(",")[1];
        Set<String> cities = null;
        try (Stream<String> lines = Files.lines(Path.of("src/myjava/stream/us_cities.csv"))) {
            cities = lines.skip(1).map(toName).collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> citiesWithA = cities.stream().filter(city -> city.startsWith("A")).collect(Collectors.toList());
        System.out.println(citiesWithA);

        String[] citiesWithA2 = cities.stream().filter(city -> city.startsWith("A")).toArray(String[]::new);
        for (String city : citiesWithA2) {
            System.out.print(city + ", ");
        }
        System.out.println();

        String cityWithANames = cities.stream().filter(city -> city.startsWith("A")).collect(Collectors.joining(", "));
        System.out.println(cityWithANames);

        System.out.println(
                cities.stream().filter(city -> city.startsWith("A")).collect(Collectors.joining(", ", "[", "]")));
    }
}
