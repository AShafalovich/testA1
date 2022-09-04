package com.ashafalovich.testApplication.service;

import com.ashafalovich.testApplication.model.Login;
import com.ashafalovich.testApplication.model.Posting;
import com.ashafalovich.testApplication.repository.LoginRepository;
import com.ashafalovich.testApplication.repository.PostingRepository;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DeliveryService {

    private final LoginRepository loginRepository;
    private final PostingRepository postingRepository;

    public DeliveryService(LoginRepository loginRepository, PostingRepository postingRepository) {
        this.loginRepository = loginRepository;
        this.postingRepository = postingRepository;
    }

    public void parseFile() throws IOException {
        loginRepository.deleteAll();
        postingRepository.deleteAll();
        parseLogins();
        parsePostings();
    }

    public List<Posting> getPostingsByParam(String startDate, String endDate, String isActive) {
        if (!startDate.equals("") & endDate.equals("")) {
            if (isActive.equals("true") || isActive.equals("false")) {
                return postingRepository.findAllByPstngDateAfterAndAuthorizedDelivery(LocalDate.parse(startDate),
                        Boolean.parseBoolean(isActive));
            } else {
                return postingRepository.findAllByPstngDateAfter(LocalDate.parse(startDate));
            }
        }
        if (!endDate.equals("") & startDate.equals("")) {
            if (isActive.equals("true") || isActive.equals("false")) {
                return postingRepository.findAllByPstngDateBeforeAndAuthorizedDelivery(
                        LocalDate.parse(endDate),
                        Boolean.parseBoolean(isActive));
            } else {
                return postingRepository.findAllByPstngDateBefore(LocalDate.parse(endDate));
            }
        }
        if (!endDate.equals("") & !startDate.equals("")) {
            if (isActive.equals("true") || isActive.equals("false")) {
                return postingRepository.findAllByPstngDateBetweenAndAuthorizedDelivery(
                        LocalDate.parse(startDate),
                        LocalDate.parse(endDate),
                        Boolean.parseBoolean(isActive));
            } else {
                return postingRepository.findAllByPstngDateBetween(
                        LocalDate.parse(startDate),
                        LocalDate.parse(endDate));
            }
        }
        if (endDate.equals("") & startDate.equals("")) {
            if (isActive.equals("true") || isActive.equals("false")) {
                return postingRepository.findAllByAuthorizedDelivery(
                        Boolean.parseBoolean(isActive));
            } else {
                return postingRepository.findAll();
            }
        }
        return postingRepository.findAll();
    }

    private void parsePostings() throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        CSVReader csvReader = new CSVReader(
                new FileReader("postings.csv"), ';', '"', 1);
        List<String[]> allRows = csvReader.readAll();

        for (String[] row : allRows) {
            String txt = Arrays.toString(row);
            txt = txt.substring(1, txt.length() - 1);
//            System.out.println("Postings txt: " + txt);

            if (!txt.equals("")) {
                Posting posting = new Posting();
                String[] words = txt.split(", \t");

                posting.setMatDoc(Long.parseLong(words[0]));
                posting.setItem(Integer.parseInt(words[1]));
                posting.setDocDate(LocalDate.parse(words[2], dateTimeFormatter));
                posting.setPstngDate(LocalDate.parse(words[3], dateTimeFormatter));
                posting.setMaterialDescription(words[4]);
                posting.setQuantity(Integer.parseInt(words[5]));
                posting.setbUn(words[6]);
                posting.setAmountLC(Double.parseDouble(words[7].replace(",", ".")));
                posting.setCrcy(words[8]);
                posting.setUserName(words[9]);
                Login logins = loginRepository.findAllByAppAccountNameAndActive(words[9], true);
                posting.setAuthorizedDelivery(logins != null);
                postingRepository.save(posting);
            }
        }
    }

    private void parseLogins() throws IOException {
        CSVReader csvReader = new CSVReader(
                new FileReader("logins.csv"));
        List<String[]> allRows = csvReader.readAll();
        allRows.remove(0);

        for (String[] row : allRows) {
            String txt = Arrays.toString(row);
            txt = txt.substring(1, txt.length() - 1);
//            System.out.println("Logins txt: " + txt);

            if (!txt.equals("")) {
                Login login = new Login();
                String[] words = txt.split(", \t");

                login.setApplication(words[0]);
                login.setAppAccountName(words[1]);
                login.setActive(Boolean.parseBoolean(words[2]));
                login.setJobTitle(words[3]);
                login.setDepartment(words[4]);
                loginRepository.save(login);
            }
        }
    }
}

