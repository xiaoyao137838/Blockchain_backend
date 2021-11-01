package com.example.springboot;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.json.simple.parser.*;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class MyFirst {
    @GetMapping(path = "/first/", produces = "application/json")
    public HttpEntity<List<Price>> getValues() throws Exception {
        List<Price> res = new ArrayList<>();
        String url_blockchain_1 = "https://blockchain.info/ticker?base=BTC";
        String url_blockchain_2 = "https://blockchain.info/ticker?base=ETH";
        List<Double> blockchain_BTC = getValue(url_blockchain_1);
        List<Double> blockchain_ETH = getValue(url_blockchain_2);
        Price blockchainPlatform = new Price();
        blockchainPlatform.setBTCBuy(blockchain_BTC.get(0));
        blockchainPlatform.setBTCSell(blockchain_BTC.get(1));
        blockchainPlatform.setETHBuy(blockchain_ETH.get(0));
        blockchainPlatform.setETHSell(blockchain_ETH.get(1));

        String url_bitmap_1 = "https://www.bitstamp.net/api-internal/price-history/btcusd/";
        String url_bitmap_2 = "https://www.bitstamp.net/api-internal/price-history/ethusd/";
        List<Double> bitmap_BTC = getValue(url_bitmap_1);
        List<Double> bitmap_ETH = getValue(url_bitmap_2);
        Price bitmapPlatform = new Price();
        bitmapPlatform.setBTCBuy(bitmap_BTC.get(0));
        bitmapPlatform.setBTCSell(bitmap_BTC.get(1));
        bitmapPlatform.setETHBuy(bitmap_ETH.get(0));
        bitmapPlatform.setETHSell(bitmap_ETH.get(1));
        if (blockchain_BTC.get(0) < bitmap_BTC.get(0)) {
            System.out.println("buy BTC: blockchain");
            System.out.println("sell BTC: bitmap");
            blockchainPlatform.setPlatformBTCBuy("Blockchain");
            bitmapPlatform.setPlatformBTCSell("Bitmap");
        } else {
            System.out.println("buy BTC: bitmap");
            System.out.println("sell BTC: blockchain");
            bitmapPlatform.setPlatformBTCBuy("Bitmap");
            blockchainPlatform.setPlatformBTCSell("Blockchain");
        }
        if (blockchain_ETH.get(0) < bitmap_ETH.get(0)) {
            System.out.println("buy ETH: blockchain");
            System.out.println("sell ETH: bitmap");
            blockchainPlatform.setPlatformETHBuy("Blockchain");
            bitmapPlatform.setPlatformETHSell("Bitmap");
        } else {
            System.out.println("buy ETH: bitmap");
            System.out.println("sell ETH: blockchain");
            bitmapPlatform.setPlatformETHBuy("Bitmap");
            blockchainPlatform.setPlatformETHSell("Blockchain");
        }
        res.add(blockchainPlatform);
        res.add(bitmapPlatform);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(res, headers, HttpStatus.OK);
    }
    public List<Double> getValue(String url_str) throws Exception {
        System.out.println("Wait...");

        URL url = new URL(url_str);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();
        System.out.println(responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        String inline = "";
        while ((inputLine = in.readLine()) != null) {
            //System.out.println(inputLine);
            inline += inputLine;
        }
        in.close();
        if (url_str.indexOf("blockchain") == -1) {
            return getFromBitmap(inline);
        }
        return getFromBlockchain(inline);

    }
    private List<Double> getFromBitmap(String data) throws Exception {
        if (data == null) {
            return new ArrayList<>();
        }
        JSONParser parse = new JSONParser();
        JSONObject jsonobj = (JSONObject) parse.parse(data);
        JSONObject jsonobj_1 = (JSONObject) jsonobj.get("data");
        JSONObject jsonobj_2 = (JSONObject) jsonobj_1.get("latest");
        String jsonobj_price = (String) jsonobj_2.get("price");
        System.out.println(jsonobj_price);
        double buyPrice = Double.parseDouble(jsonobj_price);
        double sellPrice = Double.parseDouble(jsonobj_price);
        List<Double> list = new ArrayList<>();
        list.add(buyPrice);
        list.add(sellPrice);
        return list;

    }
    private List<Double> getFromBlockchain(String data) throws Exception {
        if (data == null) {
            return new ArrayList<>();
        }
        JSONParser parse = new JSONParser();
        JSONObject jsonobj = (JSONObject) parse.parse(data);
        JSONObject jsonobj_1 = (JSONObject) jsonobj.get("USD");
        double buyPrice = (double) jsonobj_1.get("buy");
        double sellPrice = (double) jsonobj_1.get("sell");
        System.out.println(buyPrice);
        System.out.println(sellPrice);

        List<Double> list = new ArrayList<>();
        list.add(buyPrice);
        list.add(sellPrice);
        return list;
    }
}
