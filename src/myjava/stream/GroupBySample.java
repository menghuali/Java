package myjava.stream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupBySample {

    private static class City {

        private String name;
        private String state;
        private int population;
        private double area;

        public City() {
        }

        @Override
        public String toString() {
            return "[name=" + name + ", state=" + state + ", population=" + population + ", area=" + area + "]";
        }

    }

    public static void main(String[] args) {
        Function<String, City> lineToCity = (line) -> {
            String[] split = line.split(",");
            City city = new City();
            city.name = split[1];
            city.state = split[2];
            city.population = Integer.parseInt(split[3]);
            city.area = Double.parseDouble(split[4]);
            return city;
        };

        Set<City> cities = null;
        try (Stream<String> lines = Files.lines(Path.of("src/myjava/stream/us_cities.csv"))) {
            cities = lines.skip(1).map(lineToCity).collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(cities);
        System.out.println(cities.size());
        Map<String, List<City>> citiesPerState = cities.stream().collect(Collectors.groupingBy(city -> city.state));
        System.out.println(citiesPerState);

        // Count cities by state
        System.out.println(cities.stream().collect(Collectors.groupingBy(city -> city.state, Collectors.counting())));
        // Get the state with max citites
        System.out.println(cities.stream().collect(Collectors.groupingBy(city -> city.state, Collectors.counting()))
                .entrySet().stream().max((a, b) -> Long.compare(a.getValue(), b.getValue())).orElseThrow());
        System.out.println(cities.stream().collect(Collectors.groupingBy(city -> city.state, Collectors.counting()))
                .entrySet().stream().max(Comparator.comparing(Entry::getValue)).orElseThrow());
        System.out.println(cities.stream().collect(Collectors.groupingBy(city -> city.state, Collectors.counting()))
                .entrySet().stream().max(Entry.comparingByValue()).orElseThrow());

        // Sum the state population by state
        System.out.println(cities.stream().collect(
                Collectors.groupingBy(city -> city.state, Collectors.summingInt(city -> city.population))));
        System.out.println(cities.stream().collect(
                Collectors.groupingBy(city -> city.state, Collectors.summarizingInt(city -> city.population))));
        // Get the state with max population
        System.out.println(cities.stream().collect(
                Collectors.groupingBy(city -> city.state, Collectors.summingInt(city -> city.population))).entrySet()
                .stream().max(Entry.comparingByValue()));
    }

}
