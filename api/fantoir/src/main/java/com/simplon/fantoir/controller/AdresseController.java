package com.simplon.fantoir.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.simplon.fantoir.helper.Helper;
import com.simplon.fantoir.model.Adresse;
import com.simplon.fantoir.service.ICodePostalService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("adresse")
public class AdresseController {
    @Autowired
    private ICodePostalService codePostalService;
    @Autowired
    private SessionFactory sessionFactory;

    @GetMapping(value = "/search")
    @ResponseBody
    public List<Adresse> searchCP(@RequestParam String adresse) {

        Session session = this.sessionFactory.openSession();

        //list des expressions eliminées lors de la recherche
        String textAEliminer = Helper.expressionsNonAcceptees();

        // recuperer les numeros dans l'adresse
        Pattern pDigit, pStrings;
        StringBuilder digitStrBuilder = new StringBuilder();

        pDigit = Pattern.compile("\\d+");
        Matcher mDigits = pDigit.matcher(adresse);
        while(mDigits.find()) {
            digitStrBuilder.append("%" + mDigits.group().toString() + "%");
        }
        String digitStr = digitStrBuilder.toString().replace("%%", "% %");

        // recuperer chaines de caracteres de caracteres
        String tempAdresse = adresse.replaceAll("\\d","");

        String[] adresseSplited = adresse.split(" ");

        for(int i = 0; i < adresseSplited.length; i++){
            adresseSplited[i] = "%" + adresseSplited[i].toLowerCase() + "%";
        }

        // la requete sql executée pour récupérer les adresses
        String query = "SELECT n.numero, v.nom_voie, cp.code_postal, c.nom_commune \n" +
                "FROM commune c \n" +
                "INNER JOIN code_postal cp ON c.id_commune = cp.commune_id\n" +
                "INNER JOIN code_postal_voie cpv ON cp.id_code_postal = cpv.code_postal_id\n" +
                "INNER JOIN voie v ON cpv.voie_id = v.id_voie\n" +
                "INNER JOIN voie_numero vn ON v.id_voie = vn.voie_id\n" +
                "INNER JOIN numero n ON vn.numero_id = n.id_numero\n" +
                "where (cast(n.numero as varchar) like any (SELECT nbr FROM regexp_split_to_table(?1, E'\\\\\\s+') as nbr) \n" +
                "or cp.code_postal like any (SELECT nbr FROM regexp_split_to_table(?1, E'\\\\\\s+') as nbr))\n" +
                "and ((\n" +
                "lower(v.nom_voie) like any(\n" +
                "select adresseText.text\n" +
                "from (SELECT lower(text) as text FROM regexp_split_to_table(?2, E'\\\\\\s+') AS text) as adresseText \n" +
                "left join\n" +
                "(SELECT lower(text) as text FROM regexp_split_to_table(?3, E'\\\\\\s+') AS text) as textNonAccepte\n" +
                "on adresseText.text = textNonAccepte.text\n" +
                "WHERE textNonAccepte.text IS NULL))\n" +
                "and(\n" +
                "lower(c.nom_commune) like any(\n" +
                "select adresseText.text\n" +
                "from\n" +
                "(SELECT lower(text) as text FROM regexp_split_to_table(?2, E'\\\\\\s+') AS text) as adresseText \n" +
                "left join\n" +
                "(SELECT lower(text) as text FROM regexp_split_to_table(?3, E'\\\\\\s+') AS text) as textNonAccepte\n" +
                "on adresseText.text = textNonAccepte.text\n" +
                "WHERE textNonAccepte.text IS NULL)))";


        NativeQuery q = session.createSQLQuery(query);
        String adresseParam = String.join(" ", adresseSplited);
        //liste des numeros envoyés dans l'adresse à recherchée en format de string
        q.setParameter(1, digitStr);
        //liste des mots envoyés dans l'adresse en format de string
        q.setParameter(2, adresseParam);
        // liste des expressions qui seront prise en compte lors de la recherche en format de string
        q.setParameter(3, textAEliminer);

        //session.close();

        return  q.list();

    }
}
