package com.rates.exchangerates;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

public class WebParser {
    public ArrayList<Bank> bankData;
    public void parse() throws IOException {
        bankData = new ArrayList<>();
        String url_sravni = "https://www.sravni.ru/valjuty/";
        try {
            disableSSLCertificateChecking();
            Document doc_sravni = Jsoup.connect(url_sravni).get();

            Element rates_sravni = doc_sravni.getElementById("__NEXT_DATA__");

            try (FileWriter fileWriter = new FileWriter("out_currencies.json")){
                assert rates_sravni != null;
                fileWriter.write(rates_sravni.html());
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("Error fetching the URL: " + e.getMessage());
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("out_currencies.json"));
        JsonNode banksNode = root.path("props").path("initialReduxState")
                .path("currency").path("banksRates");
        for (JsonNode bankNode : banksNode) {
            Bank bank = new Bank(bankNode.path("organization").path("name").path("short").asText());
            JsonNode ratesNode = bankNode.path("rates");
            for (JsonNode rateNode : ratesNode) {
                if (rateNode.path("currency").asText().equals("USD") ||
                        rateNode.path("currency").asText().equals("EUR")){
                    CurrencySimple currency = new CurrencySimple(rateNode.path("currency").asText(),
                            rateNode.path("buy").asDouble(), rateNode.path("sell").asDouble());
                    bank.addCurrency(currency);
                }
            }
            if (!(bank.getCurrencies().isEmpty()))
                bankData.add(bank);
        }
        //System.out.println(bankData);
    }

    private static void disableSSLCertificateChecking() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            throw new RuntimeException("Failed to disable SSL certificate checking", e);
        }
    }
}