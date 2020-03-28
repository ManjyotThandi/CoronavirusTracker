package com.learning.java.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.learning.java.models.LocationStats;
import com.learning.java.repository.LocationStatsRepository;

// This will tell spring to load up this class and create an instance when the application starts
@Service
public class CVirusService {
	

	@Autowired 
	LocationStatsRepository locationStatsRepository;

	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	

	// Post construct tells spring on class load run this method
	@PostConstruct
	// scheduled to run this method everyday (in case application stays on)
	@Scheduled(cron = "* * 1 * * *")
	public void fetchData() throws IOException, InterruptedException {
		// This will delete whatever is in the table to avoid duplicate rows.
		locationStatsRepository.deleteAll();
		// create the client
		HttpClient client = HttpClient.newHttpClient();
		// set up the request
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		// Get the response back as a String
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new StringReader(response.body());
//		client.send(request, HttpResponse.BodyHandlers.ofString());
//		.thenApply(HttpResponse::body)
//        .thenAccept(System.out::println);

		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			// make a new instance of location stats, and populate it for each record in
			// records
			LocationStats locationStats = new LocationStats();
			String province = record.get("Province/State");
			locationStats.setState(province);
			String country = record.get("Country/Region");
			locationStats.setCountry(country);
			int totalNumberToDate = Integer.parseInt(record.get(record.size() - 1));
			int totalNumberFromPreviousDay = Integer.parseInt(record.get(record.size() - 2));
			locationStats.setTotalNumber(totalNumberToDate);
			locationStats.setDiffFromPreviousDay((totalNumberToDate - totalNumberFromPreviousDay)); 
			locationStatsRepository.save(locationStats);
		}
	}

}
