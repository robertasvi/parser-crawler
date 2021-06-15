package com.crawler.parser.service;

import com.crawler.parser.dto.Category;
import com.crawler.parser.dto.Source;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class SourceService {

    public List<Source> getSources() {
        List<Source> sources = new ArrayList<>();
        sources.add( new Source("Lietuvos rytas", "https://www.lrytas.lt/", "https", "www", "" ,"lrytas.lt", "", "") );
        sources.add( new Source("Verslo žinios", "https://www.vz.lt/", "https", "www", "" ,"vz.lt", "", "") );
        sources.add( new Source("LRT", "https://www.lrt.lt/", "https", "www", "" ,"lrt.lt", "", "") );
        sources.add( new Source("TV3", "https://www.tv3.lt/", "https", "www", "" ,"tv3.lt", "", "") );
        sources.add( new Source("Delfi", "https://www.delfi.lt/", "https", "www", "" ,"delfi.lt", "", "") );
        sources.add( new Source("Valstietis", "http://valstietis.lt/", "http", "www", "" ,"valstietis.lt", "", "") );
        sources.add( new Source("Alfa", "https://www.alfa.lt/", "https", "www", "" ,"alfa.lt", "", "") );
        sources.add( new Source("Respublika", "https://www.respublika.lt/", "https", "www", "" ,"respublika.lt", "", "") );
        sources.add( new Source("Vakaru ekspresas", "https://ve.lt/", "https", "www", "" ,"ve.lt", "", "") );
        sources.add( new Source("Diena", "https://www.diena.lt/", "https", "www", "" ,"diena.lt", "", "") );
        sources.add( new Source("15min", "https://www.15min.lt/", "https", "www", "" ,"15min.lt", "", "") );
        sources.add( new Source("Zmones", "https://www.zmones.lt/", "https", "www", "" ,"zmones.lt", "", "") );
        sources.add( new Source("Panele", "https://www.panele.lt/lt", "https", "www", "" ,"panele.lt/lt", "lt", "") );
        sources.add( new Source("Regionu naujienos", "https://www.regionunaujienos.lt/", "https", "www", "" ,"regionunaujienos.lt", "", "") );
        sources.add( new Source("Etaplius", "https://www.etaplius.lt/", "https", "www", "" ,"etaplius.lt", "", "") );
        sources.add( new Source("Lietuvos Respublikos Vyriausybe", "https://lrv.lt/lt/naujienos", "https", "www", "" ,"lrv.lt/lt/naujienos", "naujienos", "") );
        sources.add( new Source("Aplinkos ministerija", "https://am.lrv.lt/lt/naujienos", "https", "www", "am" ,"am.lrv.lt/lt/naujienos", "naujienos", "") );
        sources.add( new Source("Energetikos ministerija", "https://enmin.lrv.lt/lt/naujienos", "https", "www", "enmin" ,"enmin.lrv.lt/lt/naujienos", "naujienos", "") );
        sources.add( new Source("Ekonomikos ir inovaciju ministerija", "https://eimin.lrv.lt/lt/naujienos", "https", "www", "eimin" ,"eimin.lrv.lt/lt/naujienos", "naujienos", "") );
        sources.add( new Source("Finansu ministerija", "https://finmin.lrv.lt/lt/naujienos", "https", "www", "finmin" ,"finmin.lrv.lt/lt/naujienos", "naujienos", "") );
        sources.add( new Source("Kulturos ministerija", "https://lrkm.lrv.lt/lt/naujienos", "https", "www", "lrkm" ,"lrkm.lrv.lt/lt/naujienos", "naujienos", "") );
        sources.add( new Source("Socialines apsaugos ir darbo ministerija", "https://socmin.lrv.lt/lt/naujienos", "https", "www", "socmin" ,"socmin.lrv.lt/lt/naujienos", "naujienos", "") );
        sources.add( new Source("Susisiekimo ministerija", "https://sumin.lrv.lt/lt/naujienos", "https", "www", "sumin" ,"sumin.lrv.lt/lt/naujienos", "naujienos", "") );
        sources.add( new Source("Sveikatos apsaugos ministerija", "https://sam.lrv.lt/lt/naujienos", "https", "www", "sam" ,"sam.lrv.lt/lt/naujienos", "naujienos", "") );
        sources.add( new Source("Svietimo, mokslo ir sporto ministerija", "https://www.smm.lt/web/lt/news", "https", "www", "" ,"smm.lt/web/lt/news", "news", "") );
        sources.add( new Source("Teisingumo ministerija", "https://tm.lrv.lt/lt/naujienos", "https", "www", "tm" ,"tm.lrv.lt/lt/naujienos", "naujienos", "") );
        sources.add( new Source("Uzsienio reikalu ministerija", "https://www.urm.lt/default/lt/naujienos", "https", "www", "" ,"urm.lt/default/lt/naujienos", "default/lt/naujienos", "") );
        sources.add( new Source("Vidaus reikalu ministerija", "https://vrm.lrv.lt/lt/naujienos", "https", "www", "vrm" ,"vrm.lrv.lt/lt/naujienos", "naujienos", "") );
        sources.add( new Source("Zemes ukio minniterija", "https://zum.lrv.lt/lt/naujienos", "https", "www", "zum" ,"zum.lrv.lt/lt/naujienos", "naujienos", "") );
        sources.add( new Source("Policija", "https://policija.lrv.lt/lt/naujienos", "https", "www", "policija" ,"policija.lrv.lt/lt/naujienos", "naujienos", "") );
        return sources;
    }

    public String[] getSportasKeywords() {
        String[] keywords = new String[]{"sport", "cempio", "varzyb", "taure", "trener", "varzov",
                "komand", "rinktine", "krepsin", "futbol", "tenis", "nba", "fiba", "fifa", "eurolyg",
                "zaid", "rezult", "skand"};
        return keywords;
    }

    public String[] getKriminalaiKeywords() {
        String[] keywords = new String[]{"kriminal", "narko", "mafi", "zmogzu", "kalej", "nelaim",
                "avarij", "gink", "gaistr", "sprog", "trag", "seksual", "itar", "teis"};
        return keywords;
    }

    public String[] getKelionesKeywords() {
        String[] keywords = new String[]{"kelion", "atosto", "ispud"};
        return keywords;
    }

    public String[] getStiliusKeywords() {
        String[] keywords = new String[]{"stil", "mada", "mados", "sukuos", "drabuz", "avali"};
        return keywords;
    }

    public String[] getZmonesKeywords() {
        String[] keywords = new String[]{"veidai", "zmones", "vedejai", "pramogu-pasaulis", "aktor",
                "muzik", "atlikej", "skyryb", "zvaigzd"};
        return keywords;
    }

    public String[] getLaisvalaikisKeywords() {
        String[] keywords = new String[]{"laisvalaik", "kultur", "televi", "pramog", "kinas", "teatr",
                "koncert", "rengin", "kultur"};
        return keywords;
    }

    public String[] getSeimaKeywords() {
        String[] keywords = new String[]{"vaik", "zaisl", "teva", "sveika", "maist", "skon", "mityb",
                "augint", "gyvenimo-budas", "mam", "tet", "terap", "mazam"};
        return keywords;
    }

    public String[] getUkisKeywords() {
        String[] keywords = new String[]{"uki","agro"};
        return keywords;
    }

    public String[] getMokslasKeywords() {
        String[] keywords = new String[]{"moksl", "technolo", "kosmos", "atradi", "labora", "inter", "progra",
                "bitcoin", "kripto"};
        return keywords;
    }

    public String[] getVerslasKeywords() {
        String[] keywords = new String[]{"versl", "karjer", "preky", "pramon", "transport", "profsaj", "imon",
                "vadov", "darbuot", "darb", "alga", "mokes", "pasiskol", "skola", "biudz", "energ"};
        return keywords;
    }

    public String[] getFinansaiKeywords() {
        String[] keywords = new String[]{"finans", "ekonom", "pinig", "rinka", "rinkos", "investi", "infliac",
                "deflia", "turtas", "auks", "sidabr", "naft", "euras", "doler", "kursas", "valiut"};
        return keywords;
    }

    public String[] getPolitikaKeywords() {
        String[] keywords = new String[]{"politik", "koru", "savivald", "meras", "prezi", "minist", "seimas",
                "rinkejai", "rinkimai", "referendum", "respub", "konflik", "karas", "pabegeliai", "parlamen",
                "prote", "itar", "polito", "valdan"};
        return keywords;
    }

    public String[] getUzsienyjeKeywords() {
        String[] keywords = new String[]{"uzsien", "pasaul", "world"};
        return keywords;
    }

    public String[] getLietuvojeKeywords() {
        String[] keywords = new String[]{"lietuvoje", "savivald", "vilni", "kaun", "klaipe", "siaul", "palang",
                "druski", "rajon", "paneve", "uten", "riboj", "valstyb"};
        return keywords;
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add( new Category("Lietuvoje", "", "lietuvoje") );
        categories.add( new Category("Užsienyje", "", "uzsienyje") );
        categories.add( new Category("Politika", "", "politika") );
        categories.add( new Category("Finansai", "", "finansai") );
        categories.add( new Category("Verslas", "", "verslas") );
        categories.add( new Category("Mokslas", "", "mokslas") );
        categories.add( new Category("Ūkis", "", "ukis") );
        categories.add( new Category("Šeima", "", "seima") );
        categories.add( new Category("Laisvalaikis", "", "laisvalaikis") );
        categories.add( new Category("Kelionės", "", "keliones") );
        categories.add( new Category("Kriminalai", "", "kriminalai") );
        categories.add( new Category("Sportas", "", "sportas") );
        return categories;
    }
}

