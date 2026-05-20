package controller;

import model.*;
import storage.IStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    IStorage storage;

    public Controller(IStorage storage) {
        this.storage = storage;
    }

    //Destillering
    private int destilleringsID = 1;
    public Destillering createDestillering(LocalDate startDato, LocalDate slutDato, double alkoholProcent, boolean rygematriale, String kommentar, double væskeMængde, Maltbatch maltbatch) {
        Destillering destillering = new Destillering(destilleringsID, startDato, slutDato, alkoholProcent, rygematriale, kommentar, væskeMængde, maltbatch);
        destilleringsID++;
        storage.addDestillering(destillering);
        return destillering;
    }

    public List<Destillering> getDestilleringer() {
        return storage.getDestilleringer();
    }

    //Produkt
    private int produktNr = 1;
    public Produkt createProdukt( double vandMængde , double alkoholProcent, String beskrivelse, KvalitetsStempel kvalitetsStempel) {
        Produkt produkt = new Produkt(produktNr, vandMængde, alkoholProcent, beskrivelse, kvalitetsStempel);
        produktNr++;
        storage.addProdukt(produkt);
        return produkt;
    }

    public List<Produkt> getProdukter(){
        return  storage.getProdukter();
    }

    //Fade
    private int fadNr = 1;
    public Fad createFad(String land, double størrelse, String tidligereIndhold, Leverandør leverandør) {
        Fad fad = new Fad(fadNr, land, størrelse, tidligereIndhold, leverandør);
        fadNr++;
        storage.addFad(fad);
        return fad;
    }

    public List<Fad> getFade() {
        return storage.getFade();
    }

    public List<Fad> getFadeKlarTilAftapning() {
        List<Fad> klarFade = new ArrayList<>();

        for (Fad fad : storage.getFade()) {
            if (fad.erKlarTilAftapning(LocalDate.now())) {
                klarFade.add(fad);
            }
        }

        return klarFade;
    }

    //Destillat
    public Destillat createDestillat(String destilatNavn, LocalDate dato, double alkoholProcent) {
        Destillat destillat = new Destillat(destilatNavn, dato, alkoholProcent);
        storage.addDestillat(destillat);
        return destillat;
    }

    public List<Destillat> getDestillater(){
        return storage.getDestillater();
    }

    //DestilleringsMængde
    public DestilleringsMængde createDestilleringsMængde (double mængde, Destillering destillering, Destillat destillat){
        return destillering.createDestilleringsMængde(mængde, destillat);
    }

    //PåfyldningsMængde
    public Påfyldning createPåfyldning (double mængdeFraDestillat, LocalDate dato, Destillat destillat, Fad fad){
        return destillat.createPåfyldning(mængdeFraDestillat, dato, fad);
    }

    //lager
    private int lagerIdCount = 1;
    public Lager createLager(String lagerNavn, String adresse) {
        Lager lager = new Lager(lagerIdCount, lagerNavn, adresse);
        lagerIdCount++;
        storage.addLager(lager);
        return lager;
    }

    public Lager createLagerMedReoler(String lagerNavn, String adresse, int antalReoler) {
        Lager lager = createLager(lagerNavn, adresse);
        lager.createReoler(antalReoler);
        return lager;
    }

    public List<Lager> getLager(){
        return storage.getLagerListe();
    }

    //MaltBatch
    private int batchNr = 1;
    public Maltbatch createMaltBatch (Kornsort kornsort){
        Maltbatch maltbatch = new Maltbatch(batchNr, kornsort);
        batchNr++;
        storage.addMaltbatch(maltbatch);
        return maltbatch;
    }

    public List<Maltbatch> getMaltbatches(){
        return storage.getMaltbatch();
    }

    //Leverandør
    public Leverandør createLeverandør (String navn, String land, String kontaktInfo){
        Leverandør leverandør = new Leverandør(navn, land, kontaktInfo);
        storage.addLeverandør(leverandør);
        return leverandør;
    }

    public List<Leverandør> getLeverandører(){
        return storage.getLeverandør();
    }


    public List<Flaske> createFlasker(int størrelse, Produkt produkt, int antalFlasker){

        List<Flaske> flasker = new ArrayList<>();

        if (produkt == null) {
            throw new IllegalArgumentException("Produkt skal vælges");
        }
        if (størrelse <= 0) {
            throw new IllegalArgumentException("Størrelse skal være større end 0");
        }
        if (antalFlasker <= 0) {
            throw new IllegalArgumentException("Antal flasker skal være større end 0");
        }
        double samletMængde = (størrelse / 1000.0) * antalFlasker;
        if (samletMængde > produkt.getRestMængde()) {
            throw new IllegalArgumentException("Der er ikke nok whisky");
        }

        for (int i = 0; i < antalFlasker; i++) {

            Flaske flaske = produkt.createFlaske(størrelse);

            storage.addFlaske(flaske);

            flasker.add(flaske);
        }

        return flasker;
    }

    public List<Flaske> getFlasker(){
        return storage.getFlasker();
    }

    public WhiskySammensætning createWhiskySammensætning (double mængdeFraDestillat, Produkt produkt, Destillat destillat){
        return produkt.createWhiskySammensætning(mængdeFraDestillat, destillat);
    }

    public List<Fad> søgEfterFade(Integer fadNr, Lager lager, String tidligereIndhold, Integer alder) {
        List<Fad> alleFade = getFade();
        List<Fad> fundneFade = new ArrayList<>();
        if (alder != null && alder < 0) {
            throw new IllegalArgumentException("Alder må ikke være negativ");
        }
        for (Fad fad : alleFade) {
            boolean matcher = true;
            if (fadNr != null && fad.getFadNr() != fadNr) {
                matcher = false;
            }
            if (lager != null ) {
                if (fad.getReol() == null || fad.getReol().getLager() != lager){
                    matcher = false;
                }
            }
            if (tidligereIndhold != null && !tidligereIndhold.isBlank()) {
                if (!tidligereIndhold.equalsIgnoreCase(fad.getTidligereIndhold())) {
                    matcher = false;
                }
            }
            if (alder != null ){
                if (fad.getPåfyldninger().isEmpty()){
                    matcher = false;
                } else {
                    LocalDate sidsteDato = fad.getPåfyldninger().getLast().getDato();
                    LocalDate grænseDato = LocalDate.now().minusYears(alder);
                    if (sidsteDato.isAfter(grænseDato)) {
                        matcher = false;
                    }
                }
            }
            if (matcher) {
                fundneFade.add(fad);
            }
        }
        return fundneFade;
    }

    //UC7 Opret whiskyprodkut
    public List<Destillat> getDestillaterKlarTilProdukt() {

        List<Destillat> klareDestillater = new ArrayList<>();

        for (Destillat destillat : storage.getDestillater()) {

            if (destillat.getTilgængeligKlarMængde(LocalDate.now()) > 0) {
                klareDestillater.add(destillat);
            }
        }

        return klareDestillater;
    }


    public String visHistorik(int flaskeNr, int produktNr) {

        Flaske fundetFlaske = null;

        for (Flaske flaske : storage.getFlasker()) {
            if (flaske.getFlaskeNr() == flaskeNr && flaske.getProdukt().getProduktNr() == produktNr) {
                fundetFlaske = flaske;
            }
        }

        if (fundetFlaske == null) {
            throw new IllegalArgumentException("Flaske findes ikke");
        }

        StringBuilder sb = new StringBuilder();

        Produkt produkt = fundetFlaske.getProdukt();

        sb.append("=== Historik for flaske ")
                .append(flaskeNr)
                .append(" ===\n\n");

        sb.append("Flaske: Nr: ")
                .append(flaskeNr)
                .append("/")
                .append(produkt.getFlasker().size())
                .append("\n\n");

        sb.append("Produkt:\n")
                .append("  Nr: ").append(produkt.getProduktNr()).append("\n")
                .append("  Alkohol: ").append(produkt.getAlkoholProcent()).append("%\n")
                .append("  Vand: ").append(produkt.getVandMængde())
                .append(" L fra ").append(produkt.getVandOprindelse()).append("\n")
                .append("  Beskrivelse: ").append(produkt.getBeskrivelse())
                .append("\n\n");

        for (WhiskySammensætning ws : produkt.getWhiskySammensætninger()) {

            Destillat destillat = ws.getDestillat();

            sb.append("Destillat:\n")
                    .append("  Navn: ").append(destillat.getDestilatNavn()).append("\n")
                    .append("  Produceret: ").append(destillat.getDato()).append("\n")
                    .append("  Mængde brugt i produktet: ")
                    .append(ws.getMængdeFraDestillat()).append(" L\n");

            sb.append("  Fra destillering:\n");

            for (DestilleringsMængde dm : destillat.getDestilleringsMængder()) {
                Destillering destillering = dm.getDestillering();

                sb.append("    - Destillering nr: ")
                        .append(destillering.getDestilleringsID())
                        .append(" (slutdato: ")
                        .append(destillering.getSlutDato())
                        .append(")\n");
            }

            sb.append("  Lagring på fad:\n");

            for (Påfyldning påfyldning : destillat.getPåfyldninger()) {

                Fad fad = påfyldning.getFad();

                sb.append("    - Fad nr: ").append(fad.getFadNr()).append("\n")
                        .append("      Mængde påfyldt: ")
                        .append(påfyldning.getMængde()).append(" L\n")
                        .append("      Tidligere indhold: ")
                        .append(fad.getTidligereIndhold()).append("\n")
                        .append("      Oprindelsesland: ")
                        .append(fad.getLand()).append("\n")
                        .append("      Påfyldningsdato: ")
                        .append(påfyldning.getDato()).append("\n");

                if (fad.getReol() != null) {
                    sb.append("      Placering: ")
                            .append(fad.getReol().getFadPlacering(fad))
                            .append("\n");
                }

                sb.append("\n");
            }
        }

        return sb.toString();
    }

}
