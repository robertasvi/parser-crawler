package com.crawler.parser.service;

import com.crawler.parser.dto.Source;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    List<URI> stilius;
    List<URI> keliones;
    List<URI> kriminalai;
    List<URI> sportas;
    List<URI> kiti;
    Map<String, List<URI>> categorized;

    public Map run() {
        lietuvoje = new ArrayList<>();
        uzsienyje = new ArrayList<>();
        politika = new ArrayList<>();
        finansai = new ArrayList<>();
        verslas = new ArrayList<>();
        mokslas = new ArrayList<>();
        ukis = new ArrayList<>();
        seima = new ArrayList<>();
        laisvalaikis = new ArrayList<>();
        stilius = new ArrayList<>();
        keliones = new ArrayList<>();
        kriminalai = new ArrayList<>();
        sportas = new ArrayList<>();
        kiti = new ArrayList<>();
        categorized = new HashMap<>();
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
        categorized.put("lietuvoje",lietuvoje);
        categorized.put("uzsienyje",uzsienyje);
        categorized.put("politika",politika);
        categorized.put("finansai",finansai);
        categorized.put("verslas",verslas);
        categorized.put("mokslas",mokslas);
        categorized.put("ukis",ukis);
        categorized.put("seima",seima);
        categorized.put("laisvalaikis",laisvalaikis);
        categorized.put("stilius",stilius);
        categorized.put("keliones",keliones);
        categorized.put("kriminalai",kriminalai);
        categorized.put("sportas",sportas);
        categorized.put("kiti",kiti);
        return categorized;
    }

    public void categorize(URI extracted) {
        System.out.println(extracted.toString());
        if(extracted.getPath().contains("lietuvoje") || extracted.getPath().contains("savivald") || extracted.getPath().contains("vilni") ||
                extracted.getPath().contains("kaun") || extracted.getPath().contains("klaipe") || extracted.getPath().contains("siaul") ||
                extracted.getPath().contains("palang") || extracted.getPath().contains("druski") || extracted.getPath().contains("rajon") ||
                extracted.getPath().contains("paneve") || extracted.getPath().contains("uten")) {
            lietuvoje.add(extracted);
        } else if(extracted.getPath().contains("uzsien") || extracted.getPath().contains("pasaul") || extracted.getPath().contains("world")) {
            uzsienyje.add(extracted);
        } else if(extracted.getPath().contains("politik") || extracted.getPath().contains("koru") || extracted.getPath().contains("savivald") ||
                extracted.getPath().contains("meras") || extracted.getPath().contains("prezi") || extracted.getPath().contains("minist") ||
                extracted.getPath().contains("seimas") || extracted.getPath().contains("rinkejai") || extracted.getPath().contains("rinkimai") ||
                extracted.getPath().contains("referendum") || extracted.getPath().contains("respub") || extracted.getPath().contains("konflik") ||
                extracted.getPath().contains("karas") || extracted.getPath().contains("pabegeliai") || extracted.getPath().contains("parlamen")) {
            politika.add(extracted);
        } else if(extracted.getPath().contains("finans") || extracted.getPath().contains("ekonom") || extracted.getPath().contains("pinig") ||
                extracted.getPath().contains("rinka") || extracted.getPath().contains("rinkos") || extracted.getPath().contains("investi") ||
                extracted.getPath().contains("infliac") || extracted.getPath().contains("deflia") || extracted.getPath().contains("turtas") ||
                extracted.getPath().contains("auks") || extracted.getPath().contains("sidabr") || extracted.getPath().contains("naft") ||
                extracted.getPath().contains("euras") || extracted.getPath().contains("doler") || extracted.getPath().contains("kursas")) {
            finansai.add(extracted);
        } else if(extracted.getPath().contains("versl") || extracted.getPath().contains("karjer") || extracted.getPath().contains("preky") ||
                extracted.getPath().contains("pramon") || extracted.getPath().contains("transport") || extracted.getPath().contains("profsaj") ||
                extracted.getPath().contains("imon") || extracted.getPath().contains("vadov") || extracted.getPath().contains("darbuot") ||
                extracted.getPath().contains("darb") || extracted.getPath().contains("alga") || extracted.getPath().contains("mokes")) {
            verslas.add(extracted);
        } else if(extracted.getPath().contains("moksl") || extracted.getPath().contains("technolo") || extracted.getPath().contains("kosmos") ||
                extracted.getPath().contains("atradi") || extracted.getPath().contains("labora")) {
            mokslas.add(extracted);
        } else if(extracted.getPath().contains("uki") || extracted.getPath().contains("agro")) {
            ukis.add(extracted);
        } else if(extracted.getPath().contains("vaik") || extracted.getPath().contains("zaisl") || extracted.getPath().contains("tevai") ||
                extracted.getPath().contains("sveik") || extracted.getPath().contains("maist") || extracted.getPath().contains("skon") ||
                extracted.getPath().contains("mityb") || extracted.getPath().contains("augint")) {
            seima.add(extracted);
        } else if(extracted.getPath().contains("laisvalaik") || extracted.getPath().contains("kultur") || extracted.getPath().contains("televi") ||
                extracted.getPath().contains("pramog") || extracted.getPath().contains("kinas") || extracted.getPath().contains("teatr") ||
                extracted.getPath().contains("koncert") || extracted.getPath().contains("rengin") || extracted.getPath().contains("kultur")) {
            laisvalaikis.add(extracted);
        } else if(extracted.getPath().contains("stil") || extracted.getPath().contains("mada") || extracted.getPath().contains("mados") ||
                extracted.getPath().contains("sukuos") || extracted.getPath().contains("drabuz") || extracted.getPath().contains("avali")) {
            stilius.add(extracted);
        } else if(extracted.getPath().contains("kelion") || extracted.getPath().contains("atosto") || extracted.getPath().contains("ispud")) {
            keliones.add(extracted);
        } else if(extracted.getPath().contains("kriminal") || extracted.getPath().contains("narko") || extracted.getPath().contains("mafi") ||
                extracted.getPath().contains("zmogzu") || extracted.getPath().contains("kalej") || extracted.getPath().contains("nelaim") ||
                extracted.getPath().contains("avarij") || extracted.getPath().contains("gink") || extracted.getPath().contains("gaistr") ||
                extracted.getPath().contains("sprog")) {
            kriminalai.add(extracted);
        } else if(extracted.getPath().contains("sport") || extracted.getPath().contains("cempion") || extracted.getPath().contains("varzyb") ||
                extracted.getPath().contains("taure") || extracted.getPath().contains("trener") || extracted.getPath().contains("varzov") ||
                extracted.getPath().contains("komand") || extracted.getPath().contains("rinktine") || extracted.getPath().contains("krepsin") ||
                extracted.getPath().contains("futbol") || extracted.getPath().contains("tenis") || extracted.getPath().contains("nba") ||
                extracted.getPath().contains("fiba") || extracted.getPath().contains("fifa") || extracted.getPath().contains("eurolyg")) {
            sportas.add(extracted);
        } else {
            kiti.add(extracted);
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
        if (sameDomain(uriOriginal, uriExtracted)) {
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

    public boolean sameDomain(URI original, URI extracted) {
        if (extracted.getHost() != null && extracted.getHost().equals(original.getHost())) {
            return true;
        } else if (extracted.getHost() == null && extracted.getPath() != null && !extracted.getPath().isEmpty()) {
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

