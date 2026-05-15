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
    public Produkt createProdukt( double vandMængde, String vandOprindelse, double alkoholProcent, String beskrivelse, KvalitetsStempel kvalitetsStempel) {
        Produkt produkt = new Produkt(produktNr, vandMængde, vandOprindelse, alkoholProcent, beskrivelse, kvalitetsStempel);
        produktNr++;
        storage.addProdukt(produkt);
        return produkt;
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
}
