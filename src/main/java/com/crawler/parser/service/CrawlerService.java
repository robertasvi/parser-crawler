package com.crawler.parser.service;

import com.crawler.parser.dto.Source;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {

    @Autowired
    SourceService sourceService;

    List<URI> lietuvoje;
    List<URI> uzsienyje;
    List<URI> politika;
    List<URI> finansai;
    List<URI> verslas;
    List<URI> mokslas;
    List<URI> ukis;
    List<URI> seima;
    List<URI> laisvalaikis;
    List<URI> zmones;
    List<URI> stilius;
    List<URI> keliones;
    List<URI> kriminalai;
    List<URI> sportas;
    List<URI> kiti;
    Map<String, List<URI>> categorized;

    public Map run() {
        initiateLists();
        sourceService.getSources().forEach(source -> {
            List<String> extractions = new ArrayList<>();
            Document page = parse(source.getRaw());
            Elements links = page.getElementsByTag("a");
            for (Element link : links) {
                String extracted = link.attr("href");
                try {
                    URI extractedUri = normalize(source, extracted);
                    if (isArticle(extractedUri) && !isDuplicate(extractions, extractedUri) && !isComment(extractedUri.toString())) {
                        extractions.add(extractedUri.getPath());
                        categorize(extractedUri);
                    }
                } catch (URISyntaxException e) {
//                    e.printStackTrace();
                } catch (Exception e) {
//                    e.printStackTrace();
                }
            }
        });
        aggregateCategoriesMap();
        return categorized;
    }

    public void initiateLists() {
        lietuvoje = new ArrayList<>();
        uzsienyje = new ArrayList<>();
        politika = new ArrayList<>();
        finansai = new ArrayList<>();
        verslas = new ArrayList<>();
        mokslas = new ArrayList<>();
        ukis = new ArrayList<>();
        seima = new ArrayList<>();
        laisvalaikis = new ArrayList<>();
        zmones = new ArrayList<>();
        stilius = new ArrayList<>();
        keliones = new ArrayList<>();
        kriminalai = new ArrayList<>();
        sportas = new ArrayList<>();
        kiti = new ArrayList<>();
        categorized = new HashMap<>();
    }

    public void aggregateCategoriesMap() {
        categorized.put("lietuvoje",lietuvoje);
        categorized.put("uzsienyje",uzsienyje);
        categorized.put("politika",politika);
        categorized.put("finansai",finansai);
        categorized.put("verslas",verslas);
        categorized.put("mokslas",mokslas);
        categorized.put("ukis",ukis);
        categorized.put("seima",seima);
        categorized.put("laisvalaikis",laisvalaikis);
        categorized.put("zmones",zmones);
        categorized.put("stilius",stilius);
        categorized.put("keliones",keliones);
        categorized.put("kriminalai",kriminalai);
        categorized.put("sportas",sportas);
        categorized.put("kiti",kiti);
    }

    public void categorize(URI extracted) {
        if(isLietuvoje(extracted)) {
            lietuvoje.add(extracted);
        } else if(isUzsienyje(extracted)) {
            uzsienyje.add(extracted);
        } else if(isPolitika(extracted)) {
            politika.add(extracted);
        } else if(isFinansai(extracted)) {
            finansai.add(extracted);
        } else if(isVerslas(extracted)) {
            verslas.add(extracted);
        } else if(isMokslas(extracted)) {
            mokslas.add(extracted);
        } else if(isUkis(extracted)) {
            ukis.add(extracted);
        } else if(isSeima(extracted)) {
            seima.add(extracted);
        } else if(isLaisvalaikis(extracted)) {
            laisvalaikis.add(extracted);
        } else if(isZmones(extracted)) {
            zmones.add(extracted);
        } else if(isStilius(extracted)) {
            stilius.add(extracted);
        } else if(isKeliones(extracted)) {
            keliones.add(extracted);
        } else if(isKriminalai(extracted)) {
            kriminalai.add(extracted);
        } else if(isSportas(extracted)) {
            sportas.add(extracted);
        } else {
            kiti.add(extracted);
        }
    }

    public boolean isSportas(URI extracted) {
        String[] keywords = sourceService.getSportasKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isKriminalai(URI extracted) {
        String[] keywords = sourceService.getKriminalaiKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isKeliones(URI extracted) {
        String[] keywords = sourceService.getKelionesKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isStilius(URI extracted) {
        String[] keywords = sourceService.getStiliusKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isZmones(URI extracted) {
        String[] keywords = sourceService.getZmonesKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLaisvalaikis(URI extracted) {
        String[] keywords = sourceService.getLaisvalaikisKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSeima(URI extracted) {
        String[] keywords = sourceService.getSeimaKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isUkis(URI extracted) {
        String[] keywords = sourceService.getUkisKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isMokslas(URI extracted) {
        String[] keywords = sourceService.getMokslasKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isVerslas(URI extracted) {
        String[] keywords = sourceService.getVerslasKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFinansai(URI extracted) {
        String[] keywords = sourceService.getFinansaiKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPolitika(URI extracted) {
        String[] keywords = sourceService.getPolitikaKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isUzsienyje(URI extracted) {
        String[] keywords = sourceService.getUzsienyjeKeywords();
        int matches = 0;
        for (String word : keywords) {
            if(extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if(matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLietuvoje(URI extracted) {
        String[] keywords = sourceService.getLietuvojeKeywords();
        int matches = 0;
        for (String word : keywords) {
            if (extracted.getPath().contains(word)) {
                matches++;
            }
        }
        if (matches > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isComment(String extracted) {
        if(extracted.contains("/com") || extracted.contains("=com") ||
                extracted.contains("#komen") || extracted.contains("/komentar") ||
                extracted.contains("com") || extracted.contains("/komentar") ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDuplicate(List<String> extractions, URI extracted) {
        if (extractions.contains(extracted.getPath())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isArticle(URI extracted) throws Exception {
        if (extracted.getPath().split("-").length > 4 || extracted.getPath().split("_").length > 4) {
            return true;
        } else {
            return false;
        }
    }

    public URI normalize(Source original, String extracted) throws URISyntaxException {
        URI uriOriginal = new URI(original.getRaw());
        URI uriExtracted = new URI(extracted);
        if (sameDomain(original, extracted)) {
            if ((extracted.contains("https://") || extracted.contains("http://")) && extracted.contains("www.") && extracted.contains(original.getDomain())) {
                // Ideal scenario. It contains everything to be structured in new URI().
            } else if (extracted.contains("www.") && extracted.contains(original.getDomain())) {
                if (extracted.startsWith("//")) {
                    uriExtracted = new URI("https://" + extracted.substring(2));
                } else if (extracted.startsWith("/")) {
                    uriExtracted = new URI("https://" + extracted.substring(1));
                } else {
                    uriExtracted = new URI("https://" + extracted);
                }
            } else if ((extracted.contains("https://") || extracted.contains("http")) && extracted.contains(original.getDomain())) {
                // Ideal scenario
            } else if (extracted.contains(original.getDomain())) {
                if (extracted.startsWith("//")) {
                    uriExtracted = new URI("https://" + "www." + extracted.substring(2));
                } else if (extracted.startsWith("/")) {
                    uriExtracted = new URI("https://" + "www." + extracted.substring(1));
                } else {
                    uriExtracted = new URI("https://" + "www." + extracted);
                }
            } else if ((!extracted.contains("https://") && !extracted.contains("http://")) && !extracted.contains("www.") && !extracted.contains(original.getDomain())) {
                if (extracted.startsWith("/")) {
                    uriExtracted = new URI("https://" + "www." + original.getDomain() + extracted);
                } else {
                    uriExtracted = new URI("https://" + "www." + original.getDomain() + '/' + extracted);
                }
            }
        } else {
            uriExtracted = new URI("");
        }
        return uriExtracted;
    }

    public boolean sameDomain(Source original, String extracted) {
        if (extracted != null && extracted.contains(original.getDomain())) {
            return true;
        } else {
            return false;
        }
    }

    public Document parse(String url) {
        Document page = null;
        try {
            page = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

}

