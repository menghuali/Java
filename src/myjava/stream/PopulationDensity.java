package myjava.stream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.DoubleSummaryStatistics;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

public class PopulationDensity {

    public static void main(String[] args) {
        ToDoubleFunction<String> computeDensity = line -> {
            String[] data = line.split(",");
            double population = Double.valueOf(data[3]);
            double landArea = Double.valueOf(data[4]);
            return population / landArea;
        };

        try (Stream<String> lines = Files.lines(Path.of("src/myjava/stream/us_cities.csv"))) {
            double max = lines.skip(1).mapToDouble(computeDensity).max().orElseThrow();
            System.out.println(max);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Stream<String> lines = Files.lines(Path.of("src/myjava/stream/us_cities.csv"))) {
            DoubleSummaryStatistics summary = lines.skip(1).mapToDouble(computeDensity).summaryStatistics();
            System.out.println(summary);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
